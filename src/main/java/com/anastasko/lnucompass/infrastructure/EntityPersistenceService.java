package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.enums.EntityTypeName;
import com.anastasko.lnucompass.model.view.SyncModels;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntityPersistenceService<T> {

	Class<T> getEntityClass();
	T newInstance();
    EntityTypeName getEntityTypeName();

	T findOne(Long key);
	List<T> findMany(Iterable<Long> keys);
	List<T> findAll();

	T findOne(Long key, String graphName);

	List<T> findMany(Iterable<Long> keys, String graphName);

	List<T> findAll(String graphName);

	void deleteOne(T item);
	void deleteMany(Iterable<T> items);
	void deleteAll();

	void create(T item);
	void update(T item);

	T getReference(T item);
	T getReference(Long id);

}
