package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.ArrayList;
import java.util.List;

@ApiObject(name="object with string ids", description = "object with string ids", show = false)
public class StringIdsList {

    @ApiObjectField
    private List<String> ids = new ArrayList<String>();

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
