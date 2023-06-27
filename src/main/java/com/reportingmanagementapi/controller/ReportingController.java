package com.reportingmanagementapi.controller;

import com.reportingmanagementapi.model.CustomerDTO;
import com.reportingmanagementapi.service.ReportingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@Slf4j
public class ReportingController {

    @Autowired
    ReportingService reportingService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getApplications(){
        log.info("Inside the ApplicationController.getApplications");
        List<CustomerDTO> customerDTOList = null;
        try {
            customerDTOList = reportingService.findAll();
            log.info("Customer response:{}", customerDTOList );
            if(CollectionUtils.isEmpty(customerDTOList)){
                log.info("Customer details are not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            log.error("Exception while calling getApplications", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<CustomerDTO>>(customerDTOList, HttpStatus.OK);
    }


}
