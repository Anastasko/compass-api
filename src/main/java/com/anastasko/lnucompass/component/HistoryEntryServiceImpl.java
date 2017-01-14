package com.anastasko.lnucompass.component;

import com.anastasko.lnucompass.implementation.AbstractEntityPersistenceServiceImpl;
import com.anastasko.lnucompass.infrastructure.HistoryEntryService;
import com.anastasko.lnucompass.infrastructure.UserService;
import com.anastasko.lnucompass.model.domain.HistoryEntry;
import com.anastasko.lnucompass.model.domain.HistoryEntry;
import com.anastasko.lnucompass.model.view.UserViewModel;
import com.anastasko.lnucompass.validation.exceptions.DuplicateEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class HistoryEntryServiceImpl extends AbstractEntityPersistenceServiceImpl<HistoryEntry> implements HistoryEntryService {

	private static final Logger logger = LoggerFactory.getLogger(HistoryEntryServiceImpl.class);
	
	@Override
	public Class<HistoryEntry> getEntityClass() {
		return HistoryEntry.class;
	}

    @Override
    @Transactional
	public List<HistoryEntry> findAfter(Long order) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<HistoryEntry> query = criteriaBuilder.createQuery(this.getEntityClass());
		Root<HistoryEntry> root = query.from(getEntityClass());
		Predicate predicate = criteriaBuilder.greaterThan(root.get("order"), order);
		query = query.select(root).where(predicate);
		TypedQuery<HistoryEntry> typedQuery = getEntityManager().createQuery(query);
		List<HistoryEntry> result = typedQuery.getResultList();
		return result;
	}

	private long selectCount(){
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<HistoryEntry> root = query.from(getEntityClass());
		query = query.select(criteriaBuilder.count(root));
		return getEntityManager().createQuery(query).getSingleResult();
	}

	@Override
    @Transactional
	public void create(HistoryEntry item) {
		item.setOrder(selectCount()+1);
		super.create(item);
	}
}
