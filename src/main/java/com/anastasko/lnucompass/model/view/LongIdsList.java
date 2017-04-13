package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.ArrayList;
import java.util.List;

@ApiObject(name="object with number ids", description = "object with number ids", show = false)
public class LongIdsList {

    @ApiObjectField
    private List<Long> ids = new ArrayList<Long>();

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
