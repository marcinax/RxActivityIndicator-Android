package marcinax.rxactivityindicator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    private final RxActivityIndicator activityIndicator = new RxActivityIndicator();

    private Button button;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        subscribeProgress();

        button.setOnClickListener(v -> startOperations());
    }

    private void subscribeProgress() {
        //Subscribe for activity indicator, eg. show/hide progress bar or enable/disable button

        activityIndicator.map(isLoading -> isLoading ? View.VISIBLE : View.INVISIBLE)
                .subscribe(progressBar::setVisibility);

        activityIndicator.map(isLoading -> !isLoading)
                .subscribe(button::setEnabled);
    }

    private void startOperations() {
        longOperation() //It takes 3 seconds
                .compose(activityIndicator.trackActivity()) //Use 'compose' to mark Observable which should activate loading (track activity)
                .subscribe();

        longOperation()
                .flatMap(x -> longOperation()) //It takes 6 seconds, after 3 seconds progress bar is still spinning
                .compose(activityIndicator.trackActivity())
                .subscribe();
    }

    private Observable<Unit> longOperation() {
        return Observable.timer(3, TimeUnit.SECONDS).map(x -> Unit.getDefault());
    }
}
