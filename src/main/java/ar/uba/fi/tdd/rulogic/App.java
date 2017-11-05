package ar.uba.fi.tdd.rulogic;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

/**
 * Console application.
 *
 */
//public class App
//{
//	public static void main(String[] args) {
//		System.out.println("I shall answer all your questions!");
//    }
//}
public class App
{
	public static void main(String[] args) throws IOException {    
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);		
		System.out.println("Interprete lógico por substitución!");
		
		KnowledgeBase kb = new KnowledgeBase("src/main/resources/rules.db");
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Base de datos cargada. Ingrese una consulta. Salga presionando 'q'.");
		
		String line = buffer.readLine();
		
		while (!line.equals("q")) {
			System.out.println(kb.answer(line));
			line = buffer.readLine();
			
			System.out.flush();
			System.setOut(old);
			System.out.println(baos.toString());
		}    	
    }
} 	
