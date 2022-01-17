package com.waracle.cakemgr.service.converter;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.model.CakeRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CakeRequestToCakeConverter implements Function<CakeRequest, Cake> {

    @Override
    public Cake apply(CakeRequest cakeRequest) {
        return Cake.builder()
                .title(cakeRequest.getTitle())
                .desc(cakeRequest.getDesc())
                .image(cakeRequest.getImage())
                .build();
    }
}
