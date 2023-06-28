package com.reportingmanagementapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestforUpdate {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
}