# android-accountviews
*The Android View implementations for displaying user's account information*
 
## How to use
### Install library
 `android-accountviews` supports intallation to local maven repository. To install library into a local maven repository, invoke following command on the project root :
 
    $ ./gradlew :accountviews:install
     
If it is a first time after clone, the build may fails. If so, please follow following procedure :

1. Remove ':sample' from `settings.gradle`. After removing the ':sample', the settings.gradle should look like :  

        include ':accountviews'

2. Invoke `install` command for `android-accountviews` library.
   
        $ ./gradlew :accountviews:install
   
3. Revert `settings.gradle` to original state.
  
        include ':accountviews', ':sample'

4. Now your build would be run without any issues.

### Add to dependency
`android-accountviews` now available on the maven central repository. To use `android-accountviews` from your project, add following to your `build.gradle`.

    dependencies {
        compile 'com.androidhuman:accountviews:+'
    }

If you prefer modify the `android-accountviews` with your implementation, modify your `build.gradle` as follows:

    buildscript {
        repositories {
            mavenCentral()
            mavenLocal() // Add this
        }
    }
       
    //... Skip some lines ...
       
    dependencies {
        compile 'com.androidhuman.accountviews:android-accountviews:+' // Add this
    }


Okay, it's all done. Now you can use `android-accountviews` from your project.
