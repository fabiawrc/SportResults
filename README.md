# SportResults - Jetpack compose and Clean Architecture native Android application

This app shows some of my current experience with the latest approaches and technologies for developing native Android apps.

SportResults is a sample app built with
* Kotlin
* Jetpack Compose
* Clean Architecture
* MVVM
* Coroutines
* Dagger - Hilt (DI)
* Retrofit
* Room
* Repository pattern
* Lottie animations

To try out this sample app, you need to use 
[Android Studio Arctic Fox](https://developer.android.com/studio)
You can clone this repository or import the
project from Android Studio following the steps
[here](https://developer.android.com/jetpack/compose/setup#sample).

This sample showcases:

* SplashScreen with simple Lottie animation
* Simple list of activities with filtering by StorageType
* Simple detail of activity with some custom components
* Saving activity to local storage (Room) or remote storage (API)
* UI state management
* Integration with Architecture Components: Navigation, ViewModel
* Custom components - Text Input, Toolbar, DropDrown, TimePicker, Chip
* Material Design 3 theming
* Sample Unit tests

## Saving activities to remote storage (API)
Saving and loading sports activities to remote storage is emulated using fake classes because the backend interface is not available.

### Unit tests
In [androidTest](app/src/test/java/com/example/sportresults) you'll find unit tests


