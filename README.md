Cake Manager:

This service is used to list all of the cakes availalbe in system, add new cake to the system and download the cake list from system.

**Build:**

Clone this repository to the local

Navigate to the project folder from terminal and run following command:

"mvn clean install".

To run a server locally execute the following command:

`mvn spring-boot:run`

**Test as per requirement:**

Provide basic authentication details as below to access below endpoints
Username : admin
Password : CakeManager

To add new cake use POST request endpoint : http://localhost:8282/
Ex body:

{
        "title": "lemon cheese cake",
        "desc": "A lemon cake made of cheese",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}

To list all the cakes use GET request endpoint: http://localhost:8282/

To add new cake use POST request endpoint : http://localhost:8282/cakes
Ex body:

{
        "title": "lemon cheese cake",
        "desc": "A lemon cake made of cheese",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}

To download cake list in JSON file format use GET request endpoint http://localhost:8282/cakes


To store details in database here I’m using temporary database as H2 database. The details are available in application.property file in resources.


**Added more features:**

Pre-requirement:
Create docker hub account.
Provide docker hub log in details in Github secrets from repository Settings.

Provide details as below
DOCKER_USERNAME: your username
DOCKER_PASSWORD: your password

CI/CD:
The CI/CD details are available in maven.yml file. Here I’m using Github Actions for CI/CD pipeline.
When make any changes in the Github master branch the CI/CD runs automatically.

And docker image is generated to run from docker container.

To run project from docker container use below commands:

Start Docker desktop
Run following docker command in terminal to pull docker container "docker pull udayalekkala/cake-manager:latest".
Verify docker images by runing "Docker images" command in terminal. Then run "docker run udayalekkala/cake-manager"
