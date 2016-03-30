package com.tutorial.pik;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by Tom on 30.03.2016.
 */

public class SampleTest extends TestCase {
    
    public void testDoSample() {
        Sample sample = new Sample();
        assertTrue(sample.doSample(2,2) == 4);
    }
}