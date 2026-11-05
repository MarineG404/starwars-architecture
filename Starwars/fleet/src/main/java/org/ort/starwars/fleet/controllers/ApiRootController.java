package org.ort.starwars.fleet.controllers;

import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.ort.starwars.fleet.configuration.Settings;



@RestController 
public class ApiRootController {

    @Autowired 
    private Settings settings;
    
    @GetMapping("/api")
    public String get() {
        return "Hello world !";
    }

}
