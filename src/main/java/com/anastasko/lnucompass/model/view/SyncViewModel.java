package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.api.model.view.sync.SyncActiveViewModel;
import com.anastasko.lnucompass.api.model.view.sync.SyncDeletedViewModel;

public class SyncViewModel {

    private SyncActiveViewModel active = new SyncActiveViewModel();

    private SyncDeletedViewModel deleted = new SyncDeletedViewModel();

    public SyncActiveViewModel getActive() {
        return active;
    }

    public void setActive(SyncActiveViewModel active) {
        this.active = active;
    }

    public SyncDeletedViewModel getDeleted() {
        return deleted;
    }

    public void setDeleted(SyncDeletedViewModel deleted) {
        this.deleted = deleted;
    }
}
