package com.waracle.cakemgr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.model.CakeRequest;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CakeController {

    private final CakeService cakeService;
    @Value("${file.name}")
    private String fileName;
    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cake>> getCakeList(){
        return ResponseEntity.status(HttpStatus.OK).body(cakeService.getCakeList());
    }

    @PostMapping("/")
    public ResponseEntity<String> addCake(@RequestBody CakeRequest cake){
        return ResponseEntity.status(HttpStatus.CREATED).body(cakeService.addCake(cake));
    }

    @PostMapping("/cakes")
    public ResponseEntity<String> addCakes(@RequestBody CakeRequest cake){
        return ResponseEntity.status(HttpStatus.CREATED).body(cakeService.addCake(cake));
    }

    @GetMapping("/cakes")
    public ResponseEntity<byte[]> downloadCakeList() throws JsonProcessingException {
        byte[] bytes = cakeService.downloadCakeList();
        HttpHeaders respHeaders = getHttpHeaders(bytes);
        return new ResponseEntity<>(bytes, respHeaders, HttpStatus.OK);
    }

    private HttpHeaders getHttpHeaders(byte[] bytes) {
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(bytes.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return respHeaders;
    }
}
