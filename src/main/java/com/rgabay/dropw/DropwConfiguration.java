package com.rgabay.dropw;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.*;

public class DropwConfiguration extends Configuration {

	@NotEmpty
	private String password;
	private String url;
	private String type;
	private String index;

	@NotNull
	@Valid
	private DataSourceFactory dataSourceFactory = new DataSourceFactory();
	
	@JsonProperty
	public String getPassword() {
		return password;
	}
	
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}
	
	@JsonProperty
	public String getUrl() {
		return url;
	}
	
	@JsonProperty
	public String getType() {
		return type;
	}
	
	@JsonProperty
	public String getIndex() {
		return index;
	}
}
