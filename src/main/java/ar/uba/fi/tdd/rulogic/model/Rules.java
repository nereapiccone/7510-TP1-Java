package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;

public class Rules {

	private List<String> rulesList;

	public Rules() {
		this.rulesList = new ArrayList<String>();
	}

	public List<String> getRules() {
		return rulesList;
	}

	public void setRules(List<String> rules) {
		this.rulesList = rules;
	}

	public void addRule(String rule) {
		this.rulesList.add(rule);
	}

	// Check if the query is a rule
	public boolean isARule(String query) {
		if (query.contains(":-"))
			return true;
		return false;
	}

	// Search name of rule to continue with facts
	public String getVariable(String query) {
		String variable = removeParenthesis(query);
		String name = null;
		
		for (String i : this.rulesList) {
			if (this.removeParenthesis(i).equals(variable)) {
				name = i;
				break;
			}
		}				
		return name;
	}
	
	public String removeParenthesis(String query) {
		if (query.indexOf("(") != -1) {
			return query.substring(0, query.indexOf("("));
		}
		return null;
	}

}
