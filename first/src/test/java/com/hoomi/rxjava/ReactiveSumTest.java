package com.hoomi.rxjava;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;

import java.io.InputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.inOrder;
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

        InOrder inOrder = inOrder(mockedPrintStream);
        inOrder.verify(mockedPrintStream).println(new Double(5.2d));
        inOrder.verify(mockedPrintStream).println(new Double(4.0d));
        inOrder.verify(mockedPrintStream).println("Completed");
    }

    @Test
    public void testSubscribe_4Inputs() throws Exception {
        observableA = Observable.just(2.0d, 1.5d);
        observableB = Observable.just(3.2d, 2.0d);
        reactiveSum = new ReactiveSum(observableA, observableB, mockedPrintStream);

        InOrder inOrder = inOrder(mockedPrintStream);
        inOrder.verify(mockedPrintStream).println(new Double(5.2d));
        inOrder.verify(mockedPrintStream).println(new Double(4.7d));
        inOrder.verify(mockedPrintStream).println(new Double(3.5d));
        inOrder.verify(mockedPrintStream).println("Completed");
    }

    @Test
    public void testSubscribe_5Inputs() throws Exception {
        observableA = Observable.just(2.0d, 1.5d, 1.0d);
        observableB = Observable.just(3.2d, 2.0d);
        reactiveSum = new ReactiveSum(observableA, observableB, mockedPrintStream);

        InOrder inOrder = inOrder(mockedPrintStream);
        inOrder.verify(mockedPrintStream).println(new Double(5.2d));
        inOrder.verify(mockedPrintStream).println(new Double(4.7d));
        inOrder.verify(mockedPrintStream).println(new Double(3.5d));
        inOrder.verify(mockedPrintStream).println(new Double(1.5d));
        inOrder.verify(mockedPrintStream).println("Completed");
    }
}