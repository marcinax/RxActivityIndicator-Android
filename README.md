# RxActivityIndicator

A small library that helps you keep track of operations progress. It allows you to show progress bar (indicator) in a convenient way.

RxActivityIndicator will make onNext with 'true' value when there is at least one operation in progress. When all operations complete, 'false' value will be sent. Take a look at sample app for more details.

Usage
---
```java
private final RxActivityIndicator activityIndicator = new RxActivityIndicator();

activityIndicator
    .map(isLoading -> isLoading ? View.VISIBLE : View.INVISIBLE)
    .subscribe(progressBar::setVisibility);

longOperation() //Observable
    .compose(activityIndicator.trackActivity())
    .subscribe();
```
