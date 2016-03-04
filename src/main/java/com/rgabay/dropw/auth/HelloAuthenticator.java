package com.rgabay.dropw.auth;

import com.google.common.base.Optional;
import com.rgabay.dropw.core.User;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class HelloAuthenticator implements Authenticator<BasicCredentials, User> {
	
	private String password;
	
    public HelloAuthenticator(String password) {
		super();
		this.password = password;
	}



	@Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (password.equals(credentials.getPassword())) {
            return Optional.of(new User());
        }
        return Optional.absent();
    }
}
