package com.hoomi.rxjava.first;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hoomanostovari on 14/03/2016.
 */
public class FirstExample {

    private final List<String> stringList;
    private final PrintStream printStream;

    FirstExample(PrintStream printStream) {
        this.stringList = Arrays.asList("One", "Two", "Three", "Four", "Five");
        this.printStream = printStream;
    }

    public FirstExample() {
        this(System.out);
    }

    public void runIterate() {
        stringList.forEach(printStream::println);
        printStream.println("Complete");

    }

    public void runRxJava() {
        Observable.from(stringList).subscribe(printStream::println,
                throwable -> printStream.println("There was an error"),
                () -> printStream.println("Complete"));
    }
}
