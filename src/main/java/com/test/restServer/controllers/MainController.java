package com.test.restServer.controllers;

import com.test.restServer.models.ApiClient;
import com.test.restServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private ApiClient apiClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public List<User> getUsers(){

        HttpHeaders requestHeaders = new HttpHeaders();
       // requestHeaders.add("Authorization", authorizationHeader);
       // HttpHeaders headers = new HttpHeaders();
       // headers.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<User> requestEntity = new HttpEntity<>(requestHeaders);

        List<User> user = restTemplate.exchange(
                "http://localhost:8080/api/all",
                HttpMethod.GET,
                requestEntity,
                List.class
        ).getBody();

        return user;
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public User saveUsers(@RequestBody User user){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<User> requestEntity = new HttpEntity<>(user,requestHeaders);

        User savedUser = restTemplate.exchange(
                "http://localhost:8080/api/save",
                HttpMethod.POST,
                requestEntity,
                User.class
                ).getBody();

        return savedUser;
    }
    @RequestMapping(value = "/update/{Id}", method = RequestMethod.PUT, produces = "application/json")
    public Boolean updateUsers(@PathVariable String Id ,@RequestBody User user) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<User> requestEntity = new HttpEntity<>(user,requestHeaders);
        String url = "http://localhost:8080/api/user/update/"+Id;

        Boolean savedUser = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Boolean.class
        ).getBody();

        return savedUser;
    }
}
