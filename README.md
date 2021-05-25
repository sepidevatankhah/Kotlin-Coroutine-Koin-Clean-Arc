Actions Status Kotlin Version AGP Gradle License

# KOTLIN - MVVM - COROUTINES - KOIN 
This is a project used to implement and explain several topics in Android Development. The idea is to share through different commits and branches with different implementations regarding the use of frameworks, patterns, and good practices, for now are consisted of two branches: 
<il>
<li><a href="https://github.com/sepidevatankhah/Kotlin-Coroutine-Koin-Clean-Arc/tree/base-branch">Base_branch</a> is a simple Photo list sample that retrieves pictures from Pixabay site, it is structured according to the Clean Architecture, MVVM Pattern, Koin DI, Coroutines.</li>
<li><a href="https://github.com/sepidevatankhah/Kotlin-Coroutine-Koin-Clean-Arc/tree/bottom-navigation-branch">Bottom-navigation-branch</a> is a online/offline music list sample from Last.fm, this one is also structured according to the Clean Architecture, MVVM Pattern, Koin DI, Coroutines and the main focus is implementing the Bottom-Navigation according to the Google Navigation Component and working with google ORM (<b>ROOM</b>).</li>
</il>
</br>
Regarding these projects, will be added step-by-step tutorials in Medium that walk through it, related link will be added as soon as they will be completed.

<h2>What is used in project? </h2>
<ol>
 <li><strong>Kotlin</strong></li>
<li><strong>Clean Architecture</strong>: An app using layered architecture based on <a href="http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html" rel="nofollow">Clean Architecture by Uncle Bob</a>.</li>
<li><strong>Retrofit</strong>: To retrieve data from the network</li>
<li><strong>Architecture components and using MVVM</strong>: LiveData and ViewModel</li>
<li><strong>Coroutines</strong>: Coroutines are a new way of managing background threads that can simplify code by reducing the need for callbacks. They convert async callbacks for long-running tasks, such as database or network access, into sequential code. We use coroutines to do tasks in a background thread. This goes very well with the idea of use cases, single actions that the ViewModel calls depending of its needs. The guideline should be that every task executed by a use case should be done in a background thread, so, in the main thread, we could show a loading screen or any alternative, and the UI doesn’t get blocked.</li>
<li><strong>Room</strong>: To read and write from google ORM </li>
<li><strong>Navigation component</strong>: At last it's settled, single activity is what's Google recommend now. Navigation editor makes things easy for us to design navigation path of our app.</li>
<li><strong>Koin</strong>: Using Dependency injection to manage the dependencies in an optimal way.</li>
<li><strong>Glide</strong>: To view images from the network </li>
</ol>

My main objective is to achieve with the least amount of code RUDT principles which means that the code must be easy to:

Read
Update
Debug
Test (Unit & UI)

Library reference resources:
<ol>
<li>Koin: <a href="https://insert-koin.io/">Start Koin</a></li>
<li>Retrofit: <a href="https://github.com/square/retrofit">GitHub - square/retrofit: Type-safe HTTP client for Android and Java by Square, Inc.</a></li>
<li>Coroutines: <a href="https://kotlinlang.org/docs/coroutines-guide.html">Coroutines guide</a></li>
<li>Constraint-Layout: <a href="https://developer.android.com/training/constraint-layout/index.html" rel="nofollow">Build a Responsive UI with ConstraintLayout | Android Developers</a></li>
<li>Glide: <a href="https://github.com/bumptech/glide" rel="nofollow">An image loading and caching library for Android focused on smooth scrolling </a></li>
</ol>
