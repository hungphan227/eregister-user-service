package com.hungphan.eregister.userservice.repository;

import com.hungphan.eregister.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
}
