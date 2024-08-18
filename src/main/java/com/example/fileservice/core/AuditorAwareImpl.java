package com.example.fileservice.core;

import jakarta.annotation.Resource;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Resource
    UserContext userContext;

    @Override
    public Optional<Long> getCurrentAuditor() {

        try {
            return Optional.of(userContext.getId());
        } catch (Exception e) {
            return null;
        }
    }


// ------------------ Use below code for spring security --------------------------

/*class SpringSecurityAuditorAware implements AuditorAware<User> {

	public User getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		return ((MyUserDetails) authentication.getPrincipal()).getUser();
	}
}*/
}