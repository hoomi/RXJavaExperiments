package com.hoomi.rxjava.third;

/**
 * Created by hos05 on 3/15/16.
 */
public interface Mapper<V, M> {
    M map(V value);
}
