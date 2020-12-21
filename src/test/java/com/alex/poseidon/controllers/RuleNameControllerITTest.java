package com.alex.poseidon.controllers;

import com.alex.poseidon.models.RuleNameModel;
import com.alex.poseidon.services.RuleNameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameControllerITTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @MockBean
    RuleNameService ruleNameService;

    @Before
    public void setupMockmvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestRuleNameListViewShouldReturnSuccess() throws Exception {
        //1. Setup

        RuleNameModel rule = new RuleNameModel();
        rule.setId(1);
        rule.setName("Rule Name");
        rule.setDescription("Description");
        rule.setJson("Json");
        rule.setTemplate("Template");
        rule.setSqlStr("SQL");
        rule.setSqlPart("SQL Part");

        List<RuleNameModel> ruleList = new ArrayList<>();
        ruleList.add(rule);

        doReturn(ruleList)
                .when(ruleNameService)
                .getAllRuleNames();

        //2. Act
        mockMvc.perform(get("/ruleName/list"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleName"))
                .andReturn();
        assertTrue(ruleList.get(0).getTemplate().equals("Template"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestRuleNameAddViewShouldReturnSuccess() throws Exception {
        //1. Setup

        //2. Act
        mockMvc.perform(get("/ruleName/add"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("ruleName/add"))
                .andExpect(model().attributeExists("ruleName"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void postRequestRuleNameValidateShouldReturnSuccess() throws Exception {
        //1. Setup

        RuleNameModel rule = new RuleNameModel();
        rule.setId(1);
        rule.setName("Rule Name");
        rule.setDescription("Description");
        rule.setJson("Json");
        rule.setTemplate("Template");
        rule.setSqlStr("SQL");
        rule.setSqlPart("SQL Part");

        List<RuleNameModel> ruleList = new ArrayList<>();
        ruleList.add(rule);

        doNothing()
                .when(ruleNameService)
                .saveRuleName(rule);

        doReturn(ruleList)
                .when(ruleNameService)
                .getAllRuleNames();
        //2. Act
        mockMvc.perform(post("/ruleName/validate")
                .flashAttr("successSaveMessage", "Your rule name was successfully added")
                .param("id", "1")
                .param("name", "Rule Name")
                .param("Description", "Description"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"))
                .andExpect(flash().attributeExists("successSaveMessage"))
                .andReturn();
        assertTrue(ruleList.get(0).getTemplate().equals("Template"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestRuleNameUpdateIdShouldReturnSuccess() throws Exception {
        //1. Setup

        RuleNameModel rule = new RuleNameModel();
        rule.setId(1);
        rule.setName("Rule Name");
        rule.setDescription("Description");
        rule.setJson("Json");
        rule.setTemplate("Template");
        rule.setSqlStr("SQL");
        rule.setSqlPart("SQL Part");

        doReturn(true)
                .when(ruleNameService)
                .checkIfIdExists(rule.getId());

        doReturn(rule)
                .when(ruleNameService)
                .getRuleNameById(rule.getId());
        //2. Act
        mockMvc.perform(get("/ruleName/update/{id}", "1"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"))
                .andReturn();
        assertTrue(rule.getTemplate().equals("Template"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void postRequestRuleNameUpdateIdShouldReturnSuccess() throws Exception {
        //1. Setup

        RuleNameModel rule = new RuleNameModel();
        rule.setId(1);
        rule.setName("Rule Name");
        rule.setDescription("Description");
        rule.setJson("Json");
        rule.setTemplate("Template");
        rule.setSqlStr("SQL");
        rule.setSqlPart("SQL Part");

        List<RuleNameModel> ruleList = new ArrayList<>();
        ruleList.add(rule);

        doReturn(true)
                .when(ruleNameService)
                .checkIfIdExists(rule.getId());
        doNothing()
                .when(ruleNameService)
                .saveRuleName(rule);

        doReturn(ruleList)
                .when(ruleNameService)
                .getAllRuleNames();

        //2. Act
        mockMvc.perform(post("/ruleName/update/{id}", "1")
                .flashAttr("successUpdateMessage", "Your rule name was successfully updated")
                .param("id", "1")
                .param("name", "Rule Name")
                .param("Description", "Description"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"))
                .andExpect(flash().attributeExists("successUpdateMessage"))
                .andReturn();
        assertTrue(ruleList.get(0).getTemplate().equals("Template"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestRuleNameDeleteIdShouldReturnSuccess() throws Exception {
        //1. Setup

        RuleNameModel rule = new RuleNameModel();
        rule.setId(1);
        rule.setName("Rule Name");
        rule.setDescription("Description");
        rule.setJson("Json");
        rule.setTemplate("Template");
        rule.setSqlStr("SQL");
        rule.setSqlPart("SQL Part");

        List<RuleNameModel> ruleList = new ArrayList<>();
        ruleList.add(rule);

        doReturn(true)
                .when(ruleNameService)
                .checkIfIdExists(rule.getId());

        doNothing()
                .when(ruleNameService)
                .deleteRuleNameById(rule.getId());

        doReturn(ruleList)
                .when(ruleNameService)
                .getAllRuleNames();
        //2. Act
        mockMvc.perform(get("/ruleName/delete/{id}", "1")
                .flashAttr("successDeleteMessage", "Your rule name was successfully deleted"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ruleName/list"))
                .andExpect(flash().attributeExists("successDeleteMessage"))
                .andReturn();
        assertTrue(ruleList.get(0).getTemplate().equals("Template"));
    }
}