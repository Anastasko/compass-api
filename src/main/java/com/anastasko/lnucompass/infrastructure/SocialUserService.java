package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;

public interface SocialUserService extends EntityPersistenceService<SocialUserAccount>{

	SocialUserAccount findByToken(String token);
	
	SocialUserAccount findByUserIdAndProvider(String userId, SocialProvider provider);
		
}
