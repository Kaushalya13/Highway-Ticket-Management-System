package lk.ijse.gdse.vehicleservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.vehicleservice.conversion.ConversionData;
import lk.ijse.gdse.vehicleservice.dto.VehicleDTO;
import lk.ijse.gdse.vehicleservice.repository.VehicleDAO;
import lk.ijse.gdse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@Transactional
public class VehicleServiceIMPL implements VehicleService {
    @Autowired
    private ConversionData conversionData;
    @Autowired
    private VehicleDAO vehicleDAO;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void save(VehicleDTO vehicleDTO) {
        vehicleDAO.save(conversionData.convertToEntity(vehicleDTO));
    }

    @Override
    public boolean isExistsUser(String userId) {
        String url = "http://user-service/api/v1/user/existUser/"+userId;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
            if (response.getStatusCode()== HttpStatus.OK){
                return true;
            }else {
                return false;
            }
        }catch (HttpClientErrorException e){
            return false;
        }
    }

    @Override
    public boolean isExistsVehicle(String vehicleId) {
        return vehicleDAO.existsById(vehicleId);
    }

    @Override
    public List<VehicleDTO> getAll() {
        return conversionData.convertToDTO(vehicleDAO.findAll());
    }
}
