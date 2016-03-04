# Basic DropWizard App
## To run : 
1. clone the repo
2. run mvn package against the master pom
3. java -jar target/dropw-1.0-SNAPSHOT.jar server config.yml
## Features/Supported Use Cases :
1. Basic HTTP GET - static response : ```curl -v http://localhost:8088/hello```
2. HTTP GET - static response with Basic Auth : ```curl -v http://localhost:8088/hello/protected -u user:pwd``` (password value "pwd" is driven by the "password" key in the config.yml file, providing incorrect password resluts in HTTP 401)
3. HTTP GET - response driven by the MySQL-stored data : ```curl -v http://localhost:8088/people```
4. HTTP GET - response driven by the Elasticsearch-stored data : ```curl -v http://localhost:8088/elasticppl```
5. HTTPS GET - static response : ```curl -k https://localhost:8443/hello```

##  Config notes:
1. RDBMS - the app will look for a local instance of MySQL on port 3306. The database and user credentials it's looking for are driven by the values in config.yml. DDL and sample data seed script are located in ```/src/main/resources```.
2. Elasticsearch - the app is using Jest API to communicate with the Elasticsearch endpoint. Config parameters for the endpoint are provided in the config.yml file. To populate some sample data post a couple of records with "fname" and "lname" values defined to http://localhost:9200/hr/people, for example : 
```curl -v -XPOST -d'{"fname" : "Darth", "lname" : "Vader"}' localhost:9200/hr/people```
3. SSL - using a self-signed cert, import the cert into your local keystore : ```keytool -import -trustcacerts -alias selfsigned -file selfsigned.crt -keystore cacerts``` (under the ```jre/lib/security``` directory)
## TODOs :
- add a PUT support to add data to the DB and Elasticsearch
- add basic UI 
- add a meaningful healthcheck
- add a CA issued cert (https://letsencrypt.org/)
- add a Postman collection to set up the data/run integration tests
