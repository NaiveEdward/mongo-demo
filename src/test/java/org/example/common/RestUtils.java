package org.example.common;

import org.springframework.web.client.RestTemplate;

public class RestUtils {

    private static RestTemplate restTemplate;

    public static RestTemplate getRestTemplate(){
        if(restTemplate!=null){
            return restTemplate;
        }else{
            restTemplate = new RestTemplate();
            return restTemplate;
        }
    }

}
