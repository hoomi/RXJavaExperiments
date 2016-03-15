package com.hoomi.rxjava.second;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;

import java.io.InputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by hoomanostovari on 15/03/2016.
 */
public class ReactiveSumTest {

    private static final Double TEST_DOUBLE = 2.0d;
    private static final String TEST_EXCEPTION_MESSAGE = "THIS IS A TEST EXCEPTION";
    private ReactiveSum reactiveSum;
    private Observable<Double> observableA;
    private Observable<Double> observableB;
    @Mock
    private InputStream mockedInputStream;
    @Mock
    private PrintStream mockedPrintStream;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        observableA = Observable.just(2.0d);
        observableB = Observable.just(3.2d);
        reactiveSum = new ReactiveSum(observableA, observableB, mockedPrintStream);
        reactiveSum.setInputStream(mockedInputStream);
        reactiveSum.setPrintStream(mockedPrintStream);
    }

    @Test
    public void testOnNext() throws Exception {

        reactiveSum.onNext(TEST_DOUBLE);

        verify(mockedPrintStream).println(TEST_DOUBLE);
    }

    @Test
    public void testOnError() throws Exception {
        reactiveSum.onError(new Exception(TEST_EXCEPTION_MESSAGE));

        verify(mockedPrintStream).println(TEST_EXCEPTION_MESSAGE);
    }

    @Test
    public void testSubscribe_3Inputs() throws Exception {
        observableA = Observable.just(2.0d);
        observableB = Observable.just(3.2d, 2.0d);
        reactiveSum = new ReactiveSum(observableA, observableB, mockedPrintStream);

        assertTrue(4.0d == reactiveSum.getSum());
        verify(mockedPrintStream, atLeastOnce()).println("Completed");
    }

    @Test
    public void testSubscribe_4Inputs() throws Exception {
        observableA = Observable.just(2.0d, 1.5d);
        observableB = Observable.just(3.2d, 2.0d);
        reactiveSum = new ReactiveSum(observableA, observableB, mockedPrintStream);

        assertTrue(3.5d == reactiveSum.getSum());
        verify(mockedPrintStream, atLeastOnce()).println("Completed");
    }

    @Test
    public void testSubscribe_5Inputs() throws Exception {
        observableA = Observable.just(2.0d, 1.5d, 3.0d);
        observableB = Observable.just(3.2d, 2.0d);
        reactiveSum = new ReactiveSum(observableA, observableB, mockedPrintStream);

        assertTrue(5.0d == reactiveSum.getSum());
        verify(mockedPrintStream, atLeastOnce()).println("Completed");

    }
}