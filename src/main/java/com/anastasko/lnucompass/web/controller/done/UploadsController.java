package com.anastasko.lnucompass.web.controller.done;

import com.anastasko.lnucompass.configuration.WebConfig;
import com.anastasko.lnucompass.web.controller.AbstractController;
import org.jsondoc.core.annotation.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Api(name = "Uploads", description = "UploadsController")
@RestController
@RequestMapping("/uploads")
class UploadsController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(UploadsController.class);

	private List<String> populateList(File file, int crop) {
		List<String> files = new ArrayList<>();
		if (file.exists()) {
			for (File item : file.listFiles()) {
				if (item.isFile()) {
					files.add(item.getPath().substring(crop));
				} else if (item.isDirectory()) {
					files.addAll(populateList(item, crop));
				}
			}
		}
		return files;
	}

	private List<String> traverse(String prefix){
		String path = WebConfig.COMPASS_DIR + prefix;
		logger.info("get path list from " + path);
		File file = new File(path);
		return populateList(file, WebConfig.COMPASS_DIR.length());
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<String> getList(@RequestParam(value = "prefix", required = false) String prefix) {
        if (prefix == null){
            prefix = "/uploads";
        }
	    return traverse(prefix);
	}

}
