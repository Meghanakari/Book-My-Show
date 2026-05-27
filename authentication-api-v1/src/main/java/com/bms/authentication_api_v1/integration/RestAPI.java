package com.bms.authentication_api_v1.integration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

public abstract class RestAPI {

    @Autowired
    RestTemplate restTemplate;

    public String addQueryParams(String url, Map<String, String> queryParams){
        if(queryParams.size() == 0){
            return url;
        }
        url += "?";
        int count = 1;
        for(String key : queryParams.keySet()){
            url += (key + "=" + queryParams.get(key));
            if(count < queryParams.size()){
                url += "&";
            }
            count++;
        }
        return url;
    }

    public Object makePostCall(
            String apiBaseUrl,
            String apiEndPoint,
            Object requestBody,
            Map<String, String> queryParams){
        String url = apiBaseUrl + apiEndPoint;
        url = this.addQueryParams(url, queryParams);
        URI finalUrl = URI.create(url);
        RequestEntity requestEntity = RequestEntity.post(finalUrl).body(requestBody);
        //RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(finalUrl, HttpMethod.POST, requestEntity, Object.class);
        return response.getBody();
    }
    public Object makeGetCall(String apiBaseUrl, String apiEndPoint, Map<String, String> queryParams){
        String url = apiBaseUrl + apiEndPoint;
        url = this.addQueryParams(url, queryParams);
        RequestEntity requestEntity = RequestEntity.get(url).build();
        //RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
        return response.getBody();
    }
    public Object makePutCall(String apiBaseUrl, String apiEndPoint, Object requestBody, Map<String, String> queryParams){
        String url = apiBaseUrl + apiEndPoint;
        url = this.addQueryParams(url, queryParams);
        URI finalUrl = URI.create(url);
        RequestEntity requestEntity = RequestEntity.put(finalUrl).body(requestBody);
        //RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, requestEntity, Object.class);
        return response.getBody();
    }
    public Object makeDeleteCall(String apiBaseUrl, String apiEndPoint, Map<String, String> queryParams){
        String url = apiBaseUrl + apiEndPoint;
        url = this.addQueryParams(url, queryParams);
        RequestEntity requestEntity = RequestEntity.delete(url).build();
        //RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Object.class);
        return response.getBody();
    }
}
