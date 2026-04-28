package com.homevalue.controller;

import com.homevalue.model.CustomerRequest;
import com.homevalue.service.CustomerRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requests")
public class CustomerRequestController {

    private final CustomerRequestService customerRequestService;

    public CustomerRequestController(CustomerRequestService customerRequestService) {
        this.customerRequestService = customerRequestService;
    }

    @GetMapping
    public List<CustomerRequest> getRequests(@RequestParam(required = false) String email) {
        return customerRequestService.getRequests(email);
    }

    @PostMapping
    public ResponseEntity<?> createRequest(@RequestBody CustomerRequest customerRequest) {
        if (customerRequest.getUserEmail() == null || customerRequest.getUserEmail().isBlank()) {
            return ResponseEntity.badRequest().body("User email is required");
        }
        if (customerRequest.getUserName() == null || customerRequest.getUserName().isBlank()) {
            return ResponseEntity.badRequest().body("User name is required");
        }
        if (customerRequest.getRequestGoal() == null || customerRequest.getRequestGoal().isBlank()) {
            return ResponseEntity.badRequest().body("Request goal is required");
        }
        if (customerRequest.getMessage() == null || customerRequest.getMessage().isBlank()) {
            return ResponseEntity.badRequest().body("Message is required");
        }
        return ResponseEntity.ok(customerRequestService.create(customerRequest));
    }

    @PutMapping("/{id}/respond")
    public ResponseEntity<?> respondToRequest(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String adminResponse = body.get("adminResponse");
        if (adminResponse == null || adminResponse.isBlank()) {
            return ResponseEntity.badRequest().body("Admin response is required");
        }
        return ResponseEntity.ok(customerRequestService.respond(id, adminResponse, body.get("status")));
    }
}
