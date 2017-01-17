package com.anastasko.lnucompass.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anastasko.lnucompass.configuration.WebConfig;

//@Api(description = "PathRespondController", name = "PathRespondController", visibility=ApiVisibility.PRIVATE)
@RestController
@RequestMapping("/path")
class PathRespondController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(PathRespondController.class);

	@Autowired
	private ServletContext context;

	private List<String> populateList(File file, int crop) {
		List<String> files = new ArrayList<>();
		if (file.exists()) {
			for (File item : file.listFiles()) {
				if (item.isFile()) {
					files.add(context.getContextPath() + item.getPath().substring(crop-1));
				} else if (item.isDirectory()) {
					files.addAll(populateList(item, crop));
				}
			}
		}
		return files;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<String> getList(@RequestParam String prefix) {
		String path = WebConfig.COMPASS_DIR + prefix;
		logger.info("get path list from " + path);
		File file = new File(path);
		return populateList(file, WebConfig.COMPASS_DIR.length());
	}

}
