import Head from "next/head";
import React, { PureComponent } from "react";
import { makeStyles } from "@material-ui/core/styles";
import NativeSelect from "@material-ui/core/NativeSelect";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  Label,
} from "recharts";

const API_PATH = "http://localhost:8080/api/"; //The base path to the Spring Boot Rest Service
var currentList = [];

const useStyles = makeStyles((theme) => ({
  formControl: {
    margin: theme.spacing(1),
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing(2),
  },
}));
/**
 * Main Page
 * @param {*} param0
 * @returns
 */
function Home({ departments, stats, selectedStatList }) {
  const classes = useStyles();
  const [state, setState] = React.useState({
    department: null,
    stats: [],
    selectedStatList: [],
  });

  /**
   * Handle a change when the department is changed
   * @param {*} event
   */
  const handleChange = (event) => {
    const name = event.target.name;
    //console.log("Name = " + name);
    //console.log("value = " + event.target.value);

    const idVal = event.target.value;

    //console.log("stats = " + stats);

    //Filter the list of departments.
    const f = stats.filter(function (d) {
      return d.department.id == event.target.value;
    });

    console.log("f = " + f);
    currentList = f;

    setState({
      ...state,
      [name]: event.target.value,
    });
  };

  let options = [];
  options.push(
    <option key={-1} value={-1}>
      Select Department
    </option>
  );

  console.log("selectedStatList = " + selectedStatList);
  console.log("currentList = " + currentList);

  //Get the list of departments for the Native Select.
  let size = departments && departments.length > 0 ? departments.length : 0;

  for (let i = 0; i < size; i++) {
    let v = departments[i];
    options.push(
      <option key={v.id} value={v.id}>
        {v.departmentName}
      </option>
    );
  }

  let graphList = []; //Graphs to add to page.

  //Check whether we have a list of department stats.
  let hasCurrentList = currentList && currentList.length > 0 ? true : false;

  if (hasCurrentList) {
    let data = [
      { name: "Monday", Total: 0 },
      { name: "Tuesday", Total: 0 },
      { name: "Wednesday", Total: 0 },
      { name: "Thursday", Total: 0 },
      { name: "Friday", Total: 0 },
      { name: "Saturday", Total: 0 },
      { name: "Sunday", Total: 0 },
    ];

    let activitySet = {}; //Complete set of activities

    //Go through the list and populate totals and convert time to hours.
    for (let i = 0; i < currentList.length; i++) {
      let s = currentList[i];
      let type = s.type;
      let dayEnum = s.dayEnum;
      let timeSpendSeconds = s.timeSpendSeconds;
      let timeInHours = (timeSpendSeconds / 60 / 60).toFixed(2);
      timeInHours = Number(timeInHours);
      let activityName = s.activity.activityName;

      let dayObj = null;
      switch (s.dayEnum) {
        case "Monday":
          dayObj = data[0];
          break;
        case "Tuesday":
          dayObj = data[1];
          break;
        case "Wednesday":
          dayObj = data[2];
          break;
        case "Thursday":
          dayObj = data[3];
          break;
        case "Friday":
          dayObj = data[4];
          break;
        case "Saturday":
          dayObj = data[5];
          break;
        case "Sunday":
          dayObj = data[6];
          break;
      }

      dayObj.Total = dayObj.Total + timeInHours;

      let cAct = dayObj[activityName];

      if (!cAct) {
        dayObj[activityName] = timeInHours;
        activitySet[activityName] = 1;
      } else {
        dayObj[activityName] = cAct + timeInHours;
      }
    }

    let lineSet = []; //The other line set not including Total
    let c = 0; //coloun to select line colour

    //Go through all the activity sets and add the line to the grpah.
    Object.keys(activitySet).forEach(function (k, index) {
      data.forEach(function (val, index) {
        if (!val[k]) {
          val[k] = 0;
        }
      });

      let stroke = "#ff0000";
      switch (c) {
        case 0:
          stroke = "#fcba03";
          break;
        case 1:
          stroke = "#00ff00";
          break;
        case 2:
          stroke = "#3dfc03";
          break;
        case 3:
          stroke = "#03fcf0";
          break;
        case 4:
          stroke = "#f403fc";
          break;
        case 5:
          stroke = "#fc0341";
          break;
      }

      //Add the line to the grpah.
      lineSet.push(
        <Line
          type="monotone"
          dataKey={k}
          stroke={stroke}
          activeDot={{ r: 8 }}
        />
      );

      c++;
    });

    //console.log("data = " + JSON.stringify(data));
    //Add the graph to the set.
    graphList.push(
      <LineChart
        width={800}
        height={400}
        data={data}
        margin={{
          top: 5,
          right: 30,
          left: 20,
          bottom: 5,
        }}
      >
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name"></XAxis>
        <YAxis></YAxis>
        <Tooltip />
        <Legend />
        <Line
          type="monotone"
          dataKey="Total"
          stroke="#8884d8"
          activeDot={{ r: 8 }}
        />
        {lineSet}
      </LineChart>
    );
  }

  return (
    <div className="container">
      <Head>
        <title>TWG Demo</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main>
        <h1 className="title">Welcome to TWG Demo</h1>
        <div>
          <NativeSelect
            value={state.departmentID}
            onChange={handleChange}
            className={classes.selectEmpty}
            inputProps={{
              name: "department",
            }}
          >
            {options}
          </NativeSelect>
        </div>
        <div>{graphList}</div>
      </main>

      <footer></footer>

      <style jsx>{`
        .container {
          min-height: 100vh;
          padding: 0 0.5rem;
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
        }

        main {
          padding: 5rem 0;
          flex: 1;
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
        }

        footer {
          width: 100%;
          height: 100px;
          border-top: 1px solid #eaeaea;
          display: flex;
          justify-content: center;
          align-items: center;
        }

        footer img {
          margin-left: 0.5rem;
        }

        footer a {
          display: flex;
          justify-content: center;
          align-items: center;
        }

        a {
          color: inherit;
          text-decoration: none;
        }

        .title a {
          color: #0070f3;
          text-decoration: none;
        }

        .title a:hover,
        .title a:focus,
        .title a:active {
          text-decoration: underline;
        }

        .title {
          margin: 0;
          line-height: 1.15;
          font-size: 4rem;
        }

        .title,
        .description {
          text-align: center;
        }

        .description {
          line-height: 1.5;
          font-size: 1.5rem;
        }

        .logo {
          height: 1em;
        }

        @media (max-width: 600px) {
          .grid {
            width: 100%;
            flex-direction: column;
          }
        }
      `}</style>

      <style jsx global>{`
        html,
        body {
          padding: 0;
          margin: 0;
          font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto,
            Oxygen, Ubuntu, Cantarell, Fira Sans, Droid Sans, Helvetica Neue,
            sans-serif;
        }

        * {
          box-sizing: border-box;
        }
      `}</style>
    </div>
  );
}

/**
 * Get the initial data from  the service.
 * @param {*} ctx
 * @returns
 */
Home.getInitialProps = async (ctx) => {
  let pd = {
    method: "GET",
    mode: "no-cors",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  };

  //Gets the list of departments at start.
  const res1 = await fetch(API_PATH + "department", pd);
  const deptList = await res1.json();

  //Get all stats and filter in UI
  //TODO: Need to fix, should get data Dying namically on handling UI event.
  const res2 = await fetch(API_PATH + "stats", pd);
  const statList = await res2.json();

  console;

  return { departments: deptList, stats: statList, selectedStatList: [] };
};

export default Home;
