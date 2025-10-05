package com.example.Order.Product;

import com.example.Order.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.cloud.openfeign.encoding.HttpEncoding.CONTENT_TYPE;

public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    private RestTemplate restTemplate;
    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> purchaseRequests) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, "application/json");
        HttpEntity<List<PurchaseRequest>> request = new HttpEntity<>(purchaseRequests, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PurchaseResponse>> response = restTemplate.exchange(productUrl +"/purchase", HttpMethod.POST, request, responseType);
        if(response.getStatusCode().isError()) {
            throw new BusinessException("Error occured in purchase");
        }
return  response.getBody();

    }
}
