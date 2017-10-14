package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.api.model.view.sync.SyncCountViewModel;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.anastasko.lnucompass.model.view.SyncViewModel;

import java.util.Set;

public interface SyncService {

    String exchange(String hash, Set<Long> cityItems);

    String next(String hash);

    /** generated */

    SyncViewModel sync(SyncModels hash);

    SyncCountViewModel count();
}
