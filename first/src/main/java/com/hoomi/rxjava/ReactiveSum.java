package com.hoomi.rxjava;

import rx.Observable;
import rx.Observer;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by hoomanostovari on 15/03/2016.
 */
public class ReactiveSum implements Observer<Double> {

    private PrintStream printStream;
    private InputStream inputStream;
    private Double sum;

    public ReactiveSum(Observable<Double> a, Observable<Double> b) {
        this(a,b,System.out);
    }

    ReactiveSum(Observable<Double> a, Observable<Double> b, PrintStream printStream) {
        this.sum = 0d;
        this.printStream = printStream;
        Observable.amb(a,b).subscribe(this);

    }

    void setPrintStream(PrintStream printStream) {
        this.printStream = printStream;
    }

    void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void onCompleted() {
        printStream.println("Completed");
    }

    @Override
    public void onError(Throwable e) {
        printStream.println(e.getMessage());
    }

    @Override
    public void onNext(Double aDouble) {
        this.sum = aDouble;
        printStream.println(aDouble);
    }
}
