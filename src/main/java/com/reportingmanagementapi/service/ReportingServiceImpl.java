package com.reportingmanagementapi.service;

import com.reportingmanagementapi.model.CustomerDTO;
import com.reportingmanagementapi.model.CustomerRequest;
import com.reportingmanagementapi.model.CustomerRequestforUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@EnableRetry
public class ReportingServiceImpl implements ReportingService{
    @Autowired
    //RestTemplate restTemplate;

    @Value("${application.url}")
    private String applicationUrl;

    @Override
    @Retryable(value = {Exception.class}, maxAttempts = 5, backoff = @Backoff(delay = 6000))
    public List<CustomerDTO> findAll() throws URISyntaxException {
        log.info("Inside ReportingServiceImpl.findAll, and applicationGetAllUrl:{}",applicationUrl );
        WebClient webClient = WebClient.create(applicationUrl);
        Flux<CustomerDTO> result = webClient.get()
                .retrieve()
                .bodyToFlux(CustomerDTO.class);
        return result.collectList().block();
    }

    @Recover
    public List<CustomerDTO> recover( Exception ex) throws URISyntaxException {
        System.out.println("Inside Recover.....");
        return null;
    }


        public CustomerDTO save(CustomerRequest customer){
        log.info("Inside ReportingServiceIm.save, request:{}", customer);
        WebClient webClient = WebClient.create(applicationUrl);

        Mono<CustomerDTO> response = webClient.post()
                .body(Mono.just(customer), CustomerDTO.class)
                .retrieve()
                .bodyToMono(CustomerDTO.class);
        return response.block();
    }

    @Override
    public CustomerDTO updateCustomer(CustomerRequestforUpdate customerRequest) {
        log.info("Inside ReportingServiceIm.updateCustomer, request:{}", customerRequest);
        WebClient webClient = WebClient.create(applicationUrl);

        Mono<CustomerDTO> response = webClient.put()
                .body(Mono.just(customerRequest), CustomerRequestforUpdate.class)
                .retrieve()
                .bodyToMono(CustomerDTO.class);
        return response.block();
    }

    @Override
    public void delete(int customerID) {
        log.info("Inside ReportingServiceIm.delete, request:{}", customerID);
        WebClient webClient = WebClient.create(applicationUrl+"?customerID="+ customerID);

        Mono<String> response = webClient.delete()
                .retrieve()
                .bodyToMono(String.class);
        response.block();
    }
}
