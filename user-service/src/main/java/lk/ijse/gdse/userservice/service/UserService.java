package lk.ijse.gdse.userservice.service;

import lk.ijse.gdse.userservice.dto.UserDTO;

public interface UserService {
    void save(UserDTO userDTO);
    boolean isUserExists(String userId);
    boolean checkCredential (UserDTO userDTO);
}
