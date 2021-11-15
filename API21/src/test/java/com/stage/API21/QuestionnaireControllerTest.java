package com.stage.API21;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionnaireControllerTest {
	
	@Autowired
	public MockMvc mockMvc;
	
	/*
	@Test
	public void testGetQuestionnaires() throws Exception{
		mockMvc.perform(get("/questionnaires")).andExpect(status().isOk()).andExpect(jsonPath("$[*].nom", is("Modèle de sondage sur les anciens élèves")));
	}*/
	
	@Test
    public void testGetQuestionnaires() throws Exception {

        mockMvc.perform(get("/questionnaires"))

            .andExpect(status().isOk())

            .andExpect(jsonPath("$[0].nom", is("Modèle de sondage sur les anciens élèves")));

    }
	
}
