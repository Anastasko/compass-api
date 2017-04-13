package com.anastasko.lnucompass.infrastructure;

import java.util.Map;

import com.anastasko.lnucompass.model.enums.ItemKind;

public interface ListIconsService {

	Map<String, Map<String, String>> getAndroidIcons();
	
	Map<String, Map<String, String>> getiosIcons();

	Map<ItemKind, String> getMap();
	
}
