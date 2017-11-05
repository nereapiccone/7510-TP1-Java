package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;

public class Facts {
    private List<String> factsLists;

	public Facts() {
		this.factsLists = new ArrayList<String>();
	}
	
    public List<String> getFacts() {
        return factsLists;
    }
    
    public void setFactsList(List<String> facts) {
        this.factsLists = facts;
    }
    
    public void addFact(String fact) {
        this.factsLists.add(fact);
    }
    
	// Check if fact is in the list of facts
    public boolean checkFacts(String query) {
        for (String fact : this.factsLists) {
            if (fact.equals(query))
                return true;
        }
        return false;
    }
    
	// Check if is a fact
	public boolean isAFact(String query) {
		if (!query.contains(":-"))
			return true;
		return false;
	}
}
