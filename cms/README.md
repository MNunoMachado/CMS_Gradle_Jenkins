CMS - College Management System
===============================


 Class Assignment - Part 2:   
 ===========================
 
  
 Goal 1) Pipeline Design
 ------
*For every component, a sketch for each Pipeline, along with a description must be provided.*

 After creating an issue for this component of the exercise I have started with the first goal.  
 
 I have added a stage for each task of the exercise (plus some extra stages):
  - Repository Checkout;  
  - War file;  
  - Javadoc;  
  - Unit Tests;  
  - Integration Tests;  
  - Mutation Tests;  
  - System Test;  
  - UI Acceptance Manual Tests;  
  - Continuous Integration Feedback.    
 
  ![ pipeline sketch ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/4664c4755ac8b497818834fbf4c052448fa743e0/odsoft/exercise2/component3/cms/images/ima12.png)      

 
The Pipeline starts with the **Repository Checkout** as usual. Then the second stage is where I Build the project and archive the war file.  
The third stage I start the tomcat server (this will be explained with more detail in this report). The 4º stage is to create and publish the **Javadoc**.  
Then the 5º, 6º and 7º stages are to run the Tests (unit, integration and mutation) and generate reports and publish them (similar to what was done in CA1).  
The next stage is to deploy the war file to the tomcat instance and execute a smoke test.
The next stage is to run one **Acceptance Manual Test** by sending and email to the user and the Pipeline waits for the user input. 
If all the previous tasks were successful add a tag to the repository for the **Continuous Integration Feedback** with a success message.   
To close the Pipeline I have added a stage to close the tomcat server.  
If at any stage, there is an error, I add a tag to the repository for the **Continuous Integration Feedback** with a fail message.       

Next it is presented the main steps (with items to be published and archived)  for the Pipeline. In blue it is shown the success path and in red the failure path. Any error that occurs leads to the failure tag.  


 
  ![ pipeline sketch ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/60378085cb7f61c463b72fe028fa3a60ef85b7b8/odsoft/exercise2/component3/cms/images/ima13.png)      

 
 
  Goal 2 & 3  & ) Pipeline Tasks & Pipeline Implementation & Report
  ------
   
*Component 3 Configure a Jenkins Pipeline using a Scripted Jenkinsfile, performing a sequential build;*  

 The current Pipeline is based on the Jenkinsfile (Jenkins Scripted Pipelines). 
A lot of the previous tasks were already being done in Jenkins using the **freestyle project** so it was just a matter of adapting the code to use a 
scripted Pipeline (in a Jenkinsfile).  

To use the Jenkinsfile, I had to add the path after choosing the credentials, as it is shown next.   
  
  ![ Jenkins ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/ab0224757a5f6adce15286bc706ec3cfa340d11e/odsoft/exercise2/component3/cms/images/ima8.png)      
   
Before creating the Jenkinsfile I had to remove some dependencies created for the previous Class Assignment. I have removed the dependency between the integration
tests and the unit tests.  

      `  test.finalizedBy(integrationTest)   `


I have used the functionality **Snippet Generator**, in Jenkins, to help me with the **Pipeline Sintax**.    

   ![ Snippet Generator ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/51458f5198b4cd406d783daad27ade6bca67dd0e/odsoft/exercise2/component3/cms/images/ima1.png)      
  
Before starting the Jenkins file I have created a function and used the isUnix command.   
This function checks if the Pipeline is running on a Unix OS and if it is it returns :    
    ` sh command `  
if it is not, it returns:  
    ` bat command`  
