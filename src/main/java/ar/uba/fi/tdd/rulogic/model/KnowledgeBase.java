package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnowledgeBase {

	private TextReader textReader = new TextReader();
	private List<String> queries = new ArrayList<String>();
	private Facts facts = new Facts();
	private Rules rules = new Rules();

	public KnowledgeBase(String filePath) throws IOException {
		this.queries = textReader.fileParsed(filePath);
		this.checkIfIsARuleOrIsAFact(queries);
	}

	public List<String> getQueries() {
		return this.queries;
	}

	public List<String> getFacts() {
		return facts.getFacts();
	}

	public List<String> getRules() {
		return rules.getRules();
	}

	// Add to rule or fact list depending on which is each one.-
	public void checkIfIsARuleOrIsAFact(List<String> queries) {
		for (String query : queries) {
			query = query.replaceAll(" ", "");
			if (checkQuery(query)) {
				query = query.trim().replace(".", "");
				if (!query.contains(":-"))
					facts.addFact(query);
				else
					rules.addRule(query);
			}
		}
	}

	// Controls if query has a correct format.-
	public boolean checkQuery(String query) {
		query = query.replaceAll("\t", "");
		if (containsAnyCharacters(query))
			return false;
		else
			return true;
	}

	public boolean containsAnyCharacters(String query) {
		if ((query.charAt(query.length() - 1) != '.') || (query.indexOf("(") == -1) || ((query.indexOf(")") == -1)))
			return true;
		return false;
	}

	public boolean answer(String query) {

		boolean answer = false;
		query = query.replaceAll("\\.", "");

		if (facts.isAFact(query)) {
			answer = facts.checkFacts(query.replaceAll(" ", ""));
			if (answer)
				return answer;
		}

		String variable = rules.getVariable(query);
		if (variable == null) {
			return answer;
		}

		List<String> variablesFromRules = createRulesWithFacts(variable, query);

		return applyRule(variablesFromRules, this.facts.getFacts());
	}

	public boolean applyRule(List<String> variablesFromRules, List<String> facts) {
		int ruleSize = 0;

		for (int i = 0; i < variablesFromRules.size(); i++) {
			if (facts.indexOf(variablesFromRules.get(i).replaceAll(" ", "")) != -1) {
				ruleSize++;
			}
		}
		if (ruleSize == variablesFromRules.size()) {
			return true;
		}
		return false;
	}

	//Create rule from fact and db.-
	public List<String> createRulesWithFacts(String variable, String query) {

		List<String> queryParam = Arrays.asList(getNamesFromRules(query));
		List<String> rulesList = Arrays.asList(variable.split(":-"));
		
		List<String> ruleNames = Arrays.asList(getNamesFromRules(rulesList.get(0)));
		String rule  = rulesList.get(1);
		
		for (int i = 0; i < ruleNames.size(); i++) {
			rule = rule.replaceAll(ruleNames.get(i), queryParam.get(ruleNames.indexOf(ruleNames.get(i))));
		}
		
		List<String> ruleCreatedWithFact = new ArrayList<String>();
		int numberOfFacts = getNumberOfFacts(rule);
		
		while (numberOfFacts > 0) {
			ruleCreatedWithFact.add(rule.substring(0, rule.indexOf(")") + 1));
			if (numberOfFacts != 1) {
				rule = rule.substring(rule.indexOf(")") + 2);
			}
			numberOfFacts--;
		}
		return ruleCreatedWithFact;
	}

	public int getNumberOfFacts(String ruleFacts) {
		int counter = 0;
		for (int i = 0; i < ruleFacts.length(); i++) {
			if (ruleFacts.charAt(i) == '(') {
				counter++;
			}
		}
		return counter;
	}

	// Get names from rules, like hija(X,Y) -> (X,Y)
	public String[] getNamesFromRules(String query) {
		String subquery = query.substring(query.indexOf("(") + 1, query.indexOf(")"));
		return subquery.split(",");
	}

}
