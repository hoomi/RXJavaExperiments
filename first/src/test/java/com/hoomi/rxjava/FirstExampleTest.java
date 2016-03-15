package com.hoomi.rxjava;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

/**
 * Created by hoomanostovari on 14/03/2016.
 */
public class FirstExampleTest {
    @Mock
    private PrintStream mockedPrintStream;
    private FirstExample firstExample;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        firstExample = new FirstExample(mockedPrintStream);
    }

    @Test
    public void testRunIterate() throws Exception {
        firstExample.runIterate();
        InOrder inOrder = inOrder(mockedPrintStream);
        inOrder.verify(mockedPrintStream).println("One");
        inOrder.verify(mockedPrintStream).println("Two");
        inOrder.verify(mockedPrintStream).println("Three");
        inOrder.verify(mockedPrintStream).println("Four");
        inOrder.verify(mockedPrintStream).println("Five");
        inOrder.verify(mockedPrintStream).println("Complete");
    }

    @Test
    public void testRunRxJava() throws Exception {
        firstExample.runRxJava();
        InOrder inOrder = inOrder(mockedPrintStream);
        inOrder.verify(mockedPrintStream).println("One");
        inOrder.verify(mockedPrintStream).println("Two");
        inOrder.verify(mockedPrintStream).println("Three");
        inOrder.verify(mockedPrintStream).println("Four");
        inOrder.verify(mockedPrintStream).println("Five");
        inOrder.verify(mockedPrintStream).println("Complete");
    }
}