package marcinax.rxactivityindicator;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Marcin Gmyz on 27.04.2017.
 */

public class RxActivityIndicator extends Observable<Boolean> {
    private final BehaviorSubject<Integer> variable = BehaviorSubject.createDefault(0);

    private Observable<Boolean> loading;

    public RxActivityIndicator() {
        loading = variable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturnItem(0)
                .map(x -> x > 0)
                .share();
    }

    private <T> Observable<T> trackActivity(Observable<T> source) {
        Callable<Unit> resourceFactory = () -> {
            increment();
            return Unit.getDefault();
        };

        Function<Unit, Observable<T>> observableFactory = x -> source;

        Consumer<Unit> disposer = unit -> decrement();

        return Observable.using(resourceFactory, observableFactory, disposer);
    }

    @Override
    protected void subscribeActual(Observer<? super Boolean> observer) {
        loading.subscribe(observer);
    }

    private void increment() {
        variable.onNext(variable.getValue() + 1);
    }

    private void decrement() {
        variable.onNext(variable.getValue() - 1);
    }

    public <T> ObservableTransformer<T, T> trackActivity() {
        return this::trackActivity;
    }
}
