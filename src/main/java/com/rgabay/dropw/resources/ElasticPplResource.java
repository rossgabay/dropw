package com.rgabay.dropw.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgabay.dropw.api.PersonDomain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/elasticppl")
public class ElasticPplResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticPplResource.class);
	
	//private static final String ELASTIC_URL = "http://localhost:9200";
	//private static final String TYPE_NAME = "people";
    //private static final String INDEX_NAME = "hr";
	
	private final String ELASTIC_URL;
	private final String TYPE_NAME;
    private String INDEX_NAME;
	
	public ElasticPplResource(String url, String type, String index) {
		super();
		ELASTIC_URL = url;
		TYPE_NAME = type;
		INDEX_NAME = index;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting(){	
		
		String response = "";
		try {
	            // Get Jest client
	            HttpClientConfig clientConfig = new HttpClientConfig.Builder(ELASTIC_URL).multiThreaded(true).build();
	            JestClientFactory factory = new JestClientFactory();
	            factory.setHttpClientConfig(clientConfig);
	            JestClient jestClient = factory.getObject();
	            
	            try {
	            	response = readAllData(jestClient);
	            } finally {
	                jestClient.shutdownClient();
	            }

	        } catch (Exception e) {
	            LOGGER.error(e.getMessage());
	            response = "server error";
	        }
				
		return response;
	}

	private String readAllData(final JestClient jestClient)
            throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX_NAME).addType(TYPE_NAME).build();
               
        JestResult result = jestClient.execute(search);
        List<PersonDomain> ppl = result.getSourceAsObjectList(PersonDomain.class);
        
        ObjectMapper mapper = new ObjectMapper();
        StringBuffer sb = new StringBuffer();
        for (PersonDomain pd : ppl) {
        	String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pd);
        	sb.append(jsonString);
            LOGGER.info(jsonString);
        }
        
        return sb.toString();
    }
}