So, for example, to run the **gradlew build** I have used the **runCommand('gradlew build'** which can be translated, since I am running in windows, in:  
    ` bat gradlew build -x test`

With this solution I have assured that the Pipeline must be able to run on any of the student's machines.  
Throughout  this report I have added some **echo** commands in order to help me read the results..  
The first task was to **checkout** the git repository in order to access our repository. In this process I used the same credentials from the CA1.  

For the second task I run the **gradlew clean build -x test** command for building the war file, as it was done in the CA1. I used again  the gradlew to avoid version problems. 
The **-x test** is to exclude running the tests while doing the build. Since there are stages to run tests, later, I have excluded the tests from this stage.  
The **clean** command assures that there are not previous files.  
A have used the **dir()** command to be able to access the folders needed with a cleaner code. 
At first I had add the code  `cd odsoft/exercise2/component3/cms && gradlew clean build -x test` but then I have changed this and used the  **dir command** like this : `   dir('odsoft/exercise2/component3/cms') `.    
This **dir** command was used many times in the Jenkinsfile.  

I have decided to enter an extra stage in this phase, to turn on the **TOMCAT** server. In a professional environment the server is already "ON" so this stage it is not needed. 
But since we are using our local machines, and to avoid the need to always before running the Pipeline make sure that the 
tomcat is running. And if it is not running to go to the bin folder inside tomcat and run the  **startup** command. I have decided to create this extra stage to startup tomcat.
I have created an environment variable with the base location of the tomcat ( **TOMCAT_HOME** - "C:\tomcat\apache-tomcat-9.0.40" ). 
This variable was created so I could run this stage in any other computer (just by adding a similar environment variable).  
Since the file that must be run, to start the tomcat changes based on the OS system.
I have added an **if else** to check if the used **isUnix** or not. If it is unix it runs the **startup.sh** file if it is not it runs the **startup.bat** file.  

 ``` 
        stage('Tomcat Server Init') {
            echo 'Start Tomcat ...'
            dir("${TOMCAT_HOME}/bin") {
                if (isUnix()) {
                    sh 'startup.sh'
                } else {
                    bat 'startup.bat'
                }
            }
        }
 ```

The third task was to generate the javadoc. Again, using the **gradlew javadoc** command.   
This javadoc task had 2 dependencies in the build.gradle file : the **renderPlantUml** and the **copyDocsToJavadoc** (this was done in the CA1).  

    ```
    javadoc.dependsOn renderPlantUml
    javadoc.finalizedBy(copyDocsToJavadoc)
    ```
In the JenkinsFile I have used  the  option :  
   ```
            dir('odsoft/exercise2/component3/cms') {
                runCommand(' gradlew javadoc')
                javadoc javadocDir: 'build/docs/javadoc', keepAll: false
            }
 ```
   
So after running the gradle task the results are published.    

For the next three tasks (4º , 5º and 6º) I had to run the  **unit**, **integration** and **mutation** tests.  
The only part that was new in this CA was that to publish the results of the tests I used some specific commands of Jenkins.  
  
     ```
        stage('Unit Tests') {
            echo 'Unit Tests...'
            dir('odsoft/exercise2/component3/cms') {
                runCommand('gradlew test')
                junit 'build/test-results/test/*.xml'
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/test', reportFiles: 'index.html', reportName: 'HTML Report - Unit Tests', reportTitles: ''])
            }
        }

        stage('Integration Tests') {
            echo 'Integration Tests...'
            dir('odsoft/exercise2/component3/cms') {
                runCommand('gradlew integrationTest')
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/integrationTest', reportFiles: 'index.html', reportName: 'HTML Report - Integration Tests', reportTitles: ''])
                jacoco execPattern: '**/.exec'
            }
        }

        stage('Mutation Tests') {
            echo 'Mutaion Tests...'
            dir('odsoft/exercise2/component3/cms') {
                runCommand('gradlew pitest')
                publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/reports/pitest', reportFiles: 'index.html', reportName: 'HTML Report - Mutation Tests', reportTitles: ''])
            }
        }
      ```
With these commands I generate the unit, integration and mutation tests coverage reports and publish these reports and the test reports. 
If the I had not added the **clean** command in the second stage I may have a problem with the command **junit**.. This command is only executed if the report on the unit tests changes. 
If the unit tests are the same and are run, and the report is the same I would have an error. But with the **clean** command the previous report is deleted, so there is no error.   
I have moved the **jacoco** command to the stage **Integration Tests** so this report will aggregate unit and integration tests.  

For the next task I had to  deploy the application to a pre-configured Tomcat Server instance.
I had to install Tomcat (by downloading the zip distribution) and configured it to run on the port 8081 and added a new user (as instructed in the 
document **Class Assignment - Part 2: Support**) in the tomcat-users.xml .  
Tomcat is an open-source Java Servlet Container.   
 
At first, I have tested Tomcat locally to better understand how it works.   
I have tested locally the commands **startup** and **shutdown**. 
In order to run an application on the Tomcat we have to add the war file to the **webapps** folder (C:/tomcat/apache-tomcat-9.0.40/webapps**).  
Then in the **bin** folder of Tomcat I ran the command **startup.bat**. 
After the command **startup** I have entered the url **http://localhost:8081/cms-1.0/#!CwContacts/** and tried to use the application by adding a new contact.After the command **startup** I have entered the url **http://localhost:8081/cms-1.0/#!CwContacts/** and tried to use the application by adding a new contact.  
The application was working as expected and I was able to had a new Contact.
I have also tested, using curl, to check if the base url of the application is responsive after deployment in the Tomcat Server, 
ensuring that the application is properly deployed.  

In the Jenkinsfile, this stage started with the TOMCAT already running (the 3º stage starts tomcat).  
First, I had to install the Plugin **Deploy to container Plugin**. This plugin allows to deploy a war to a container after a successful build.  
Then I had to create new credentials, in Jenkins, so I could access Tomcat user that I created previously. 
Then I use the **deploy** command to deploy our application to the tomcat server (this line of code was obtain with the help of the **Snippet Generator**):   
`  deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://localhost:8081')], contextPath: 'CMS', onFailure: false, war: 'odsoft/exercise2/component3/cms/build/libs/*.war'  `  
I am deploying the code to the url **http://localhost:8081** where tomcat (version 9) is running, I define the path to the war file and I define the contextPath **CMS** where I can get to the application, in this case 
` http://localhost:8081/CMS/  `  

I have placed the **smoke test** (**curl**) inside a **retry(3)** command. With the **--fail** I assure that the STATUS CODE 404 is considered as and error.  
With the **retry** command I want to avoid executing the **curl** and failing the Pipeline while the Contacts application is getting deployed in the tomcat instance. 
So with this command I try a total of 3 times to execute the curl and only if the 3 times fail will the Pipeline fail too.  

    ```
            retry(3) {
                echo 'Executing smoke test ...'
                runCommand( 'curl --fail http://localhost:8081/cms-1.0/#!C1wContacts' )
            }
    ```
 
For the 8º tasks **UI Acceptance Manual Tests** It was requested that a user should be notified by email of the successful execution of all the 
previous tests and be asked to perform a manual test.      
To send and email I have created a gmail account. I have changed this definition on the gmail so it would be easier to access the email with Jenkins (allowed access to less secure apps). 


   ![ gmail ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/51458f5198b4cd406d783daad27ade6bca67dd0e/odsoft/exercise2/component3/cms/images/ima2.png)      

 
In the Jenkins configurations I have added the **E-mail Notification** option

   ![ email Jenkins ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/51458f5198b4cd406d783daad27ade6bca67dd0e/odsoft/exercise2/component3/cms/images/ima3.png)        

To find out which SMTP port to used I have checked the information from  the next website:  
  ` https://www.arclab.com/en/kb/email/list-of-smtp-and-pop3-servers-mailserver-list.html `
 
With everything configured and with the help of the **Snippet Generator** 

I have used the command **mail**.  

    ```
 	mail bcc: '', body: "Jenkins is waiting for your input. Please address the issue in ${BUILD_URL} !!", cc: '', from: '', replyTo: '', subject: 'ODSOFT EMAIL', to: '1181719@isep.ipp.pt'

    ````
With this line I manage to send and email, and the email has a url to the current build, as it is shown in the next image.  

   ![ email ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/ab0224757a5f6adce15286bc706ec3cfa340d11e/odsoft/exercise2/component3/cms/images/ima9.png)        

Then in order to make the Pipeline stop and wait for the user input I have used the command **input**. 

	input message: 'UI Acceptance Manual Tests', parameters: [booleanParam(defaultValue: true, description: '''should we proceed to the next stage?
						''', name: '' )]

 With this command the Pipeline stops and waits for the user to click on the **Proceed** button. If the **Abort** button is clicked the Pipeline is aborted.  
To solve the problem that the user forgot (although it receives and email) that the Pipeline needed the manual input, I have added a timeout for this task.  
So I added a time out of 48 hours, and if the user does not choose to proceed or abort by then the Pipeline if aborted.  

`	timeout(time: 48, unit: 'HOURS')  `
  
   ![ email code ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/51458f5198b4cd406d783daad27ade6bca67dd0e/odsoft/exercise2/component3/cms/images/ima5.png)        
  
  
To summarize in this task the user receives and email with the message "Jenkins is waiting for your input. Please address the issue ( + URL to the build) !!" 
and the Pipeline is stopped and waits for the user input.  

The final task was **Continuous Integration Feedback**. In this task, it was requested that a tag was pushed to the repository with the
build number and the status (Success or Failed).    
I have tried the **post** command as it was presented in the classes for the declarative Pipeline but it didn't work. So I have used a try catch block.  
At the end of the Pipeline I create a tag and push it to the repository with the code :   

    ```
            runCommand("git tag -a Ex2Comp3-Build#${BUILD_NUMBER}-${currentBuild.currentResult} -m 'BuildSuccess'")
            runCommand(' git push --tags')
    ``` 

Using the "" the Jenkinsfiles interpolates the variables :   
- ${BUILD_NUMBER} -The current build number, such as "153".  
- ${currentBuild.currentResult} - to the result of the build process. Typically SUCCESS, UNSTABLE, or FAILURE. Will never be null.  

In the catch block I have added the code :   

    ```
        currentBuild.result = 'FAIL'
        runCommand("git tag -a Ex2Comp3-Build#${BUILD_NUMBER}-${currentBuild.currentResult} -m 'BuildFailed'")
        runCommand(' git push  --tags')
    ```   
The first line sets the result of the build as FAIL.  
This way If the Pipeline runs as expected a **success tag** if generated, if any exception occurs during the process the **failed tag** is created.  
I could have added the tag stage in the **finally** block since the code is done in a generic way, and I only had to change the message ("-m" ) in the tag and used again the "${currentBuild.currentResult}".  

   ![ email code ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/51458f5198b4cd406d783daad27ade6bca67dd0e/odsoft/exercise2/component3/cms/images/ima6.png)        
 
 
In the try catch block I have added a **finally**  block too, so I could do the final task - **Shutdown the Tomcat** instance that was created.



Final result 
  ---
    
   ![ pipeline ](https://bitbucket.org/mei-isep/odsoft-20-21-mbs-g006/raw/4664c4755ac8b497818834fbf4c052448fa743e0/odsoft/exercise2/component3/cms/images/ima11.png)        


 

Alternatives 
  ---
  
There are many ways to execute this Pipeline.  
**1) ** I could have not added the tasks to **startup** and **shutdown** tomcat and have it running in my machine.   
 
**2)** To deploy the war file I could have used gradle. Like this:   
 
Create a new task in  **build.gradle** :  
   ```
  task newDeployToTomcat(type: Copy) {
  
      delete "${tomcat_home}/webapps/cms-1.0"
      delete "${tomcat_home}/webapps/cms-1.0.war"
      from 'build/libs'
      into "${tomcat_home}/webapps"
      include "*.war"
  }
  ```   
  
In this task I delete the previous CMS - College Management System  files in the tomcat webapp and then while the tomcat server is already
running I copy the new war file (output from the second stage - **Build and Archive** ) into the **webapp** folder on tomcat.    
When the new war file is copied to the webapp folder, the tomcat server deploys the CMS app.
For all this I used the environment variable that was created ( **TOMCAT_HOME** - "C:\tomcat\apache-tomcat-9.0.40" ).






