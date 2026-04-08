package com.homevalue.service;
import com.homevalue.model.User;
import com.homevalue.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class AuthService implements CommandLineRunner {
    private final UserRepository repo;
    public AuthService(UserRepository repo){this.repo=repo;}
    public Optional<User> login(String email,String password){
        return repo.findByEmail(email).filter(u->u.getPassword().equals(password));
    }
    public User register(String email,String password){
        if(repo.findByEmail(email).isPresent()) throw new RuntimeException("Email already exists");
        User u=new User(); u.setEmail(email); u.setPassword(password); return repo.save(u);
    }
    @Override
    public void run(String... args){
        if(repo.findByEmail("admin@homevalue.com").isEmpty()){
            User a=new User(); a.setEmail("admin@homevalue.com"); a.setPassword("admin123"); a.setAdmin(true); repo.save(a);
        }
    }
}