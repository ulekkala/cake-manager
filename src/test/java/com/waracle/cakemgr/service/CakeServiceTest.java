package com.waracle.cakemgr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.model.CakeRequest;
import com.waracle.cakemgr.repository.CakeRepository;
import com.waracle.cakemgr.service.converter.CakeRequestToCakeConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    private CakeRequestToCakeConverter cakeRequestToCakeConverter;

    @InjectMocks
    private CakeService cakeService;

    @Test
    void getCakeListTest(){

        List<Cake> expected = new ArrayList<>();
        expected.add(Cake.builder().title("Chocolate").build());
        expected.add(Cake.builder().title("lemon").build());

        Mockito.when(cakeRepository.findAll()).thenReturn(expected);
        List<Cake> actual = cakeService.getCakeList();

        Assertions.assertEquals(expected.size(),actual.size());
    }

    @Test
    void addCakeTest(){
        CakeRequest cakeRequest = CakeRequest.builder().title("lemon").build();
        Cake cake = Cake.builder().title("lemon").build();
        Mockito.when(cakeRepository.save(Mockito.any())).thenReturn(cake);
        String actual = cakeService.addCake(cakeRequest);

        Assertions.assertEquals("lemon", actual);
    }

    @Test
    void downloadCakeListTest() throws JsonProcessingException {

        Mockito.when(objectMapper.writeValueAsString(Mockito.any())).thenReturn("cake");
        byte[] actual= cakeService.downloadCakeList();

        Assertions.assertEquals(4, actual.length);
    }
}
