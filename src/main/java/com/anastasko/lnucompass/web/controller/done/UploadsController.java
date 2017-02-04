package com.anastasko.lnucompass.web.controller.done;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.anastasko.lnucompass.model.view.FileInfo;
import com.anastasko.lnucompass.model.view.StringIdsList;
import com.anastasko.lnucompass.web.controller.AbstractController;
import org.jsondoc.core.annotation.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.anastasko.lnucompass.configuration.WebConfig;

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

    @RequestMapping(method = RequestMethod.POST)
    public List<FileInfo> getList(@RequestBody StringIdsList ids, HttpServletResponse response) {
	    List<FileInfo> result = new ArrayList<>();
	    for(String path : ids.getIds()){
            File file = new File(WebConfig.COMPASS_DIR + path);
            if (file.exists()){
                result.add(new FileInfo(path, file.lastModified()));
            }
        }
        response.setHeader("Timestamp", ""+System.currentTimeMillis());
        return result;
    }


}
