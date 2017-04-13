package com.anastasko.lnucompass.infrastructure;

import java.io.Serializable;
import java.util.List;

import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.jinq.jpa.JPAJinqStream;

import com.anastasko.lnucompass.model.domain.AbstractEntity;
import org.springframework.transaction.annotation.Transactional;

public interface EntityPersistenceService<T> {

	Class<T> getEntityClass();
	T newInstance();
    EntityTypeName getEntityTypeName();

	T findOne(Long key);
	List<T> findMany(Iterable<Long> keys);
	List<T> findAll();

	T findOne(Long key, String graphName);
	T findOneByAttribute(String attribute, Object value);

	List<T> findMany(Iterable<Long> keys, String graphName);
	List<T> findAll(String graphName);

	void deleteOne(Long id);
	void deleteOne(T item);
	void deleteMany(Iterable<T> items);
	void deleteAll();

	void create(T item);
	void update(T item);

	T getReference(T item);
	T getReference(Long id);

}
