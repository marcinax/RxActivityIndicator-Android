# RxActivityIndicator

[![Release](https://jitpack.io/v/marcinax/RxActivityIndicator-Android.svg)](https://jitpack.io/#marcinax/RxActivityIndicator-Android)

A small library that helps you keep track of operations progress. It allows you to show progress bar (indicator) in a convenient way.

RxActivityIndicator will make onNext with 'true' value when there is at least one operation in progress. When all operations complete, 'false' value will be sent. Take a look at sample app for more details.

Usage
---
```java
//Create activity indicator
private final RxActivityIndicator activityIndicator = new RxActivityIndicator();

//Subscribe loading state, show or hide progress bar
activityIndicator
    .map(isLoading -> isLoading ? View.VISIBLE : View.INVISIBLE)
    .subscribe(progressBar::setVisibility);

//Use 'compose' to track Observable's activity
longOperation() //Observable
    .compose(activityIndicator.trackActivity())
    .subscribe();

//It is possible to track many Observables at once
secondLongOperation()
    .compose(activityIndicator.trackActivity())
    .subscribe();
```

Installation
---
How to install: https://jitpack.io/#marcinax/RxActivityIndicator-Android
