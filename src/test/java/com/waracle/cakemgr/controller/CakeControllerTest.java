package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.model.CakeRequest;
import com.waracle.cakemgr.repository.CakeRepository;
import com.waracle.cakemgr.service.CakeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CakeControllerTest {

    @Mock
    private CakeService cakeService;

    @InjectMocks
    CakeController cakeController;

    @Test
    void getCakeListTest(){
        List<Cake> expected = new ArrayList<>();
        expected.add(Cake.builder().title("Chocolate").build());
        expected.add(Cake.builder().title("lemon").build());

        Mockito.when(cakeService.getCakeList()).thenReturn(expected);
        ResponseEntity<List<Cake>> actual = cakeController.getCakeList();
        Assertions.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void addCakeTest(){
        CakeRequest cakeRequest = CakeRequest.builder().title("lemon").build();

        Mockito.when(cakeService.addCake(Mockito.any())).thenReturn("lemon");
        ResponseEntity<String> actual = cakeController.addCake(cakeRequest);
        Assertions.assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void add_CakeTest(){
        CakeRequest cakeRequest = CakeRequest.builder().title("lemon").build();

        Mockito.when(cakeService.addCake(Mockito.any())).thenReturn("lemon");
        ResponseEntity<String> actual = cakeController.addCakes(cakeRequest);
        Assertions.assertEquals(HttpStatus.CREATED, actual.getStatusCode());
    }

    @Test
    void downloadCakeListTest() throws JsonProcessingException {
        String json = "Cake";
        Mockito.when(cakeService.downloadCakeList()).thenReturn(json.getBytes());
        ResponseEntity<byte[]> bytes = cakeController.downloadCakeList();
        Assertions.assertEquals(HttpStatus.OK,bytes.getStatusCode());
    }
}
