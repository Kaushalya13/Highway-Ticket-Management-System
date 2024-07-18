package lk.ijse.gdse.vehicleservice.conversion;

import lk.ijse.gdse.vehicleservice.dto.VehicleDTO;
import lk.ijse.gdse.vehicleservice.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversionData {
    private final ModelMapper modelMapper;

    public Vehicle convertToEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO,Vehicle.class);
    }

    public List<VehicleDTO> convertToDTO(List<Vehicle> vehicles){
        return modelMapper.map(vehicles,List.class);
    }
}
