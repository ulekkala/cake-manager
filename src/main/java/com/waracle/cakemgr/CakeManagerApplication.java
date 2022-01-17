package com.waracle.cakemgr;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@SpringBootApplication
public class CakeManagerApplication {

	private final CakeRepository cakeRepository;
	@Value("${cake.url}")
	private String cakeListUrl;
	private final RestTemplate restTemplate;

	public CakeManagerApplication(RestTemplate restTemplate, CakeRepository cakeRepository) {
		this.restTemplate = restTemplate;
		this.cakeRepository = cakeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CakeManagerApplication.class, args);
	}

	@PostConstruct
	public void saveCakes()
	{
		log.info(cakeListUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<List<Cake>> response = restTemplate.exchange(cakeListUrl, HttpMethod.GET,entity, new ParameterizedTypeReference<List<Cake>>() {});
		List<Cake> cakes = response.getBody();
		assert cakes != null;
		cakeRepository.saveAll(cakes);
	}
}
