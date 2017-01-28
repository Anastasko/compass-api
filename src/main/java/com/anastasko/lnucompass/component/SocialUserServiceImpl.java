package com.anastasko.lnucompass.component;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anastasko.lnucompass.implementation.AbstractEntityPersistenceServiceImpl;
import com.anastasko.lnucompass.infrastructure.SocialUserService;
import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.domain.UserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;
import com.anastasko.lnucompass.model.view.UserViewModel;
import com.anastasko.lnucompass.validation.exceptions.DuplicateEmailException;

@Service
public class SocialUserServiceImpl extends AbstractEntityPersistenceServiceImpl<SocialUserAccount>
		implements SocialUserService {

	private static final Logger logger = LoggerFactory.getLogger(SocialUserServiceImpl.class);

	@Override
	public Class<SocialUserAccount> getEntityClass() {
		return SocialUserAccount.class;
	}

	@Override
	@Transactional
	public SocialUserAccount findByToken(String token) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<SocialUserAccount> query = criteriaBuilder.createQuery(this.getEntityClass());
		Root<SocialUserAccount> root = query.from(getEntityClass());
		Predicate predicate = criteriaBuilder.equal(root.get("token"), token);
		query = query.select(root).where(predicate);
		TypedQuery<SocialUserAccount> typedQuery = getEntityManager().createQuery(query);
		List<SocialUserAccount> result = typedQuery.getResultList();
		return result.size() == 0 ? null : result.get(0);
	}

	@Override
	@Transactional
	public SocialUserAccount findByUserIdAndProvider(String userId, SocialProvider provider) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<SocialUserAccount> query = criteriaBuilder.createQuery(this.getEntityClass());
		Root<SocialUserAccount> root = query.from(getEntityClass());
		Predicate predicate = criteriaBuilder.and(
			criteriaBuilder.equal(root.get("userId"), userId),
			criteriaBuilder.equal(root.get("provider"), provider)
		);
		query = query.select(root).where(predicate);
		TypedQuery<SocialUserAccount> typedQuery = getEntityManager().createQuery(query);
		List<SocialUserAccount> result = typedQuery.getResultList();
		return result.size() == 0 ? null : result.get(0);
	}

}
