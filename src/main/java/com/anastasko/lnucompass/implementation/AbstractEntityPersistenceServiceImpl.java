package com.anastasko.lnucompass.implementation;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anastasko.lnucompass.infrastructure.EntityPersistenceService;
import com.anastasko.lnucompass.infrastructure.PropertyService;
import com.anastasko.lnucompass.model.domain.AbstractEntity;
import com.anastasko.lnucompass.validation.exceptions.DomainModelException;


@Service
public abstract class AbstractEntityPersistenceServiceImpl<T extends AbstractEntity> implements EntityPersistenceService<T> {

	@PersistenceContext(unitName="entityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private JinqJPAStreamProvider jinqJPAStreamProvider;

	protected EntityManager getEntityManager() {
		
		return entityManager;
	}
	
	protected final CriteriaQuery<T> createSelectQuery(String graphName) {

		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
		query = query.select(query.from(getEntityClass()));
		return graphName == null ? query : query.distinct(true);
	}
	
	protected final List<T> getSelectQueryResultSet(CriteriaQuery<T> query, String graphName) {
		
		TypedQuery<T> typedQuery = getEntityManager().createQuery(query);
		if(graphName != null) {
			typedQuery = typedQuery.setHint(propertyService.get("jpa.graph.fetch"), getEntityManager().getEntityGraph(graphName));
		}
		
		return typedQuery.getResultList();
	}

	@PostConstruct
	private void postConstruct() {
		try {
			jinqJPAStreamProvider.
					registerAssociationAttribute(
							this.getEntityClass().getMethod("getId"),
							this.getEntityClass(), "id", false);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public final List<T> findMany(Iterable<Long> keys) {
		
		return findMany(keys, (String)null);
	}

	@Override
	@Transactional(readOnly = true)
	public final List<T> findAll() {

		return findAll((String)null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public final T findOne(Long key, String graphName) {
		
		if(graphName == null) {
			return getEntityManager().find(getEntityClass(), key);
		} else {
			Map<String, Object> hints = new HashMap<String, Object>();
			hints.put(propertyService.get("jpa.graph.fetch"), getEntityManager().getEntityGraph(graphName));
			return getEntityManager().find(getEntityClass(), key, hints);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public final List<T> findMany(Iterable<Long> keys, String graphName) {
		
		if(!(keys instanceof Collection)){
			throw new DomainModelException();
		}

		CriteriaQuery<T> query = createSelectQuery(graphName);
		query = query.where(query.getRoots().iterator().next().get("id").in((Collection<Long>)keys));
		return getSelectQueryResultSet(query, graphName);
	}

	@Override
	@Transactional(readOnly = true)
	public final List<T> findAll(String graphName) {

		CriteriaQuery<T> query = createSelectQuery(graphName);
		return getSelectQueryResultSet(query, graphName);
	}
	
	@Override
	@Transactional
	public void deleteOne(Long id) {
		
		getEntityManager().remove(findOne(id));
	}
	
	@Override
	@Transactional
	public void deleteOne(T item) {
		
		getEntityManager().remove(findOne(item.getId()));
	}

	@Override
	@Transactional
	public void deleteMany(Iterable<T> items) {
		
		StreamSupport.stream(items.spliterator(), false).forEach(key -> deleteOne(key));
	}

	@Override
	@Transactional
	public final void deleteAll() {

		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(getEntityClass());
		criteriaDelete.from(getEntityClass());
		getEntityManager().createQuery(criteriaDelete).executeUpdate();
	}

	@Override
	@Transactional
	public void create(T item) {
		getEntityManager().persist(item);
	}

	@Override
	@Transactional
	public void update(T item) {
		getEntityManager().merge(item);
	} 
	
	@Override
	@Transactional
	public T getReference(Long id) {
		if (id != null) {
			return getEntityManager().getReference(getEntityClass(), id);
		}
		return null;
	}

	@Override
	public T findOne(Long key) {
		return findOne(key, (String) null);
	}

	@Override
	public T getReference(T item) {
		if (item != null){
			return getEntityManager().getReference(getEntityClass(), item.getId());
		}
		return null;
	}
}
