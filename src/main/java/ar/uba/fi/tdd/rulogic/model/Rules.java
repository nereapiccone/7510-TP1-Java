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

	public void addRule(String rule) {
		this.rulesList.add(rule);
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
		String string = null; 
		if (query.indexOf("(") != -1) 
			string = query.substring(0, query.indexOf("("));
		return string;
	}

}
