package lk.ijse.gdse.userservice.conversion;

import lk.ijse.gdse.userservice.dto.UserDTO;
import lk.ijse.gdse.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConversionData {
    private final ModelMapper modelMapper;

    public User covertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}
