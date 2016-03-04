package com.rgabay.dropw;


import com.rgabay.dropw.auth.HelloAuthenticator;
import com.rgabay.dropw.core.User;
import com.rgabay.dropw.health.DwHealthCheck;
import com.rgabay.dropw.resources.ElasticPplResource;
import com.rgabay.dropw.resources.HelloResource;
import com.rgabay.dropw.resources.PersonResource;

import io.dropwizard.Application;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class DropwApplication extends Application<DropwConfiguration> {

	 private final HibernateBundle<DropwConfiguration> hibernateBundle =
	            new HibernateBundle<DropwConfiguration>(com.rgabay.dropw.core.Person.class) {
	                @Override
	                public DataSourceFactory getDataSourceFactory(DropwConfiguration configuration) {
	                    return configuration.getDataSourceFactory();
	                }
	            };
	            
    public static void main(final String[] args) throws Exception {
        new DropwApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropBookmarks";
    }

    @Override
    public void initialize(final Bootstrap<DropwConfiguration> bootstrap) {
    	 bootstrap.setConfigurationSourceProvider(
                 new SubstitutingSourceProvider(
                         bootstrap.getConfigurationSourceProvider(),
                         new EnvironmentVariableSubstitutor(false)
                 )
         );
         bootstrap.addBundle(hibernateBundle);  
    }

    @Override
    public void run(final DropwConfiguration configuration,
                    final Environment environment) {
    	
    				final DwHealthCheck healthCheck = new DwHealthCheck();
    				environment.healthChecks().register("none", healthCheck);
    	
    				final com.rgabay.dropw.db.PersonDAO dao = new com.rgabay.dropw.db.PersonDAO(hibernateBundle.getSessionFactory());
       					
    				environment.jersey().register(new HelloResource());
    				environment.jersey().register(new PersonResource(dao));
    				environment.jersey().register(new ElasticPplResource(
    						configuration.getUrl(),
    						configuration.getType(),
    						configuration.getIndex()
    						));
    				
    				environment.jersey().register(new AuthDynamicFeature(
    			            new BasicCredentialAuthFilter.Builder<User>()
    			                .setAuthenticator(new HelloAuthenticator(configuration.getPassword()))
    			               // TODO add authZ .setAuthorizer(new ExampleAuthorizer())
    			                .setRealm("SECRET REALM")
    			                .buildAuthFilter()));
    				
    			    //environment.jersey().register(RolesAllowedDynamicFeature.class); // TODO not needed until I add authZ
    			    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    				
    }

}
