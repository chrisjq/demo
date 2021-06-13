package com.ingenuity.kiwi.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/**
 * Default home controller. TODO: Not implemented.
 * @author chris
 *
 */
public class HomeController implements ErrorController {


	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
}
