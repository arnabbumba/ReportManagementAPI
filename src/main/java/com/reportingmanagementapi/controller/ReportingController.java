package com.reportingmanagementapi.controller;

import com.reportingmanagementapi.model.CustomerDTO;
import com.reportingmanagementapi.model.CustomerRequest;
import com.reportingmanagementapi.model.CustomerRequestforUpdate;
import com.reportingmanagementapi.service.ReportingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@Slf4j
public class ReportingController {

    @Autowired
    ReportingService reportingService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getApplications(){
        log.info("Inside the ReportingController.getApplications");
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

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("Start ReportingController.saveCustomer");
        if (customerRequest == null) {
            log.info("Invalid Customer request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomerDTO customerDTO = null;
        try {
            customerDTO = reportingService.save(customerRequest);
            log.info("Customer details, customerDTO:{}", customerDTO);

            if (customerDTO == null) {
                log.info("Customer details not found for the customerDTO:{}", customerDTO);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Exception while getting saving customer details", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End ReportingController.saveCustomer");
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerRequestforUpdate customerRequest) {
        log.info("Start ReportingController.updateCustomer");
        if (customerRequest == null) {
            log.info("Invalid Customer request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomerDTO customerDTO = null;
        try {
            customerDTO = reportingService.updateCustomer(customerRequest);
            log.info("Customer details, customerDTO:{}", customerDTO);

            if (customerDTO == null) {
                log.info("Customer details not found for the customerDTO:{}", customerDTO);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            log.error("Exception while getting saving customer details", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End ReportingController.updateCustomer");
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("customerID") int customerID){
        log.info("Inside ReportingController.delete, customerID:{}", customerID);
        String response = null;
        if(customerID <=0){
            log.info("Invalid Customer Id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            reportingService.delete(customerID);

        }catch (Exception ex){
            log.error("Exception while deleting the Customer details", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }


}
