# KOTLIN - MVVM - COROUTINES - KOIN 
A Photo list sample that retrieves pictures from Pixabay site
 
<ul>
<li>The application supports both portrait and landscape mode </li>
<li>The user is able to search for images entering a search term with at least 3  characters </li>
<li>When the app starts it triggers a search for the string "flowers"</li>
<li>When an item in the result list gets clicked, a new detail screen will be opened</li>

</ul>

<h2>What is used in project? </h2>
<ol>
 <li><strong>Kotlin</strong></li>
<li><strong>Clean Architecture</strong>: An app using layered architecture based on <a href="http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html" rel="nofollow">Clean Architecture by Uncle Bob</a>.</li>
<li><strong>Retrofit</strong>: To retrieve data from the network</li>
<li><strong>Architecture components and using MVVM</strong>: LiveData and ViewModel</li>
<li><strong>Coroutines</strong>Coroutines are a new way of managing background threads that can simplify code by reducing the need for callbacks. They convert async callbacks for long-running tasks, such as database or network access, into sequential code. We use coroutines to do tasks in a background thread. This goes very well with the idea of use cases, single actions that the ViewModel calls depending of its needs. The guideline should be that every task executed by a use case should be done in a background thread, so, in the main thread, we could show a loading screen or any alternative, and the UI doesn’t get blocked.</li>
<li><strong>Navigation component</strong>: At last it's settled, single activity is what's Google recommend now. Navigation editor makes things easy for us to design navigation path of our app.</li>
<li><strong>Koin</strong>: Using Dependency injection to manage the dependencies in an optimal way.</li>
<li><strong>Glide</strong>: To view images from the network </li>
</ol>

Library reference resources:
<ol>
<li>Koin: <a href="https://github.com/google/dagger">https://insert-koin.io/</a></li>
<li>Retrofit: <a href="https://github.com/square/retrofit">GitHub - square/retrofit: Type-safe HTTP client for Android and Java by Square, Inc.</a></li>
<li>Coroutines: <a href="https://github.com/ReactiveX/RxJava">GitHub - ReactiveX/RxJava: RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.</a></li>
<li>Constraint-Layout: <a href="https://developer.android.com/training/constraint-layout/index.html" rel="nofollow">Build a Responsive UI with ConstraintLayout | Android Developers</a></li>
<li>Glide: <a href="https://github.com/bumptech/glide" rel="nofollow">An image loading and caching library for Android focused on smooth scrolling </a></li>
</ol>
