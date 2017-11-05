package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		knowledgeBase = new KnowledgeBase("src/main/resources/rules.db");
	}

	@Test
	public void testFailAnswerFact() throws IOException {
		Assert.assertFalse(this.knowledgeBase.answer("varon (santiago)."));		
		Assert.assertFalse(this.knowledgeBase.answer("mujer (santiago)."));
	}

	@Test
	public void testCorrectAnswerFact() throws IOException {
		Assert.assertTrue(this.knowledgeBase.answer("mujer (cecilia)."));
		Assert.assertTrue(this.knowledgeBase.answer("varon (nicolas)."));
	}
	
	@Test
	public void testFailAnswerRule() throws IOException {
		Assert.assertFalse(this.knowledgeBase.answer("padre(mario, pepe)."));		
		Assert.assertFalse(this.knowledgeBase.answer("hija(maria, roberto)."));
	}

	@Test
	public void testCorrectAnswerRule() throws IOException {
		Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepe)."));
		Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe, juan)."));
	}
	

}
