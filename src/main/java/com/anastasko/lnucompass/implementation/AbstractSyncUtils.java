package com.anastasko.lnucompass.implementation;

import com.anastasko.lnucompass.api.infrastructure.service.CityItemService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AbstractSyncUtils {

    @Autowired
    private CityItemService cityItemService;

    public List<EntityCityItem> sortedCityItems() {
        return cityItemService.findAll(true).stream()
                .sorted(Comparator.comparing(item -> item.getId()))
                .collect(Collectors.toList());
    }

}
