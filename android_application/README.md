Documentation

This project is written in Kotlin and the code has been structured following the Clean Architecture approach based on Uncle Bob - The Clean Architecture and in the presentation layer is using a model-view-presenter (MVP) pattern and S.O.L.I.D as a design principle guideline.

The app is divided into 3 modules. Data -> Domain -> Presentation

Libraries
The following libraries are located in android_application -> buildsystem -> dependencies.gradle

Retrofit - Network calls done via  Retrofit 
RxJava2 - To have universal structures for data flow in an application.
Dagger2 - For dependency injection
Glide  - For image loader

Debugging
LeakCanary - To Discover memory leaks.
Timber - Log errors and pass them into your crash reporting tool Crashlytics

UI 

Latest trends in the new Material Design Components Library 2018 

RecyclerView with paging using( endless scroll, filters, 
Header, Sections by Date, SwipeRefreshLayout, ProgressBar,...)
