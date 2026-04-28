package com.homevalue.service;

import com.homevalue.model.CustomerRequest;
import com.homevalue.repository.CustomerRequestRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class CustomerRequestService {

    private final CustomerRequestRepository customerRequestRepository;

    public CustomerRequestService(CustomerRequestRepository customerRequestRepository) {
        this.customerRequestRepository = customerRequestRepository;
    }

    public List<CustomerRequest> getRequests(String email) {
        if (email == null || email.isBlank()) {
            return customerRequestRepository.findAllByOrderByCreatedAtDesc();
        }
        return customerRequestRepository.findByUserEmailOrderByCreatedAtDesc(email.trim().toLowerCase(Locale.ROOT));
    }

    public CustomerRequest create(CustomerRequest customerRequest) {
        customerRequest.setUserEmail(customerRequest.getUserEmail().trim().toLowerCase(Locale.ROOT));
        customerRequest.setUserName(customerRequest.getUserName().trim());
        customerRequest.setMessage(customerRequest.getMessage().trim());
        customerRequest.setPropertyType(trimToNull(customerRequest.getPropertyType()));
        customerRequest.setRequestGoal(trimToNull(customerRequest.getRequestGoal()));
        customerRequest.setBudgetRange(trimToNull(customerRequest.getBudgetRange()));
        customerRequest.setTimeline(trimToNull(customerRequest.getTimeline()));
        customerRequest.setRequirementType(trimToNull(customerRequest.getRequirementType()));
        customerRequest.setPhone(trimToNull(customerRequest.getPhone()));
        customerRequest.setCity(trimToNull(customerRequest.getCity()));
        if (customerRequest.getStatus() == null || customerRequest.getStatus().isBlank()) {
            customerRequest.setStatus("Pending");
        }
        return customerRequestRepository.save(customerRequest);
    }

    public CustomerRequest respond(Long id, String adminResponse, String status) {
        CustomerRequest customerRequest = customerRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        customerRequest.setAdminResponse(adminResponse == null ? null : adminResponse.trim());
        customerRequest.setStatus((status == null || status.isBlank()) ? "Answered" : status.trim());
        customerRequest.setRespondedAt(LocalDateTime.now());
        return customerRequestRepository.save(customerRequest);
    }

    public long countPending() {
        return customerRequestRepository.countByStatusIgnoreCase("Pending");
    }

    private String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
