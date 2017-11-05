package ar.uba.fi.tdd.rulogic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;

public class AppTest {

	@Test
	public void testFileMain() throws Exception {

		String data = "padre(juan,pepe)." + "\n"+"q";
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			String[] args = null;
			App.main(args);
			

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			PrintStream old = System.out;
			System.setOut(ps);
			System.out.flush();
			System.setOut(old);
			System.out.println(baos.toString());
			Assert.assertEquals("", baos.toString());
		} finally {
			System.setIn(stdin);
		}

	}
}
