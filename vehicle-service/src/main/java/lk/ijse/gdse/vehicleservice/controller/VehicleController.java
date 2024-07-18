package lk.ijse.gdse.vehicleservice.controller;

import lk.ijse.gdse.vehicleservice.dto.VehicleDTO;
import lk.ijse.gdse.vehicleservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @GetMapping("/health")
    public String healthCheck(){
        return "Vehicle Health Check";
    }

    @PostMapping (value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody VehicleDTO vehicleDTO){
        if (vehicleService.isExistsUser(vehicleDTO.getUserId())){
            vehicleService.save(vehicleDTO);
            return ResponseEntity.ok("Vehicle Saved Successfully");
        }else {
            return ResponseEntity.ok("User does not exists");
        }
    }

    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody VehicleDTO vehicleDTO){
        vehicleService.save(vehicleDTO);
        return ResponseEntity.ok("Vehicle Update Successfully");
    }

    @GetMapping("/existVehicle/{vehicleId}")
    public ResponseEntity<?> existVehicle(@PathVariable String vehicleId) {
        if (vehicleService.isExistsVehicle(vehicleId)){
            return ResponseEntity.ok("Vehicle exists");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle does not exist");
        }
    }

    @GetMapping("/getAllVehicle")
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(vehicleService.getAll());
    }



}
