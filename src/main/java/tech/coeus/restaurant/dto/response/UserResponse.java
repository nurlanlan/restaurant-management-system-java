package tech.coeus.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.coeus.restaurant.model.User;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private User.UserRole role;
}