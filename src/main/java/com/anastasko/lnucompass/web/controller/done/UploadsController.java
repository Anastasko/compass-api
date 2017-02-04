package com.anastasko.lnucompass.web.controller.done;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.swing.filechooser.FileView;

import com.anastasko.lnucompass.model.view.FileViewModel;
import com.anastasko.lnucompass.web.controller.AbstractController;
import org.jsondoc.core.annotation.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anastasko.lnucompass.configuration.WebConfig;

@Api(name = "Uploads", description = "UploadsController")
@RestController
@RequestMapping("/uploads")
class UploadsController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(UploadsController.class);

	private List<FileViewModel> populateList(File file, int crop) {
		List<FileViewModel> files = new ArrayList<>();
		if (file.exists()) {
			for (File item : file.listFiles()) {
				if (item.isFile()) {
					FileViewModel fileView = new FileViewModel();
					fileView.setPath(item.getPath().substring(crop-1));
					fileView.setModified(new Date(item.lastModified()));
					files.add(fileView);
				} else if (item.isDirectory()) {
					files.addAll(populateList(item, crop));
				}
			}
		}
		return files;
	}

	private List<FileViewModel> traverse(String prefix){
		String path = WebConfig.COMPASS_DIR + prefix;
		logger.info("get path list from " + path);
		File file = new File(path);
		return populateList(file, WebConfig.COMPASS_DIR.length());
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<FileViewModel> getList(@RequestParam(value = "prefix", required = false) String prefix) {
        if (prefix == null){
            prefix = "uploads";
        }
	    return traverse(prefix);
	}


}
