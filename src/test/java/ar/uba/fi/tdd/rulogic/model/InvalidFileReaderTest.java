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
		knowledgeBase = new KnowledgeBase("src/main/resources/invalidRules.db");
		queriesList = new ArrayList<String>();
		queriesList.add("varon)juan).");
		queriesList.add("varonhector).");
		queriesList.add("varon(.");
		
		factList = new ArrayList<String>();
		factList.add("varon");
		factList.add("varon(");
		
		ruleList = new ArrayList<String>();
		ruleList.add("hijoX,Y):-varon(X),padre(Y,X)");	
		ruleList.add("tio(X,Y,Z-varon(X),hermano(X,Z),padre(Z,Y)");
	
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
