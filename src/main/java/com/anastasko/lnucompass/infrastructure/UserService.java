package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.UserAccount;
import com.anastasko.lnucompass.model.view.UserAuthViewModel;
import com.anastasko.lnucompass.model.view.UserViewModel;
import com.anastasko.lnucompass.validation.exceptions.DuplicateEmailException;

public interface UserService extends EntityPersistenceService<UserAccount> {

	UserAccount findByEmail(String email);

	UserAccount registerNewUser(UserViewModel registration) throws DuplicateEmailException;

	UserAccount findByUsername(String username);

	UserViewModel auth(UserAuthViewModel userModel);
	
}
