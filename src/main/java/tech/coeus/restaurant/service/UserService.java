package tech.coeus.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.coeus.restaurant.dto.request.RegisterRequest;
import tech.coeus.restaurant.dto.response.UserResponse;
import tech.coeus.restaurant.exception.ResourceNotFoundException;
import tech.coeus.restaurant.model.User;
import tech.coeus.restaurant.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setRole(registerRequest.getRole());

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getFullName(),
                savedUser.getRole()
        );
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getRole()             )).collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRole()
        );
    }

    public List<UserResponse> getUsersByRole(User.UserRole role) {
        return userRepository.findByRole(role).stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getRole()
                )).collect(Collectors.toList());
    }

    public UserResponse updateUser(Long id, RegisterRequest updateRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!user.getUsername().equals(updateRequest.getUsername()) &&
                userRepository.existsByUsername(updateRequest.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        user.setUsername(updateRequest.getUsername());
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }
        user.setFullName(updateRequest.getFullName());
        user.setRole(updateRequest.getRole());

        User updatedUser = userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getFullName(),
                updatedUser.getRole()
        );
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    //todo
    //new feature will be here
}