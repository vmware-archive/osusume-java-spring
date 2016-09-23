# osusume-java-spring
Osusume back-end built in Java Spring Boot.

## Configuration
A environment variable for the database URL needs to be configured for PostgreSQL, such as:

`OSUSUME_DATABASE_URL=jdbc:postgresql://localhost/osusume-dev`

For accessing the Google Places API, an API key is necessary (see https://console.developers.google.com/):

`GOOGLE_PLACES_KEY=<Google Places API Key>`

For accessing to AWS S3 bucket:

`AWS_ACCESS_KEY=<AWS Access Key>`

`AWS_SECRET_KEY=<AWS Secret Access Key>`

`AWS_S3_BUCKET_NAME=<S3 Bucket Name>`

## Makefile
Please use the makefile which contains a few useful commands:

**refreshdb** Creates database. Run this to setup the DB for the first time or to wipe out all data.

**migrate** Refreshes the database scheme. Run this to apply migrations to the database.

**loadsampledata** Loads the base set of sample data that can be used with the application.

**tests** Runs all tests and then loads all sample data. ***Please note that this will remove all existing data and re-load the sample data!***

## Setting up the Project
1.) Set environment variables:

  * OSUSUME_DATABASE_URL=`OSUSUME_DATABASE_URL=jdbc:postgresql://localhost/osusume-dev` #(reset to 'osusume-test' for tests)
  * OSUSUME_DATABASE_USER
  * OSUSUME_DATABASE_PASSWORD

2.) Set up the development database:
```
# Create db
make refreshdb

# Run db migrations
make migrate

# Load sample data
make loadsampledata
```

3.) Set up the test database. You will need to change the OSUSUME_DATABASE_URL to point at the test instance (i.e. osusume-test)
```
# Run db migrations
make test-migrate

# Load sample data
make test-loadsampledata
```

4.) Build application. Tests will need to run and pass in order to successfully build the application.
```
make tests
```

5.) Change OSUSUME_DATABASE_URL to point back at the dev instance (i.e. osusume-dev). You can now start the application.
```
make start
```

## Starting the Server
The project is broken down into an "application" and a "components" directory, therefore to start the server please locate the jar under the 'applications' directory:

`java -jar build/libs/osusume-java-spring-0.0.1-SNAPSHOT.jar`

## Migrations
To run migrations on Cloud Foundry:

`OSUSUME_DATABASE_URL=<jdbc-url-cf-sql> OSUSUME_DATABASE_USER=<jdbc-user-cf-sql> OSUSUME_DATABASE_PASSWORD=<jdbc-password-cf-sql> ./gradlew flywayMigrate`

`flywayInfo` can be used instead to show which migrations have been run.

To run migrations on local:

`OSUSUME_DATABASE_URL=jdbc:postgresql://localhost/osusume-test; make refreshdb; ./gradlew flywayMigrate`

## Sample curl statements

Create a session (logon) and receive a token (please pass in a valid username and password):

`curl -i -X POST http://localhost:8080/session -H "content-type: application/json" -d '{"email":"name","password":"password"}'`

Retrieve a list of restaurants (please replace `<token>` with a valid token):

`curl http://localhost:8080/restaurants -H "Authorization: Bearer <token>" | jq .`

Retrieve details for a specific restaurant (please replace `<token>` with a valid token):

`curl http://localhost:8080/restaurants/14 -H "Authorization: Bearer <token>" | jq .`
