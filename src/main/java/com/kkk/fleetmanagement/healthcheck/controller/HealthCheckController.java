package com.kkk.fleetmanagement.healthcheck.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus( HttpStatus.OK )
public class HealthCheckController {

    @GetMapping("/health")
    public void health(){}
}
