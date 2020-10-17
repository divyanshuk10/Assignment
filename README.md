#Building the project

- If gradle sync failed, GOTO FILE >> gradle-wrapper.properties
  Edit and replace the distributionUrl with this with the below line

  distributionUrl=https\://services.gradle.org/distributions/gradle-6.1.1-all.zip

- To Run the app please GOTO Build Variants and change it to debug or release. The app will run now

- Used Dagger 2 for dependency injection
  - Network module having retrofit and okhttp
  - Database module having Room database
  - Glide module for image caching

- Used repository pattern and managing local and remote repositories seperately ( Following Seperation of Concern)

- Implemented Stetho for debugging Database

- Implemented RXJava for asynchronous tasks ie - networking, database loading etc.

- Managed Event state emmittion ie - DISCONNECTED, PROGRESS, SUCCESS, ERROR, EMPTY_DATA, LIMIT_REACHED

- Used MVVM  architecture + Repository pattern for development of app

