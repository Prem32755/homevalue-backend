package com.homevalue.service;

import com.homevalue.model.Enhancement;
import com.homevalue.repository.EnhancementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnhancementService {

    private final EnhancementRepository enhancementRepository;

    public EnhancementService(EnhancementRepository enhancementRepository) {
        this.enhancementRepository = enhancementRepository;
    }

    public List<Enhancement> getAll() {
        return enhancementRepository.findAll();
    }

    public Optional<Enhancement> getById(String id) {
        return enhancementRepository.findById(id);
    }

    public Enhancement save(Enhancement enhancement) {
        return enhancementRepository.save(enhancement);
    }
}
