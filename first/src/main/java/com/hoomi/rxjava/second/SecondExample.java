package com.hoomi.rxjava.second;

import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.regex.Pattern;

/**
 * Created by hos05 on 3/15/16.
 */
public class SecondExample {
    public static final String EXIT = "exit";
    private final PrintStream printStream;
    private final InputStream inputStream;

    public SecondExample() {
        this(System.out, System.in);
    }

    SecondExample(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
    }

    public ReactiveSum connect() {
        ConnectableObservable<String> input = from(inputStream);
        Observable<Double> a = varStream("a", input);
        Observable<Double> b = varStream("b", input);

        ReactiveSum sum = new ReactiveSum(a, b, printStream);

        input.connect();
        return sum;
    }

    private Observable<Double> varStream(String varName, ConnectableObservable<String> input) {
        final Pattern pattern = Pattern.compile("^\\s*" + varName + "\\s*[:|=]\\s*(-?\\d+\\.?\\d*)\\n*$");
        return input
                .map(pattern::matcher)
                .filter(matcher -> matcher.matches() && matcher.group(1) != null)
                .map(matcher -> Double.parseDouble(matcher.group(1)));
    }

    private ConnectableObservable<String> from(InputStream inputStream) {
        return from(new BufferedReader(new InputStreamReader(inputStream)));
    }

    private ConnectableObservable<String> from(BufferedReader bufferedReader) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                try {
                    String line;
                    while (!subscriber.isUnsubscribed() && (line = bufferedReader.readLine()) != null) {
                        if (line.equals(EXIT)) {
                            break;
                        }
                        subscriber.onNext(line);
                    }

                } catch (Exception e) {
                    subscriber.onError(e);
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        }).publish();
    }
}
