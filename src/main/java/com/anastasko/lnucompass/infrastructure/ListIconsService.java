package com.anastasko.lnucompass.infrastructure;

import java.util.Map;

public interface ListIconsService {

	Map<String, Map<String, String>> getAndroidIcons();
	
	Map<String, Map<String, String>> getiosIcons();
	
}
