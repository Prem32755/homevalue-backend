package com.homevalue.controller;

import com.homevalue.repository.CustomerRequestRepository;
import com.homevalue.repository.EnhancementRepository;
import com.homevalue.repository.PropertyRepository;
import com.homevalue.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final EnhancementRepository enhancementRepository;
    private final CustomerRequestRepository customerRequestRepository;

    public AdminController(
            UserRepository userRepository,
            PropertyRepository propertyRepository,
            EnhancementRepository enhancementRepository,
            CustomerRequestRepository customerRequestRepository
    ) {
        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
        this.enhancementRepository = enhancementRepository;
        this.customerRequestRepository = customerRequestRepository;
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalAssessments", propertyRepository.count());
        stats.put("avgValueIncrease", "Rs. 2,45,000");
        stats.put("activeRecommendations", enhancementRepository.count());
        stats.put("activeFeatures", 6);
        stats.put("totalRequests", customerRequestRepository.count());
        stats.put("pendingRequests", customerRequestRepository.countByStatusIgnoreCase("Pending"));
        return stats;
    }
}
