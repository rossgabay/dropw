package com.rgabay.dropw.health;

import com.codahale.metrics.health.HealthCheck;

public class DwHealthCheck extends HealthCheck{
	
	 @Override
	    protected Result check() throws Exception {
		 //TODO: come up with a meaningful health check
	        return Result.healthy();
	    }

}
