package com.anastasko.lnucompass.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.flow.ApiFlowSet;
import org.jsondoc.core.pojo.ApiVisibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Api(description = "PathRespondController", name = "PathRespondController", visibility=ApiVisibility.PRIVATE)
@Controller
@RequestMapping("path")
class PathRespondController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(PathRespondController.class);

	@Autowired
	private ServletContext context;

	private List<String> populateList(File file, int crop) {
		List<String> files = new ArrayList<>();
		if (file.exists()) {
			for (File item : file.listFiles()) {
				if (item.isFile()) {
					files.add(item.getPath().substring(crop-1));
					//logger.info(item.getPath());
				} else if (item.isDirectory()) {
					files.addAll(populateList(item, crop));
				}
			}
		}
		return files;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<String> getList(@RequestParam String prefix, HttpServletRequest request) {
		String path = File.separator + properties.get("routing.uploads") + File.separator + prefix;
		logger.info("get path list from " + path);
		int crop = context.getRealPath("/").length()-context.getContextPath().length();		
		String root = context.getRealPath(path);
		logger.info("root = " + root);
		File file = new File(root);
		return populateList(file, crop);
	}

}
