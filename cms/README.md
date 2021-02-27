CMS - College Management System
===============================
 
# 0. Database Support Example Code

You may find and example of adding database support to this project in the brach **database**.

# 1. Overview

This is an eclipse project. You can open it with eclipse. It includes also a build.gradle file and you can (and should) execute the building tasks from a terminal or console using gradle (the project uses the gradle wrapper, so you can use gradlew instead of gradle). The single exception is for Debug that requires the use of Eclipse.

To use this project you should download it from bitbucket (in Bitbucket use option "Downloads"). **Do not clone it!**

You should then copy the downloaded files into a folder inside your repository. **Commit these new files into your own repository.**

To use this project first create an eclipse workspace **outside** of the folder that contains your repository.

You should open this project by using the option "File/Import..."/"Existing Projects into Workspace" and select as root directory the root of your repository. Select the project to import (i..e, CMS). **Do NOT check "Copy projects into workspace"**

# 2. Important Remarks

## 2.1 GWT SDK Settings in Eclipse

It may be necessary to configure the location of the GWT SDK in the properties of the eclipse project. With the right mouse button in the cms project select "Properties". Then "GWT/General Settings". Verify if the GWT SDK settings points to your location (and version should be 2.8.1).

## 2.2 Gradle Properties 

The file `gradle.properties` contains the location of the GWT SDK and the location of Ant. You should update this file to match the location of these tools in your system (the Ant part should be optional).

# 3. Eclipse Requirements

Before importing the project you should be sure to provide the following requirements.

## 3.1 GWT Eclipse Plugin

Install the GWT Eclipse Plugin by using "Help/Eclipse Markeplace..." and serch for and install "GWT Ecplise Plugin".

## 3.2 GWT SDK

You should download and install the GWT SDK into your computer (http://www.gwtproject.org). 

# 4. How to Run

Execute in a terminal `gradle gwtRun`

The server starts and the screen displays something like this:

	:compileJava UP-TO-DATE
	:processResources UP-TO-DATE
	:classes UP-TO-DATE
	:gwtCompile UP-TO-DATE
	:war UP-TO-DATE
	:gwtRun
	Listening for transport dt_socket at address: 8000
	2017-10-06 15:55:47.055:INFO::main: Logging initialized @232ms
	2017-10-06 15:55:47.066:INFO:oejr.Runner:main: Runner
	2017-10-06 15:55:47.271:INFO:oejs.Server:main: jetty-9.2.7.v20150116
	2017-10-06 15:55:48.992:INFO:oejsh.ContextHandler:main: Started o.e.j.w.WebAppContext@68ceda24...
	2017-10-06 15:55:48.993:WARN:oejsh.RequestLogHandler:main: !RequestLog
	2017-10-06 15:55:49.033:INFO:oejs.ServerConnector:main: Started ServerConnector@177630fe{HTTP/1.1}{0.0.0.0:8080}
	2017-10-06 15:55:49.036:INFO:oejs.Server:main: Started @2218ms
	> Building 83% > :gwtRun

Now you can open Chrome and use the following url to access the application: [http://localhost:8080/Showcase.html](http://localhost:8080/Showcase.html)	

To stop the server open another terminal/console and type `gradle gwtStop`

# 5. How to Debug the Server Code

## 5.1 Running the Application from the command line

To debug the server code you can run the application as described earlier.

After that, in Eclipse, you can create a new configuration of type "Remote Java Application". Enter the following data:
- Project: CMS (select the CMS project)
- Server: localhost
- Port: 8000

In the "Source" tab also select the CMS project.

**Notes:** 

- You must run the server application before running the new run configuration of eclipse. This is because the debug port 8000 must be open by the server before starting the debug in Eclipse.

- Also note that there is a "debugSuspend" property in gradle (default value is "false") that you can set so that the server waits for the start of the Eclipse debug session.

## 5.2 Running the Application from Eclipse

You can also set up a "GWT Development Mode (Dev Mode)" configuration in Eclipse. If you do that, eclipse will launch the GWT Application in Debug Mode. In this case it is not necesssary to run the Application from the command line.

Setup the "GWT Development Mode (Dev Mode)" in this way:

- In tab "Main"

	1. Select the project CMS
	2. As main classe enter: "com.google.gwt.dev.DevMode"

- In tab "Server"

	1. Check "Run built-in server"
	2. Port: 8888

- In tab "GWT"

	1. Check "Super Development Mode"
	2. In avalable modules select "pt.isep.cms.Showcase"

- In tab "Arguments"

	1. In program arguments enter:
	
	-remoteUI "${gwt_remote_ui_server_port}:${unique_id}" -logLevel INFO -codeServerPort 9997 -war /workspaces/odsoft/2017/odsoft-edom-2017/cms/src/main/webapp -port 8888 pt.isep.cms.Showcase

Note: You should replace "/workspaces/odsoft/2017/odsoft-edom-2017/cms/src/main/webapp" by your own path.

- In the tab "classpath" add an entry for the sources of the project, i.e., /cms/src/main/java

# 6. How to Debug the Client Code

The simplest way to debug the client code is to use a "Launch Chrome" configuration in Eclipse.

Create a "Launch Chrome" configuration:

- In the "Main" tab:

	1. In URL enter "http://127.0.0.1:8888/Showcase.html"
	2. In Project select the "cms" project
	
**Notes:** 

- Remember that the server must be running! You should run the server in debug mode using a running configuration in Eclipse or the `gwtDev` task in gradle.	

- Confirm that the port number is correct (either 8888 or other you have setup)

# 7. Using the Command Line with Gradle

To get a list of available GWT tasks simple type **gradle tasks** in a terminal or console.

Some common commands:

**gradle build**: builds the application (a **war** file is produced in **build/libs**)

**gradle gwtRun**: the jetty web serever is started (using port 8080) to serve the application. You can open the application in a browser with the following url <http://127.0.0.1:8080/Contacts.html>

**gradle gwtDev**: gwt starts in development mode. You should be able to update the code of the application and the changes should be automatically visible. You can open the application in a browser with the following url <http://127.0.0.1:8080/Contacts.html>
 
# 8. Credits

This project is based on examples from the GWT project (http://www.gwtproject.org), specially:

- http://samples.gwtproject.org/samples/Showcase/Showcase.html

- http://www.gwtproject.org/articles/mvp-architecture.html



