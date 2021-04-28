package tests;

import org.junit.jupiter.api.Test;

import taiji.LongLong;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class LongLongTest {

    @Test
    void OR() {
    	LongLong test = new LongLong(0b000000000000000000000000000000000000000000000000000000000000001L,0);
        LongLong test1 = new LongLong(0,0);       
        assertEquals(test.HI, test.OR(test1).HI);
        assertEquals(test.LO, test.OR(test1).LO);
        
    }

    @Test
    void AND() {
    	LongLong test = new LongLong(0b000000000000000000000000000000000000000000000000000000000000001L,0);
        LongLong test1 = new LongLong(0,0);   
        assertEquals(test1.HI, test.AND(test1).HI);
        assertEquals(test1.LO, test.AND(test1).LO);
    }

    @Test
    void NEG() {
    	LongLong test = new LongLong(0,0);
    	LongLong erg = new LongLong(0b1111111111111111111111111111111111111111111111111111111111111111L,0b1111111111111111111111111111111111111111111111111111111111111111L);
    	assertEquals(erg.HI, test.NEG(test).HI);
        assertEquals(erg.LO, test.NEG(test).LO);
    }

    @Test
    void RSHIFT() {
    	LongLong test = new LongLong(0b0000111111111111111111111111111111111111111111111111111110000001L,0b0001111111111111111111111111111111111111111111111111111110000000L);
    	System.out.println(test.toString().charAt(12));
    	LongLong erg = new LongLong(0b0000000001111111111111111111111111111111111111111111111111111100L,0b0000100011111111111111111111111111111111111111111111111110000000L);
    	assertEquals(erg.HI, test.RSHIFT(5).HI);
        assertEquals(erg.LO, test.RSHIFT(5).LO);
       
    }

    @Test
    void LSHIFT() {
        LongLong test = new LongLong(0,0b1000000000000000000000000000000000000000000000000000000000000000L);
        LongLong erg = new LongLong(0b0000000000000000000000000000000000000000000000000000000000000100L,0);
        assertEquals(erg.HI, test.LSHIFT(3).HI);
        assertEquals(erg.LO, test.LSHIFT(3).LO);
        
    }

	@Test
	void isZero() {
		LongLong test = new LongLong(0,0);
		LongLong test1 = new LongLong(1,0);
		LongLong test2 = new LongLong(0,1);
		assertTrue(test.isZero());
		assertFalse(test1.isZero());
		assertFalse(test2.isZero());
	}
	
	@Test
	void toStringtest() {
		LongLong test = new LongLong(0,0);
		assertEquals("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", test.toString());
	}
}