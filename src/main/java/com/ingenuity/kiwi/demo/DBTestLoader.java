package com.ingenuity.kiwi.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ingenuity.kiwi.demo.entities.Activity;
import com.ingenuity.kiwi.demo.entities.Department;
import com.ingenuity.kiwi.demo.entities.Statistic;
import com.ingenuity.kiwi.demo.entities.enums.StatsDayEnum;
import com.ingenuity.kiwi.demo.entities.enums.StatsTypeEnum;
import com.ingenuity.kiwi.demo.repo.ActivityRepo;
import com.ingenuity.kiwi.demo.repo.DepartmentRepo;
import com.ingenuity.kiwi.demo.repo.StatisticsRepo;

@Configuration
class DBTestLoader {

	private static final Logger log = LoggerFactory.getLogger(DBTestLoader.class);

	@Bean
	CommandLineRunner initDatabase(DepartmentRepo departmentR, ActivityRepo activityR, StatisticsRepo statR) {

		return args -> {
			// Create Departments
			Department entertainmentD = Department.builder().departmentName("Entertainment").build();
			Department gardeningD = Department.builder().departmentName("Gardening").build();
			Department eStockroomD = Department.builder().departmentName("Stockroom").build();

			departmentR.save(entertainmentD);
			log.info("Saving Department " + entertainmentD);
			departmentR.save(gardeningD);
			log.info("Saving Department " + gardeningD);
			departmentR.save(eStockroomD);
			log.info("Saving Department " + eStockroomD);

			// Create Activities
			Activity tvA = Activity.builder().activityName("Set up display TV").build();
			Activity demoA = Activity.builder().activityName("Running demonstration session").build();
			Activity waterA = Activity.builder().activityName("Watering").build();
			Activity tidyA = Activity.builder().activityName("Tidying up").build();
			Activity meetingA = Activity.builder().activityName("Meeting").build();
			Activity scanA = Activity.builder().activityName("Scanning").build();

			activityR.save(tvA);
			log.info("Saving Activity " + tvA);
			activityR.save(demoA);
			log.info("Saving Activity " + demoA);
			activityR.save(waterA);
			log.info("Saving Activity " + waterA);
			activityR.save(tidyA);
			log.info("Saving Activity " + tidyA);
			activityR.save(meetingA);
			log.info("Saving Activity " + meetingA);
			activityR.save(scanA);
			log.info("Saving Activity " + scanA);

			// Create statistics
			// Entertainment
			for (StatsDayEnum e : StatsDayEnum.values()) {
				log.info("Saving Statistic " + statR.save(Statistic.builder().department(entertainmentD).activity(tvA)
						.dayEnum(e).type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(30l * 60l).build()));
			}

			log.info("Saving Statistic " + statR
					.save(Statistic.builder().department(entertainmentD).activity(demoA).dayEnum(StatsDayEnum.Saturday)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(30l * 60l).build()));

			log.info("Saving Statistic " + statR
					.save(Statistic.builder().department(entertainmentD).activity(demoA).dayEnum(StatsDayEnum.Saturday)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(30l * 60l).build()));

			// Gardening Department
			for (StatsDayEnum e : StatsDayEnum.values()) {
				log.info("Saving Statistic " + statR.save(Statistic.builder().department(gardeningD).activity(waterA)
						.dayEnum(e).type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(60l * 60l).build()));
			}

			for (StatsDayEnum e : StatsDayEnum.values()) {
				switch (e) {
				case Saturday:
				case Sunday:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(gardeningD).activity(tidyA)
							.dayEnum(e).type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(120l * 60l).build()));
					break;
				default:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(gardeningD).activity(tidyA)
							.dayEnum(e).type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(90l * 60l).build()));
					break;
				}

			}

			// Stockroom
			for (StatsDayEnum e : StatsDayEnum.values()) {
				log.info("Saving Statistic " + statR.save(Statistic.builder().department(eStockroomD).activity(meetingA)
						.dayEnum(e).type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(45l * 60l).build()));
			}

			for (StatsDayEnum e : StatsDayEnum.values()) {
				switch (e) {
				case Monday:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(eStockroomD).activity(scanA).dayEnum(e)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(15073 * 5l).build()));
					break;
				case Tuesday:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(eStockroomD).activity(scanA).dayEnum(e)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(24555 * 5l).build()));
					break;
				case Wednesday:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(eStockroomD).activity(scanA).dayEnum(e)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(13000 * 5l).build()));
					break;
				case Thursday:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(eStockroomD).activity(scanA).dayEnum(e)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(18832 * 5l).build()));
					break;
				case Friday:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(eStockroomD).activity(scanA).dayEnum(e)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(10009 * 5l).build()));
					break;
				case Saturday:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(eStockroomD).activity(scanA).dayEnum(e)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(1556 * 5l).build()));
					break;
				case Sunday:
					log.info("Saving Statistic " + statR.save(Statistic.builder().department(eStockroomD).activity(scanA).dayEnum(e)
							.type(StatsTypeEnum.TotalAllTime).timeSpendSeconds(0l).build()));
					break;
				}

			}
		};
	}
}