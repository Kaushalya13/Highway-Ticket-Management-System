package lk.ijse.gdse.userservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.userservice.conversion.ConversionData;
import lk.ijse.gdse.userservice.dto.UserDTO;
import lk.ijse.gdse.userservice.repository.UserDAO;
import lk.ijse.gdse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ConversionData conversionData;
    @Override
    public void save(UserDTO userDTO) {
        userDAO.save(conversionData.covertToEntity(userDTO));
    }

    @Override
    public boolean isUserExists(String userId) {
        return userDAO.existsById(userId);
    }

    @Override
    public boolean checkCredential(UserDTO userDTO) {
        return userDAO.existsByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
    }
}
