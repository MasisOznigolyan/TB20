package com.TBmail.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="News Checker", description="Controller for triggering the E-Mail system. Do not use it if crone is activated")
@RequestMapping("/tb")
public class MyController {

    @Autowired
    private ScheduledTask myMicroservice;

    @Operation(summary="check for new news", description="Checks trendbasket.net for possible new news for spesified category. Do not use it if crone is activated ")
    @GetMapping("/mail")
    public ResponseEntity<String> performMicroserviceAction() {
        
    	myMicroservice.sendMail();
    	String message = "Scheduled task triggered manually.";
    	return ResponseEntity.status(HttpStatus.OK).body(message);
    	
    }
}
