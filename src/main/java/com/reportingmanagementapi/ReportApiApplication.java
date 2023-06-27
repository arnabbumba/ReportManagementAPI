package com.reportingmanagementapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class ReportApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportApiApplication.class, args);
        log.info("Report Management application has been started");
    }
}