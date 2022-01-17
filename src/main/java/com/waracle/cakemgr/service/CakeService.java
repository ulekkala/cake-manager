package com.waracle.cakemgr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.model.CakeRequest;
import com.waracle.cakemgr.repository.CakeRepository;
import com.waracle.cakemgr.service.converter.CakeRequestToCakeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class CakeService {

    private final CakeRepository cakeRepository;
    private final CakeRequestToCakeConverter cakeRequestToCakeConverter;
    private final ObjectMapper objectMapper;

    public CakeService(CakeRepository cakeRepository, CakeRequestToCakeConverter cakeRequestToCakeConverter, ObjectMapper objectMapper) {
        this.cakeRepository = cakeRepository;
        this.cakeRequestToCakeConverter = cakeRequestToCakeConverter;
        this.objectMapper = objectMapper;
    }

    public List<Cake> getCakeList()
    {
        return cakeRepository.findAll(); //this returns List of Cakes
    }

    public String addCake(CakeRequest cakeRequest){
        Cake cake = cakeRequestToCakeConverter.apply(cakeRequest);
        cake = cakeRepository.save(cake);
        return cake.getTitle();
    }

    public byte[] downloadCakeList() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(getCakeList());
        return json.getBytes();
    }
}
