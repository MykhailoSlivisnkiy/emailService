package com.example.emailservice.dto;

import lombok.Data;

@Data
public class SubscriptionRequest {
    String shopName;
    String destination;
}
