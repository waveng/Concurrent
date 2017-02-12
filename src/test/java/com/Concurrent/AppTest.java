package com.Concurrent;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void thtest() {
		ThreadPoolDome tp = new ThreadPoolDome();
		tp.start();
		
		ThreadPoolDome tps = new ThreadPoolDome();
		tps.starts();
	}
}
