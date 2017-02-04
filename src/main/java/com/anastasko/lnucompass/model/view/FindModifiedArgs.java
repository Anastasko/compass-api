package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;
import java.util.List;

@ApiObject(name="Find Modified Args", description="represent arguments for findModifiedItems method")
public class FindModifiedArgs {

    @ApiObjectField
    private long afterDate;

    @ApiObjectField
    private List<Long> owners;

    public long getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(long afterDate) {
        this.afterDate = afterDate;
    }

    public List<Long> getOwners() {
        return owners;
    }

    public void setOwners(List<Long> owners) {
        this.owners = owners;
    }
}
