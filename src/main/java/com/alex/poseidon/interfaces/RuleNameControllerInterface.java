package com.alex.poseidon.interfaces;

import com.alex.poseidon.models.RuleNameModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public interface RuleNameControllerInterface {
    @RequestMapping("/ruleName/list")
    String home(Model model);

    @GetMapping("/ruleName/add")
    String addRuleForm(Model model);

    @PostMapping("/ruleName/validate")
    String validate(@Valid @ModelAttribute("ruleName") RuleNameModel ruleName, BindingResult result,
                    Model model, RedirectAttributes ra);

    @GetMapping("/ruleName/update/{id}")
    String showUpdateForm(@PathVariable("id") int id, Model model);

    @PostMapping("/ruleName/update/{id}")
    String updateRuleName(@PathVariable("id") int id,
                          @Valid @ModelAttribute("ruleName") RuleNameModel ruleName,
                          BindingResult result, Model model, RedirectAttributes ra);

    @GetMapping("/ruleName/delete/{id}")
    String deleteRuleName(@PathVariable("id") int id, Model model, RedirectAttributes ra);
}
