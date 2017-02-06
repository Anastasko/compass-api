package com.anastasko.lnucompass.component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anastasko.lnucompass.implementation.AbstractEntityPersistenceServiceImpl;
import com.anastasko.lnucompass.infrastructure.UserService;
import com.anastasko.lnucompass.model.domain.UserAccount;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import com.anastasko.lnucompass.model.view.UserAuthViewModel;
import com.anastasko.lnucompass.model.view.UserViewModel;
import com.anastasko.lnucompass.validation.exceptions.DuplicateEmailException;
import com.anastasko.lnucompass.validation.exceptions.ServiceException;

@Service
public class UserServiceImpl extends AbstractEntityPersistenceServiceImpl<UserAccount> implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Class<UserAccount> getEntityClass() {
		return UserAccount.class;
	}

	@Override
	public UserAccount newInstance() {
		throw new ServiceException();
	}

	@Override
	public EntityTypeName getEntityTypeName() {
		return EntityTypeName.USER_ACCOUNT;
	}

	private void validate(UserViewModel user) {
		if (user.getUsername() == null || user.getUsername().length() < 3) {
			throw new ServiceException("username requires at least 3 characters");
		}
		if (user.getPassword() == null || user.getPassword().length() < 5) {
			throw new ServiceException("password requires at least 5 characters");
		}
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

	@Override
	@Transactional
	public UserAccount registerNewUser(UserViewModel userViewModel) throws DuplicateEmailException {		
		
		validate(userViewModel);
		
		if (findByEmail(userViewModel.getEmail()) != null) {
			throw new DuplicateEmailException("The email address " + userViewModel.getEmail() + " is already in use.");
		}
		if (findByUsername(userViewModel.getUsername()) != null) {
			throw new DuplicateEmailException("The username " + userViewModel.getEmail() + " is already in use.");
		}
		
		UserAccount user = new UserAccount();
		user.setUsername(userViewModel.getUsername());
		user.setEmail(userViewModel.getEmail());
		user.setFirstName(userViewModel.getFirstName());
		user.setLastName(userViewModel.getLastName());
		user.setPassword(passwordEncoder.encode(userViewModel.getPassword()));
		
		create(user);
		return user;
	}

	@Override
	@Transactional
	public UserViewModel auth(UserAuthViewModel userModel) {
		UserAccount user = findByUsername(userModel.getUsername());
		if (user == null){
			throw new ServiceException("user with username '" + userModel.getUsername() + "' does not exist");
		}
		if (!passwordEncoder.matches(userModel.getPassword(), user.getPassword())){
			throw new ServiceException("password does not match");
		}		
		user.setToken(UUID.randomUUID().toString());
		user.setTokenExpires(new Date((new Date()).getTime() + 1000*60*60*2));
		update(user);
		return new UserViewModel(user);
	}

}
