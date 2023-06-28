package com.reportingmanagementapi.service;

import com.reportingmanagementapi.model.CustomerDTO;
import com.reportingmanagementapi.model.CustomerRequest;
import com.reportingmanagementapi.model.CustomerRequestforUpdate;

import java.net.URISyntaxException;
import java.util.List;

public interface ReportingService {
    List<CustomerDTO> findAll() throws URISyntaxException;
    public CustomerDTO save(CustomerRequest customer);

    CustomerDTO updateCustomer(CustomerRequestforUpdate customerRequest);

    void delete(int customerID);
}
