package com.homevalue.service;

import com.homevalue.model.Property;
import com.homevalue.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<Property> getAllProperties() {
        return repository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return repository.findById(id);
    }

    public List<Property> searchProperties(String query) {
        if (query == null || query.isBlank()) {
            return getAllProperties();
        }
        return repository.findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrPropertyTypeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                query,
                query,
                query,
                query
        );
    }

    public Property saveProperty(Property property) {
        return repository.save(property);
    }

    public boolean deleteProperty(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
