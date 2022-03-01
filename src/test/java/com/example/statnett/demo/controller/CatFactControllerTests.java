package com.example.statnett.demo.controller;


import com.example.statnett.demo.exception.CatFactNotFoundException;
import com.example.statnett.demo.model.CatFact;
import com.example.statnett.demo.repository.CatFactRepository;
import com.example.statnett.demo.service.CatFactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CatFactController.class)
@ActiveProfiles("test")
public class CatFactControllerTests {

    private static final String MOCKED_CAT_FACTS = "[{\"id\":null,\"text\":\"text\",\"source\":\"source\",\"updatedAt\":" +
            "\"2000-01-01T01:01:01.000000001+01:00\",\"type\":\"type\",\"createdAt\":\"2000-01-01T01:01:01.000000001+01:00\"," +
            "\"user\":\"user\"},{\"id\":null,\"text\":\"text2\",\"source\":\"source2\",\"updatedAt\":\"2000-01-01T01:01:01.000000001+01:00\"," +
            "\"type\":\"type2\",\"createdAt\":\"2000-01-01T01:01:01.000000001+01:00\",\"user\":\"user2\"}]";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatFactService service;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private CatFactRepository catFactRepository;

    @Test
    public void givenStoredCatFacts_WhenGettingAll_thenShouldReturnAllCatFacts() throws Exception {
        List<CatFact> catFacts = createCatFacts();
        when(service.allFacts()).thenReturn(catFacts);

        this.mockMvc.perform(get("/cat-facts"))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content().string(MOCKED_CAT_FACTS));
    }

    @Test
    public void givenNoCatFacts_WhenFilteringAfterId_thenShouldReturnError() throws Exception {
        Long catFactId = 3L;
        when(service.findFactGivenID(catFactId)).thenThrow(new CatFactNotFoundException(catFactId));

        this.mockMvc.perform(get("/cat-facts/" + catFactId))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }


    private static List<CatFact> createCatFacts() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneId.of("Europe/Oslo"));

        CatFact catFactOne = new CatFact("user", "text", "source", zonedDateTime, "type", zonedDateTime);
        CatFact catFactTwo = new CatFact("user2", "text2", "source2", zonedDateTime, "type2", zonedDateTime);
        return asList(catFactOne, catFactTwo);
    }

}
