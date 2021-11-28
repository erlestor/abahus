package rest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.test.context.ContextConfiguration;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import static org.junit.jupiter.api.Assertions.;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import core.House;
import core.Main;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { AbahusApplication.class, AbahusService.class, AbahusController.class })
@WebMvcTest
@TestInstance(Lifecycle.PER_CLASS)
public class AbahusApplicationTest {

    @Autowired
    private MockMvc mvc;

    //Test for logInMethod in RestAPI
    @Test
    @Order(1)
    public void testLogIn() throws Exception {
        JSONObject body = new JSONObject();
        body.put("email", "email@email.com");
        body.put("password", "passord");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/logIn")
                .content(body.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals('"' + "email@email.com" + '"', result.getResponse().getContentAsString());
    }

    //Test for logOutMethod in RestAPI
    @Test
    @Order(2)
    public void testLogOut() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/logOut")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals('"' + "'You are logged out'" + '"', result.getResponse().getContentAsString());
    }

    //Test for RegisterMethod in RestAPI
    @Test
    @Order(3)
    public void testRegisterUser() throws Exception {

        JSONObject body = new JSONObject();
        body.put("email", "Nissemor@gmail.com");
        body.put("password", "123");
        body.put("confirmPassword", "123");
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/registerUser")
                .content(body.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals('"' + "Nissemor@gmail.com" + '"', result.getResponse().getContentAsString());
        
    }

    //Testing AddHouse in restAPI
    @Test
    @Order(4)
    public void testAddHouse() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/addHouse/Gloeshaugen1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals("\"'House is added'\"", result.getResponse().getContentAsString());

        //Testing that the house is in fact added to Houses.json
        List<House> houses = getHousesTest();

        boolean found = false;
        
        for (House h: houses){
            if (h.getLocation().equals("Gloeshaugen1")){
                found = true;
            }
        }

        assertTrue(found);
    }

    //Test of setAvailable
    @Test
    @Order(5)
    public void testSetAvailable() throws Exception{
        MvcResult result = this.mvc.perform(MockMvcRequestBuilders
                .post("/setAvailable/Gloeshaugen1/false")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        assertEquals('"' + "House is altered" + '"', result.getResponse().getContentAsString());

        //Testing if the house is in fact set as available and unavailable in Data/Houses
        List<House> houses = getHousesTest();

        for (House h: houses){
            if (h.getLocation().equals("Gloeshaugen1")){
                assertFalse(h.isAvailable());
            }
        }

        this.mvc.perform(MockMvcRequestBuilders
                .post("/setAvailable/Gloeshaugen1/true")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<House> newHouses = getHousesTest();

        for (House h: newHouses){
            if (h.getLocation().equals("Gloeshaugen1")){
                assertTrue(h.isAvailable());
            }
        }
    }

    //Private method for getting all houses from data/houses
    private List<House> getHousesTest() throws JsonParseException, JsonMappingException, IOException{
        Main m = new Main();
        return m.getHousing();
    }

    //test for removing house
    @Test
    @Order(6)
    public void testRemoveHouse() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders
                .delete("/removeHouse/Gloeshaugen1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<House> houses = getHousesTest();

        //testing that the house is removed
        for (House h : houses) {
            assertNotEquals(h.getLocation(), "Gloeshaugen1");
        }

    }

    //Test for getting all houses
    @Test
    @Order(7)
    public void testGetHouses() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/houses")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        //testing that the result is the same as Data/houses
        Main m2 = new Main();
        List<House> houses = m2.getHousing();

        JSONObject response = new JSONObject(result.getResponse().getContentAsString());

        for (int i = 0; i < houses.size() - 1; i++) {
            assertTrue(response.has(houses.get(i).getLocation()));
        }
    }

    //Removing the new user so the test can run again
    @AfterAll
    public void tearDown() throws IOException {
        Main m = new Main();
        m.logInUser("Nissemor@gmail.com", "123");
        m.removeUser();
    }

}
