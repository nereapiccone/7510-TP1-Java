package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class InvalidFileReaderTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;
	private List<String> queriesList;
	private List<String> factList;
	private List<String> ruleList;
	
	@Before
	public void setUp() throws Exception {
		knowledgeBase = new KnowledgeBase("src/main/resources/rules.db");
		queriesList = new ArrayList<String>();
		queriesList.add("varon(juan).");
		queriesList.add("varon(hector).");
		queriesList.add("varon(roberto).");
		queriesList.add("varon(alejandro).");
		queriesList.add("mujer(maria).");
		queriesList.add("mujer(cecilia).");
		queriesList.add("padre(juan,pepe).");
		queriesList.add("padre(juan,pepa).");
		queriesList.add("padre(hector,maria).");
		queriesList.add("padre(roberto,alejandro).");
		queriesList.add("padre(roberto,cecilia).");		
		queriesList.add("hijo(X,Y):-varon(X),padre(Y,X).");		
		queriesList.add("hija(X,Y):-mujer(X),padre(Y,X).");		
		queriesList.add("hermano(nicolas,roberto).");		
		queriesList.add("hermano(roberto,nicolas).");		
		queriesList.add("varon(nicolas).");	
		queriesList.add("tio(X,Y,Z):-varon(X),hermano(X,Z),padre(Z,Y).");
		queriesList.add("tia(X,Y,Z):-mujer(X),hermano(X,Z),padre(Z,Y).");
		
		factList = new ArrayList<String>();
		factList.add("varon(juan)");
		factList.add("varon(pepe)");
		factList.add("varon(hector)");
		factList.add("varon(alejandro)");
		factList.add("mujer(maria)");
		factList.add("mujer(cecilia)");
		factList.add("padre(juan,pepe)");
		factList.add("padre(juan,pepa)");
		factList.add("padre(hector,maria)");
		factList.add("padre(roberto,alejandro)");
		factList.add("padre(roberto,cecilia)");		
		factList.add("hermano(nicolas,roberto)");		
		factList.add("hermano(roberto,nicolas)");		
		factList.add("varon(nicolas)");	
		
		ruleList = new ArrayList<String>();
		ruleList.add("hijoX,Y):-varon(X),padre(Y,X)");	
		ruleList.add("tio(X,Y,Z):-varon(X),hermano(X,Z),padre(Z,Y)");
		ruleList.add("tia(X,Y,Z):-mujer(X),hermano(X,Z),padre(Z,Y)");
	
	}

	@Test
	public void getQueriesTest() throws IOException {
		Assert.assertNotEquals(this.knowledgeBase.getQueries(), queriesList);
	}
	
	@Test
	public void getFactstest() throws IOException {
		Assert.assertNotEquals(this.knowledgeBase.getFacts(), factList);
	}
	
	@Test
	public void getRulestest() throws IOException {
		Assert.assertNotEquals(this.knowledgeBase.getRules(), ruleList);
	}
}
