package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.RuleNameModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

public interface RuleNameControllerInterface {
    @RequestMapping("/ruleName/list")
    String home(Model model);

    @GetMapping("/ruleName/add")
    String addRuleForm(RuleNameModel bid);

    @PostMapping("/ruleName/validate")
    String validate(@Valid RuleNameModel ruleName, BindingResult result, Model model);

    @GetMapping("/ruleName/update/{id}")
    String showUpdateForm(@PathVariable("id") Integer id, Model model);

    @PostMapping("/ruleName/update/{id}")
    String updateRuleName(@PathVariable("id") Integer id, @Valid RuleNameModel ruleName,
                          BindingResult result, Model model);

    @GetMapping("/ruleName/delete/{id}")
    String deleteRuleName(@PathVariable("id") Integer id, Model model);
}
