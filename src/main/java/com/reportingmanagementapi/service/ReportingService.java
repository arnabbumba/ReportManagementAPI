package com.reportingmanagementapi.service;

import com.reportingmanagementapi.model.CustomerDTO;

import java.net.URISyntaxException;
import java.util.List;

public interface ReportingService {
    List<CustomerDTO> findAll() throws URISyntaxException;
}
