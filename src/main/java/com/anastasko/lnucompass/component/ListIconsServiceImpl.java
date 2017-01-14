package com.anastasko.lnucompass.component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anastasko.lnucompass.infrastructure.ListIconsService;
import com.anastasko.lnucompass.infrastructure.PropertyService;

@Service
public class ListIconsServiceImpl implements ListIconsService {

	private static final Logger logger = LoggerFactory.getLogger(ListIconsServiceImpl.class);

	@Autowired
	private ServletContext context;
	
	@Autowired
	protected PropertyService properties;
	
	/**
	 * Android icons
	 */
	
	private String getAndroidIconName(String fullFileName){
    	int index = fullFileName.indexOf('(');
    	return fullFileName.substring(0, index);
    }
    
    private String getAndroidIconSize(String fullFileName){
    	int index1 = fullFileName.indexOf('(');
    	int index2 = fullFileName.indexOf(')');
    	return fullFileName.substring(index1+1, index2);
    }
	
	@Override
	public Map<String, Map<String, String>> getAndroidIcons() {
		final String SOURCE_FOLDER = context.getRealPath("/");
		final String PATH_TO_ICONS = 
						properties.get("routing.uploads") + "/" + 
						properties.get("routing.icons") + "/" + 
						properties.get("routing.android") + "/";
		
		File folder = new File(SOURCE_FOLDER + "/" + PATH_TO_ICONS);
		logger.info("absolute path = " + folder.getAbsolutePath());
		Map<String, Map<String, String>> json = new HashMap<String, Map<String, String>>();
		if (folder.listFiles() != null){
			for(File file : folder.listFiles()){
				if (file.isFile()){
					String iconName = getAndroidIconName(file.getName());
					String iconSize = getAndroidIconSize(file.getName());
					Map<String, String> iconPathes = json.get(iconName);
					String iconUrl = context.getContextPath() + "/" + PATH_TO_ICONS + file.getName();
					if (iconPathes == null){
						iconPathes = new HashMap<String, String>();
						iconPathes.put(iconSize, iconUrl);
						json.put(iconName, iconPathes);
					} else {
						iconPathes.put(iconSize, iconUrl);
					}
				}
			}
		}
		return json;
	}
	
	/**
	 * IOS icons
	 */
	
	private String getiosIconName(String fullFileName) {
		int index = fullFileName.indexOf('@');
		return fullFileName.substring(0, index);
	}

	private String getiosIconSize(String fullFileName) {
		int index1 = fullFileName.indexOf('@');
		int index2 = fullFileName.indexOf('.');
		return fullFileName.substring(index1, index2);
	}

	@Override
	public Map<String, Map<String, String>> getiosIcons() {
		final String SOURCE_FOLDER = context.getRealPath("/");
		final String PATH_TO_ICONS = 
						properties.get("routing.uploads") + "/" + 
						properties.get("routing.icons") + "/" + 
						properties.get("routing.ios") + "/";
		
		File folder = new File(SOURCE_FOLDER + "/" + PATH_TO_ICONS);
		logger.info("absolute path = " + folder.getAbsolutePath());
		Map<String, Map<String, String>> json = new HashMap<String, Map<String, String>>();
		if (folder.listFiles() != null){
			for(File file : folder.listFiles()){
				if (file.isFile()){
					String iconName = getiosIconName(file.getName());
					String iconSize = getiosIconSize(file.getName());
					Map<String, String> iconPathes = json.get(iconName);
					String iconUrl = context.getContextPath() + "/" + PATH_TO_ICONS + file.getName();
					if (iconPathes == null){
						iconPathes = new HashMap<String, String>();
						iconPathes.put(iconSize, iconUrl);
						json.put(iconName, iconPathes);
					} else {
						iconPathes.put(iconSize, iconUrl);
					}
				}
			}
		}
		return json;
	}

}
