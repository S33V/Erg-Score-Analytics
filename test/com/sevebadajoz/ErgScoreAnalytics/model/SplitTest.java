package com.sevebadajoz.ErgScoreAnalytics.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplitTest {

    Split testSplit;

    @BeforeEach
    void setUp() {
        testSplit = new Split("01:38.7");
    }

    @Test
    void textToSeconds() {
        assertEquals(98.7, testSplit.textToSeconds());
    }

    @Test
    void textToSeconds1() {
        assertEquals(60.0, testSplit.textToSeconds("01:00.0"));
        assertEquals(90.0, testSplit.textToSeconds("01:30.0"));
        assertEquals(95.0, testSplit.textToSeconds("01:35.0"));
        assertEquals(95.5, testSplit.textToSeconds("01:35.5"));
    }

    @Test
    void secondsToString() {
        assertEquals("01:00.0", Split.secondsToString(60.0));
        assertEquals("01:30.0", Split.secondsToString(90.0));
        assertEquals("01:35.0", Split.secondsToString(95.0));
        assertEquals("01:35.5", Split.secondsToString(95.5));
    }


    @Test
    void weightAdj() {
        assertEquals("01:32.0", testSplit.weightAdj(197.0).toString());
    }

}