package com.alex.poseidon.services;

import com.alex.poseidon.models.RuleNameModel;
import com.alex.poseidon.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    private RuleNameRepository ruleNameRep;

    @Autowired
    public RuleNameService(RuleNameRepository ruleNameRep) {
        this.ruleNameRep = ruleNameRep;
    }

    /**
     * Get a list of all rule names
     *
     * @return list of rule names containing all rule name models
     */
    public List<RuleNameModel> getAllRuleNames() {
        return ruleNameRep.findAll();
    }

    /**
     *  Check if an Id already exists
     * @param id the rule name ID
     * @return true if ID already exists
     * @return false if ID doesn't exist
     */
    public boolean checkIfIdExists(int id) {
        return ruleNameRep.existsById(id);
    }

    /**
     * Save a new rule name in the DB
     * @param ruleName the RuleNameModel to save
     */
    public void saveRuleName(RuleNameModel ruleName) {
        ruleNameRep.save(ruleName);
    }

    /**
     * Delete an existent rule name from the DB
     * @param id the rule name ID
     */
    public void deleteRuleNameById(int id) {
        ruleNameRep.deleteById(id);
    }

    /**
     * Get a rule name model by ID
     * @param id the rule name ID
     * @return RuleNameModel found with the ID
     */
    public RuleNameModel getRuleNameById(int id) {
        return ruleNameRep.findById(id);
    }
}