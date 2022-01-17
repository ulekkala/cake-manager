package com.waracle.cakemgr.service.converter;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.model.CakeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CakeRequestToCakeConverterTest {

    CakeRequestToCakeConverter cakeRequestToCakeConverter = new CakeRequestToCakeConverter();

    @Test
    void applyTest(){
        CakeRequest cakeRequest = CakeRequest.builder().title("lemon").build();
        Cake actual = cakeRequestToCakeConverter.apply(cakeRequest);
        Assertions.assertEquals("lemon", actual.getTitle());
    }

}
