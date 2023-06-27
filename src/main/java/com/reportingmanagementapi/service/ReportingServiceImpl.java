package com.reportingmanagementapi.service;

import com.reportingmanagementapi.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ReportingServiceImpl implements ReportingService{
    @Autowired
    //RestTemplate restTemplate;

    @Value("${application.url}")
    private String applicationUrl;

    @Override
    public List<CustomerDTO> findAll() throws URISyntaxException {
        log.info("Inside ReportingServiceImpl.findAll, and applicationGetAllUrl:{}",applicationUrl );
        WebClient webClient = WebClient.create(applicationUrl);
        Flux<CustomerDTO> result = webClient.get()
                .retrieve()
                .bodyToFlux(CustomerDTO.class);
        return result.collectList().block();
    }
}
