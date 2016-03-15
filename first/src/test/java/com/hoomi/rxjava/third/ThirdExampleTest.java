package com.hoomi.rxjava.third;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.functions.Action1;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by hos05 on 3/15/16.
 */
public class ThirdExampleTest {

    private ThirdExample thirdExample;
    @Mock
    private PrintStream mockedPrintStream;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        thirdExample = new ThirdExample(mockedPrintStream);

    }

    @Test
    public void testMapIteration() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25, 36);

        assertEquals(expected, thirdExample.mapIteration(integers, value -> value * value));
    }

    @Test
    public void testMapReactive() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25, 36);
        Observable<List<Integer>> mappedObservable = thirdExample.mapReactive(integers, value -> value * value);
        mappedObservable.subscribe(integers1 -> {
            assertEquals(expected, integers1);
        });
    }
}