package tech.org.expensecalculator.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import tech.org.expensecalculator.config.JwtService;
import tech.org.expensecalculator.controller.AuthenticationRequest;
import tech.org.expensecalculator.controller.AuthenticationResponse;
import tech.org.expensecalculator.controller.RegisterRequest;
import tech.org.expensecalculator.model.Role;
import tech.org.expensecalculator.model.User;
import tech.org.expensecalculator.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final  UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public User findUserById(Long userId) {
        User retrievedUser = userRepo.findUserByUserId(userId);
        if(retrievedUser==null){
            System.out.println("User Not Registered");
            return null;
        }
        return retrievedUser;
    }

    public List<User> findAllUsers(){
        List<User> userList = userRepo.findAll();
        return userList;
    }

    public AuthenticationResponse createUser(RegisterRequest request){
        var user = User
                .builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getUserId())
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail()).
                orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getUserId())
                .build();
    }
}
