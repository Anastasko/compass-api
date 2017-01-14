package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.HistoryEntry;

import java.util.List;

public interface HistoryEntryService extends EntityPersistenceService<HistoryEntry> {

    List<HistoryEntry> findAfter(Long order);

}
