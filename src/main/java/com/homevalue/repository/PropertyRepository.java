package com.homevalue.repository;

import com.homevalue.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByLocationContainingIgnoreCaseOrPropertyTypeContainingIgnoreCase(String location, String propertyType);

    List<Property> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrPropertyTypeContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title,
            String location,
            String propertyType,
            String description
    );
}
