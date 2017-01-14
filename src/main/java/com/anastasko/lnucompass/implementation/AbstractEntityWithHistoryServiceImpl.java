package com.anastasko.lnucompass.implementation;

import com.anastasko.lnucompass.model.enums.EntityTypeName;
import com.anastasko.lnucompass.infrastructure.EntityWithHistoryService;
import com.anastasko.lnucompass.infrastructure.HistoryEntryService;
import com.anastasko.lnucompass.model.domain.AbstractEntity;
import com.anastasko.lnucompass.model.domain.HistoryEntry;
import com.anastasko.lnucompass.model.enums.ActionKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractEntityWithHistoryServiceImpl<T extends AbstractEntity> extends AbstractEntityPersistenceServiceImpl<T> implements EntityWithHistoryService<T> {

	@Autowired
	private HistoryEntryService historyEntryService;

	abstract public EntityTypeName getEntityTypeName();

	@Override
	@Transactional
	public void create(T item){
		super.create(item);
		HistoryEntry entry = new HistoryEntry();
		entry.setAction(ActionKind.CREATE);
		entry.setEntity(item.getId());
		entry.setType(getEntityTypeName());
		historyEntryService.create(entry);
	}

	@Override
	@Transactional
	public void update(T item){
		super.update(item);
		HistoryEntry entry = new HistoryEntry();
		entry.setAction(ActionKind.UPDATE);
		entry.setEntity(item.getId());
		entry.setType(getEntityTypeName());
		historyEntryService.create(entry);
	}

	@Override
	@Transactional
	public void deleteOne(Long id){
		super.deleteOne(id);
		HistoryEntry entry = new HistoryEntry();
		entry.setAction(ActionKind.DELETE);
		entry.setEntity(id);
		entry.setType(getEntityTypeName());
		historyEntryService.create(entry);
	}

}
