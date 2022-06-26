# Music_album_app

About this project
--------------
🚀 Simple albums and music track list app. 

🛠 Simple implementation for MVI architecture pattern and Clean Architecture.

🛠 Unit tests included.

🛠 SOLID principles.

🛠 Standard Coding Style.


Architecture pattern Used
--------------

[MVI][1]


Libraries & Tools Used
--------------

* [Foundation][4] - Components for core system capabilities, Kotlin extensions and support for
  multidex and unit testing.
* [Test][5] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
    * [View Binding][11] - To more easily write code that interacts with views. 
    * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
    * [Navigation][13] - Handle everything needed for in-app navigation.
    * [ViewModel][14] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
      asynchronous tasks for optimal execution.
    * [Repository][15] - A Module that handle data operations, You can consider repositories to be mediators between different data sources.
    * [Retrofit][16] - A simple library that is used for network transaction.
    * [Hilt][17]: For [dependency injection][18]
    * [Kotlin Coroutines][19] For managing background threads with simplified code and reducing needs for callbacks.
    * [Kotlin Flows][20] - A stream of data that can be computed asynchronously.
    * [Glide][21] - For image loading.
    

Technical choices
--------------

**MVI vs MVVM**

- A consistent state during the lifecycle of Views. 
- As it is unidirectional, Data flow can be tracked and predicted easily. 
- It ensures thread safety as the state objects are immutable.
- Easy to debug, As we know the state of the object when the error occurred.
- It's more decoupled as each component fulfills its own responsibility.
- Testing the app also will be easier as we can map the business logic for each state.
- [Read more][40]

**Hilt vs Dagger2 vs Koin**

- Hilt is built on top of the Dagger, and it comes with some advantages like simplify Dagger code and create a standard set of components and scopes to ease setup.
- Hilt generate the code in the compile time, while koin in runtime.

**Coroutines vs RxJava**

- Coroutines come with many advantages over RxJava, beside that it is Kotlin-friendly design pattern, it is: 
	* Lightweight: You can run many coroutines on a single thread due to support for suspension.
    * Fewer memory leaks: Use structured concurrency to run operations within a scope.
    * Built-in cancellation support.
    * Many Jetpack libraries include extensions that provide full coroutines support.


What's next
--------------
While the project scale up, Some points should be considered: 
- Analytics and tracking system should be implemented to provide insight on app usage and user engagement.
- Pagination should be handled in all lists from backend and client side. I already handled it in search screen only.
- More Unit & UI testing functions need to be added.


Other Projects
--------------

* [ShutterStock image list][50] - MVVM Sample.
* [Recorder][51] - Another MVVM Sample.
* [Prayer Now][52] - One of the projects I developed has 15+ Million downloads.
* [Mn Ahyaha][53] - Side project I developed from scratch.


[1]: https://cycle.js.org/model-view-intent.html
[4]: https://developer.android.com/jetpack/components
[5]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/view-binding
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/navigation/
[14]: https://developer.android.com/topic/libraries/architecture/viewmodel
[15]: https://developer.android.com/topic/architecture/data-layer#create_the_repository
[16]: https://square.github.io/retrofit
[17]: https://developer.android.com/training/dependency-injection/hilt-android
[18]: https://developer.android.com/training/dependency-injection
[19]: https://developer.android.com/kotlin/coroutines
[20]: https://developer.android.com/kotlin/flow
[21]: https://bumptech.github.io/glide/

[40]: http://hannesdorfmann.com/android/mosby3-mvi-1/

[50]: https://github.com/islamarr/shutterstock_image_list
[51]: https://github.com/islamarr/recorder
[52]: https://play.google.com/store/apps/details?id=com.AppRocks.now.prayer
[53]: https://play.google.com/store/apps/details?id=com.Ihsan.Ahyaha
