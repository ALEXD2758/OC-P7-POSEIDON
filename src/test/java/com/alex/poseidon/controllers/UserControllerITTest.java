package com.alex.poseidon.controllers;

import com.alex.poseidon.models.UserModel;
import com.alex.poseidon.services.UserService;
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
public class UserControllerITTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webContext;

    @MockBean
    UserService userService;

    @Before
    public void setupMockmvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestUserListViewShouldReturnSuccess() throws Exception {
        //1. Setup

        UserModel user = new UserModel();
        user.setId(1);
        user.setUsername("admin@admin.com");
        user.setNonHashedPassword("Admininistrator12@%*");
        user.setFullname("Alexandre Dubois");
        user.setRole("ADMIN");

        List<UserModel> userList = new ArrayList<>();
        userList.add(user);

        doReturn(userList)
                .when(userService)
                .getAllUsers();

        //2. Act
        mockMvc.perform(get("/user/list"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"))
                .andReturn();
        assertTrue(userList.get(0).getRole().equals("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestUserAddViewShouldReturnSuccess() throws Exception {
        //1. Setup

        //2. Act
        mockMvc.perform(get("/user/add"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/add"))
                .andExpect(model().attributeExists("user"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void postRequestuserValidateShouldReturnSuccess() throws Exception {
        //1. Setup

        UserModel user = new UserModel();
        user.setId(1);
        user.setUsername("admin@admin.com");
        user.setNonHashedPassword("Admininistrator12@%*");
        user.setFullname("Alexandre Dubois");
        user.setRole("ADMIN");

        List<UserModel> userList = new ArrayList<>();
        userList.add(user);

        doNothing()
                .when(userService)
                .saveUser(user);

        doReturn(userList)
                .when(userService)
                .getAllUsers();


        //2. Act
        mockMvc.perform(post("/user/validate")
                .flashAttr("successSaveMessage", "Your user was successfully added")
                .param("Id", "1")
                .param("username", "admin@admin.com")
                .param("nonHashedPassword", "Admininistrator12@%*")
                .param("fullname", "Alexandre Dubois")
                .param("role", "ADMIN"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"))
                .andExpect(flash().attributeExists("successSaveMessage"))
                .andReturn();
        assertTrue(userList.get(0).getRole().equals("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestuserUpdateIdShouldReturnSuccess() throws Exception {
        //1. Setup

        UserModel user = new UserModel();
        user.setId(1);
        user.setUsername("admin@admin.com");
        user.setNonHashedPassword("Admininistrator12@%*");
        user.setFullname("Alexandre Dubois");
        user.setRole("ADMIN");

        doReturn(true)
                .when(userService)
                .checkIfUserExistsById(user.getId());

        doReturn(user)
                .when(userService)
                .getUserById(user.getId());
        //2. Act
        mockMvc.perform(get("/user/update/{id}", "1"))
        //3. Assert
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"))
                .andReturn();
        assertTrue(user.getRole().equals("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void postRequestuserUpdateIdShouldReturnSuccess() throws Exception {
        //1. Setup

        UserModel user = new UserModel();
        user.setId(1);
        user.setUsername("admin@admin.com");
        user.setNonHashedPassword("Admininistrator12@%*");
        user.setFullname("Alexandre Dubois");
        user.setRole("ADMIN");

        List<UserModel> userList = new ArrayList<>();
        userList.add(user);

        doReturn(true)
                .when(userService)
                .checkIfUserExistsById(user.getId());

        doNothing()
                .when(userService)
                .saveUser(user);

        doReturn(userList)
                .when(userService)
                .getAllUsers();

        //2. Act
        mockMvc.perform(post("/user/update/{id}", "1")
                .flashAttr("successUpdateMessage", "Your user was successfully updated")
                .param("Id", "1")
                .param("username", "admin@admin.com")
                .param("nonHashedPassword", "Admininistrator12@%*")
                .param("fullname", "Alexandre Dubois")
                .param("role", "ADMIN"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"))
                .andExpect(flash().attributeExists("successUpdateMessage"))
                .andReturn();
        assertTrue(userList.get(0).getRole().equals("ADMIN"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    public void getRequestuserDeleteIdShouldReturnSuccess() throws Exception {
        //1. Setup

        UserModel user = new UserModel();
        user.setId(1);
        user.setUsername("admin@admin.com");
        user.setNonHashedPassword("Admininistrator12@%*");
        user.setFullname("Alexandre Dubois");
        user.setRole("ADMIN");

        List<UserModel> userList = new ArrayList<>();
        userList.add(user);

        doReturn(true)
                .when(userService)
                .checkIfUserExistsById(user.getId());

        doNothing()
                .when(userService)
                .deleteUserById(user.getId());

        doReturn(userList)
                .when(userService)
                .getAllUsers();
        //2. Act
        mockMvc.perform(get("/user/delete/{id}", "1")
                .flashAttr("successDeleteMessage", "Your user was successfully deleted"))
        //3. Assert
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"))
                .andExpect(flash().attributeExists("successDeleteMessage"))
                .andReturn();
        assertTrue(userList.get(0).getRole().equals("ADMIN"));
    }
}