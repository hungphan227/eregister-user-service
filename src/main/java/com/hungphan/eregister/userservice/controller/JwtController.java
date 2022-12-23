package com.hungphan.eregister.userservice.controller;

import com.hungphan.eregister.userservice.dto.JwtRequestModel;
import com.hungphan.eregister.userservice.dto.JwtResponseModel;
import com.hungphan.eregister.userservice.model.User;
import com.hungphan.eregister.userservice.repository.UserRepository;
import com.hungphan.eregister.userservice.utils.TokenManager;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request) throws Exception {
        String username = request.getUsername();
        User user = userRepository.findByUsername(username);
        if (user == null || !BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        final String jwtToken = tokenManager.generateJwtToken(user.getUsername());
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }

    @GetMapping("/check-authentication")
    ResponseEntity<String> isAuthenticated(@RequestHeader(HttpHeaders.AUTHORIZATION) String tokenHeader) {
        try {
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            String token = tokenHeader.substring(7);
            if (tokenManager.validateJwtToken(token)) return ResponseEntity.status(HttpStatus.OK).body(null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to get JWT Token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (ExpiredJwtException e) {
            System.out.println("JWT Token has expired");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/")
    ResponseEntity<JwtResponseModel> createUser(@RequestBody JwtRequestModel request) throws Exception {
        //BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
        return ResponseEntity.ok(null);
    }
}
