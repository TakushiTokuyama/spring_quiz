package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class QuizControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
    public void getIndexTest() throws Exception {

		mockMvc.perform(get("/question"))
		//urlのステータスを調べる 200番台成功
        .andExpect(status().isOk())
        //viewが正しいか？
        .andExpect(view().name("question"));

    }



}
