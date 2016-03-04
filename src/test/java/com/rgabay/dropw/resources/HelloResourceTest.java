package com.rgabay.dropw.resources;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.google.common.base.Optional;
import com.rgabay.dropw.auth.HelloAuthenticator;
import com.rgabay.dropw.core.User;
import com.rgabay.dropw.resources.HelloResource;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.testing.junit.ResourceTestRule;

public class HelloResourceTest {
	
	private static final HttpAuthenticationFeature FEATURE = HttpAuthenticationFeature.basic("s","password");
	
	@BeforeClass
	public static void setupClass(){
		RULE.getJerseyTest().client().register(FEATURE);
	}

	public static final Authenticator<BasicCredentials, User> AUTHENTICATOR = new Authenticator<BasicCredentials, User>(){
		@Override
		public Optional<User> authenticate(BasicCredentials arg0) throws AuthenticationException {
			return Optional.of(new User());
		}
		
	};
	
	static BasicCredentialAuthFilter<User> bcaf = new BasicCredentialAuthFilter.Builder<User>()
														.setAuthenticator(new HelloAuthenticator("password")) //TODO figure out how to pass configuration here
														.setRealm("SECRET REALM")
														.buildAuthFilter();
    
	@ClassRule
	public static final ResourceTestRule RULE = ResourceTestRule.builder().addProvider(	bcaf).setTestContainerFactory(
												new GrizzlyWebTestContainerFactory()).
												addResource(new HelloResource()).build();
	
	@Test
	public void testGreeting() {		
		String expected = "Hello Dropwizard";
		String actual = RULE.getJerseyTest().target("/hello").request(MediaType.TEXT_PLAIN).get(String.class);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSecuredGreeting() {
		
		String expected = "Top Secret data";
		String actual = RULE.getJerseyTest().target("/hello/protected").request(MediaType.TEXT_PLAIN).get(String.class);
		
		assertEquals(expected, actual);
	}

}
