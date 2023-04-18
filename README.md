
# Birthday Reminder API  
An API to send messages about your friend's birthdays  
  
## Pre-requisites  
1. Install JDK 17: My suggestion is [Eclipse Temurin website](https://adoptium.net/temurin/releases/) to download and install JDK 17. Don't forget to set the enviroment variables  
2. Install Maven 3.8.x: Follow the [Official Apache Maven website](https://maven.apache.org/install.html) instructions to download and install Maven 3.8.x.
  
## Build and Run the Application  
After setting up the prerequisites, follow the steps below to build and run the application:  
  
1. Open the terminal/command prompt and navigate to the root directory of the project.  
2. Compile and package the application by running the following command:  
  
`mvn clean package`  
  
3. After the build is successful, you can add friends in two ways:  
- By adding in the friends.txt in the format below:  
last_name,first_name,date_of_birth,email,number  
E.g.:  
Jobs,Steve,1955-04-17,steve.jobs@foobar.com,555-123-1111  
  
- By adding in the SQLite database:  
In this case you must create a connection with friends database file, located in the project root and run a query to insert data. For example:  
`INSERT INTO friends (last_name, first_name, date_of_birth, email, phone_number)  
VALUES ('jobs', 'steve', '1955-04-17', 'steve.jobs@foobar.com', '555-123-1111');`  
  
- **Note**: If you want to see the outputs with the messages, you can change a friend's birthday to the current day or insert a friend and put his birthday in your current day.  
Look at this example, inserting a friend in the friends.txt file:  
Jobs,Steve,1955-04-17,steve.jobs@foobar.com,555-123-1111  
  - [ ] Now run the command bellow:  
`java -jar target/birthday-reminder-1.0.jar`  
  
The output will show two messages, the first is an email message and the second is a SMS message:  

>Recipient: david.johnson@foobar.com  
Subject: Birthday Reminder  
Message: Dear David,  
Today is steve jobs's birthday.  
Don't forget to send him a message!  
"---------------------------------------------------"
>Recipient: 555-123-5432  
Subject: Birthday Reminder  
Message: Dear David,  
Today is steve jobs's birthday.  
Don't forget to send him a message!  
"---------------------------------------------------"
Recipient: steve.jobs@foobar.com  
Subject: Happy birthday!  
Message: Happy birthday, dear steve!  
"---------------------------------------------------"
Recipient: 555-123-1111  
Subject: Happy birthday!  
Message: Happy birthday, dear steve!  
"---------------------------------------------------"
  
## Running Tests  
To run only the tests, use the following command in the terminal/command prompt from the root directory of the project:  
  
`mvn test`  
  
This will execute all the tests in the project and provide you with a summary of the test results.
