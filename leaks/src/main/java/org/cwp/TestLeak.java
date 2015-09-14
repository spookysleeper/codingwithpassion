package org.cwp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestLeak {

	public static void main(String[] args) throws Exception {
        List leak = new ArrayList();

		System.out.println("Run test...");
		while(true) {
            Thread.sleep(100);
            leak.add(new ArrayList<BigDecimal>(1000));
		}
	}

}