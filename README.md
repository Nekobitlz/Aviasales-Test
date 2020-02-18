# Aviasales Test Task

[LATEST VERSION APK FILE](https://github.com/Nekobitlz/Aviasales-Test/blob/master/apks/Aviasales-1.0.apk)

The task is to make an animation of waiting for the results of the search for airline tickets on a given route.

## Description
The application consists of two main screens:

1. Direction screen. Contains the fields "departure city", "destination city" and the "search" button.

2. Map screen. The map shows the path between the points. An airplane is flying along this path.


## Screenshots
<img src="/screenshots/directionScreen.jpg" width="360" height="640"> <img src="/screenshots/searchScreen.jpg" width="360" height="640"> <img src="/screenshots/mapScreen.jpg" width="360" height="640">


## Setup for Developers
1. Make sure you have downloaded the latest version of [Android Studio](https://developer.android.com/sdk/index.html). It works on Linux, Windows and Mac. Download the correct version for your OS.
2. Go to [the project repo](https://github.com/Nekobitlz/Aviasales-Test) and fork it by clicking "Fork" 
3. If you are working on Windows, download [Git Bash for Windows](https://git-for-windows.github.io/) to get a full Unix bash with Git functionality
4. Clone the repo to your desktop `git clone https://github.com/YOUR_USERNAME/Aviasales-Test.git`
5. Open the project with Android Studio 
6. Get your API-key from [Google Maps APIs](https://console.cloud.google.com/google/maps-apis/) and put him to `gradle.properties` file `GOOGLE_MAP_API_KEY=YOUR_API_KEY`
7. Build a 'app' application which is inside the base directory.