package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.SocialUserAccount;

public interface SocialUserService extends EntityPersistenceService<SocialUserAccount>{

	SocialUserAccount findByToken(String token);
	
	SocialUserAccount findByUserId(String userId);
		
}
