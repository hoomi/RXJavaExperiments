package com.hoomi.rxjava.third;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hos05 on 3/15/16.
 */
public class ThirdExample {

    private final PrintStream printStream;

    public ThirdExample() {
        this(System.out);
    }

    ThirdExample(PrintStream printStream) {
        this.printStream = printStream;
    }

    public <V, M> List<M> mapIteration(List<V> list, Mapper<V, M> mapper) {
        List<M> mapped = new ArrayList<>(list.size());
        list.forEach(value -> mapped.add(mapper.map(value)));
        return mapped;
    }

    public <V, M> Observable<List<M>> mapReactive(List<V> list, Mapper<V, M> mapper) {
        return Observable
                .from(list)
                .map(mapper::map)
                .toList();
    }
}
