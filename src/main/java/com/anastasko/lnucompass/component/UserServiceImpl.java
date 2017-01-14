package com.anastasko.lnucompass.component;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anastasko.lnucompass.implementation.AbstractEntityPersistenceServiceImpl;
import com.anastasko.lnucompass.infrastructure.UserService;
import com.anastasko.lnucompass.model.domain.UserAccount;
import com.anastasko.lnucompass.model.view.UserViewModel;
import com.anastasko.lnucompass.validation.exceptions.DuplicateEmailException;

@Service
public class UserServiceImpl extends AbstractEntityPersistenceServiceImpl<UserAccount> implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserService repository;
	
	@Override
	public Class<UserAccount> getEntityClass() {
		return UserAccount.class;
	}

	@Override
	public UserAccount findByEmail(String email) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserAccount> query = criteriaBuilder.createQuery(this.getEntityClass());
		Root<UserAccount> root = query.from(getEntityClass());
		Predicate predicate = criteriaBuilder.equal(root.get("email"), email);
		query = query.select(root).where(predicate);
		TypedQuery<UserAccount> typedQuery = getEntityManager().createQuery(query);
		List<UserAccount> result = typedQuery.getResultList();
		return result.size() == 0 ? null : result.get(0);
	}
	
	@Override
	public UserAccount findByUsername(String username) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserAccount> query = criteriaBuilder.createQuery(this.getEntityClass());
		Root<UserAccount> root = query.from(getEntityClass());
		Predicate predicate = criteriaBuilder.equal(root.get("username"), username);
		query = query.select(root).where(predicate);
		TypedQuery<UserAccount> typedQuery = getEntityManager().createQuery(query);
		List<UserAccount> result = typedQuery.getResultList();
		return result.size() == 0 ? null : result.get(0);
	}
	
	@Transactional
	public UserAccount registerNewUser(UserViewModel user) throws DuplicateEmailException {
		logger.debug("Registering new user account with information: {}", user);

		if (emailExist(user.getEmail())) {
			logger.debug("Email: {} exists. Throwing exception.", user.getEmail());
			throw new DuplicateEmailException(
					"The email address: " + user.getEmail() + " is already in use.");
		}

		logger.debug("Email: {} does not exist. Continuing registration.", user.getEmail());

		UserAccount registered = new UserAccount();
		registered.setEmail(user.getEmail());
		registered.setFirstName(user.getFirstName());
		registered.setLastName(user.getLastName());
		registered.setPassword(null);
		registered.setSignInProvider(user.getSignInProvider());

		logger.debug("Persisting new user with information: {}", registered);

		repository.create(registered);
		return registered;
	}

	private boolean emailExist(String email) {
		logger.debug("Checking if email {} is already found from the database.", email);

		UserAccount user = repository.findByEmail(email);

		if (user != null) {
			logger.debug("User account: {} found with email: {}. Returning true.", user, email);
			return true;
		}

		logger.debug("No user account found with email: {}. Returning false.", email);

		return false;
	}
	
}
