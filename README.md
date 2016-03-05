# Basic Dropwizard App
## To run : 
1. clone the repo
2. run ```mvn package``` against the master pom
3. make sure configs are set to reflect your local setup
4. ```java -jar target/dropw-1.0-SNAPSHOT.jar server config.yml```

## Features/Supported Use Cases :
1. Basic HTTP GET - static response : ```curl -v http://localhost:8088/hello```
2. HTTP GET - static response with Basic Auth : ```curl -v http://localhost:8088/hello/protected -u user:pwd``` (password value "pwd" is driven by the "password" key in the config.yml file, providing incorrect password resluts in HTTP 401)
3. HTTP GET - response driven by the MySQL-stored data : ```curl -v http://localhost:8088/people```
4. HTTP GET - response driven by the Elasticsearch-stored data : ```curl -v http://localhost:8088/elasticppl```
5. HTTPS GET - static response : ```curl -k https://localhost:8443/hello```
6. UI support -  there's a CORS filter registered with Dropwizard in the App to suppress the Same Origin Policy. A simple HTML page in the ```drop_ui``` directory demonstrates usage of AngularJS/Ajax and basic Bootstrap styling, making a call to the /elasticppl endpoint exposed by the app.

##  Config notes:
1. RDBMS - the app will look for a local instance of MySQL on port 3306. The database and user credentials it's looking for are driven by the values in config.yml. DDL and sample data seed script are located in ```/src/main/resources```.
2. Elasticsearch - the app is using Jest API (https://github.com/searchbox-io/Jest/tree/master/jest) to communicate with the Elasticsearch endpoint. Config parameters for the endpoint are provided in the config.yml file (I'm running it locally on the default port 9200). To populate some sample data post a couple of records with "fname" and "lname" values defined to http://localhost:9200/hr/people, for example : 
```curl -v -XPOST -d'{"fname" : "Darth", "lname" : "Vader"}' localhost:9200/hr/people```
3. SSL - using a self-signed cert, import the cert into your local keystore : ```keytool -import -trustcacerts -alias selfsigned -file selfsigned.crt -keystore cacerts``` (under the ```jre/lib/security``` directory)  


##  TODOs :
- add HTTP PUT support to support adding data to the DB and Elasticsearch
- expand basic UI 
- add a meaningful healthcheck
- add a CA issued cert (https://letsencrypt.org/)
- add a Postman collection to set up the data/run integration tests
- add OAuth2 integration
- create a branch with H2 DB to simplify deployment/configs
