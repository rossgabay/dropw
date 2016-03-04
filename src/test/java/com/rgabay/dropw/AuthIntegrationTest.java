package com.rgabay.dropw;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.rgabay.dropw.DropwApplication;
import com.rgabay.dropw.DropwConfiguration;

import io.dropwizard.testing.junit.DropwizardAppRule;

@Ignore
public class AuthIntegrationTest {
	
	private static final String CONFIG_PATH = "config.yml"; //using the same config for test and for the runtime for now
	
	public static final DropwizardAppRule<DropwConfiguration> RULE = 
			new DropwizardAppRule<>(
									DropwApplication.class,
									CONFIG_PATH
									);

	private static final String TARGET = "http://localhost:8080";
	private static final String PATH = "/hello/protected";
	
	private Client client;
	
	@Before
	public void setUp(){
		client = ClientBuilder.newClient();
	}
	
	@After
	public void tearDown(){
		client.close();
	}
	@Test
	public void testNegativePath() {
		Response response = client.target(TARGET).path(PATH).request().get();
		assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
		
	}

}
