package org.cwp;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestLeakTest {

	@Test
    public void canRunBasicTest() {
        TestLeak testLeak = new TestLeak();
        assertEquals(1, 1);
    }

}