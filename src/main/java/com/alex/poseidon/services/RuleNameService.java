package com.alex.poseidon.services;

import com.alex.poseidon.models.RuleNameModel;
import com.alex.poseidon.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class RuleNameService {

    private RuleNameRepository ruleNameRep;

    @Autowired
    public RuleNameService(RuleNameRepository ruleNameRep) {
        this.ruleNameRep = ruleNameRep;
    }

    public List<RuleNameModel> getAllRuleNames() {
        return ruleNameRep.findAll();
    }

    public boolean checkIfIdExists(int id) {
        return ruleNameRep.existsById(id);
    }

    public void saveRuleName(RuleNameModel ruleName) {
        ruleNameRep.save(ruleName);
    }

    public void deleteRuleNameById(int id) {
        ruleNameRep.deleteById(id);
    }

    public RuleNameModel getRuleNameById(int id) {
        return ruleNameRep.findById(id);
    }
}