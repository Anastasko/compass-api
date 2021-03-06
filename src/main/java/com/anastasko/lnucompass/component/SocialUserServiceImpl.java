package com.anastasko.lnucompass.component;

import com.anastasko.lnucompass.implementation.AbstractEntityPersistenceServiceImpl;
import com.anastasko.lnucompass.infrastructure.SocialUserService;
import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import com.anastasko.lnucompass.model.enums.SocialProvider;
import com.anastasko.lnucompass.model.view.AuthViewModel;
import com.anastasko.lnucompass.validation.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class SocialUserServiceImpl extends AbstractEntityPersistenceServiceImpl<SocialUserAccount>
		implements SocialUserService {

	private static final Logger logger = LoggerFactory.getLogger(SocialUserServiceImpl.class);

	@Override
	public Class<SocialUserAccount> getEntityClass() {
		return SocialUserAccount.class;
	}

	@Override
	public SocialUserAccount newInstance() {
		throw new ServiceException();
	}

	@Override
	public EntityTypeName getEntityTypeName() {
		return EntityTypeName.SOCIAL_USER_ACCOUNT;
	}

	@Override
	@Transactional
	public SocialUserAccount findByAuth(AuthViewModel auth) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<SocialUserAccount> query = criteriaBuilder.createQuery(this.getEntityClass());
		Root<SocialUserAccount> root = query.from(getEntityClass());
		Predicate predicate = criteriaBuilder.and(
				criteriaBuilder.equal(root.joinMap("logins").get("token"), auth.getToken()),
				criteriaBuilder.equal(root.joinMap("logins").get("deviceUUID"), auth.getDeviceUUID())
		);
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
