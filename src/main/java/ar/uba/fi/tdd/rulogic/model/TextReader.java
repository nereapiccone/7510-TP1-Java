package ar.uba.fi.tdd.rulogic.model;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class TextReader {

	// Parse the received file and add to query list.-
	public List<String> fileParsed(String filePath) throws IOException {
		
		FileReader dbFile = new FileReader(filePath);
		BufferedReader bufferFile = new BufferedReader(dbFile);
		
		List<String> queries = new ArrayList<String>();
		String query;
		
		while ((query = bufferFile.readLine()) != null) {
			query = query.replaceAll(" ", "");
			query = query.replaceAll("\t", "");
            queries.add(query);            
		}
		
		bufferFile.close();
		return queries;
	}
}
