package com.reportingmanagementapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {
    private String customerName;
    private String customerAddress;
    private String customerPhone;
}