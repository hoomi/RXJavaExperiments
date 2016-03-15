package com.hoomi.rxjava.second;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by hos05 on 3/15/16.
 */
public class SecondExampleTest {
    @Mock
    private PrintStream mockedPrintStream;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConnect_Dot() throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a:2\nb:3\nexit".getBytes());
        SecondExample secondExample = new SecondExample(mockedPrintStream, byteArrayInputStream);

        ReactiveSum reactiveSum = secondExample.connect();

        assertEquals(5d,reactiveSum.getSum(), 0.00001d);
    }

    @Test
    public void testConnect_Equals() throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a=6\nb=3\nexit".getBytes());
        SecondExample secondExample = new SecondExample(mockedPrintStream, byteArrayInputStream);

        ReactiveSum reactiveSum = secondExample.connect();

        assertEquals(9d,reactiveSum.getSum(), 0.00001d);
    }

    @Test
    public void testConnect_EqualsDots() throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a=6\nb:3\nexit".getBytes());
        SecondExample secondExample = new SecondExample(mockedPrintStream, byteArrayInputStream);

        ReactiveSum reactiveSum = secondExample.connect();

        assertEquals(9d,reactiveSum.getSum(), 0.00001d);
    }

    @Test
    public void testConnect_InvalidInput() throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a6\nb:3\nexit".getBytes());
        SecondExample secondExample = new SecondExample(mockedPrintStream, byteArrayInputStream);

        ReactiveSum reactiveSum = secondExample.connect();

        assertEquals(0,reactiveSum.getSum(), 0.00001d);
    }
}