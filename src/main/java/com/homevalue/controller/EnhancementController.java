package com.homevalue.controller;
import com.homevalue.model.Enhancement;
import com.homevalue.service.EnhancementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/enhancements")
public class EnhancementController {
    private final EnhancementService service;
    public EnhancementController(EnhancementService service){this.service=service;}
    @GetMapping
    public List<Enhancement> getAll(){return service.getAll();}
    @GetMapping("/{id}")
    public ResponseEntity<Enhancement> getById(@PathVariable String id){
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}