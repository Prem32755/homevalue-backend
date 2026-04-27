package com.homevalue.repository;

import com.homevalue.model.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, Long> {
    List<CustomerRequest> findAllByOrderByCreatedAtDesc();

    List<CustomerRequest> findByUserEmailOrderByCreatedAtDesc(String userEmail);

    long countByStatusIgnoreCase(String status);
}
