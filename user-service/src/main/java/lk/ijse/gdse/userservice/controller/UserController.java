package lk.ijse.gdse.userservice.controller;

import lk.ijse.gdse.userservice.dto.UserDTO;
import lk.ijse.gdse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/health")
    public String healthCheck(){
        return "User Health Check";
    }

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO){
        userService.save(userDTO);
        return ResponseEntity.ok("User Save Successfully");
    }


    @PutMapping(value = "/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO){
            userService.save(userDTO);
            return ResponseEntity.ok("User Update Successfully");
    }

    @GetMapping("/existUser/{userId}")
    public ResponseEntity<?> existUser(@PathVariable String userId){
        if(userService.isUserExists(userId)){
            return ResponseEntity.ok("User Exits");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }

    @PostMapping("/checkCredential")
    public ResponseEntity<?> checkCredential(@RequestBody UserDTO userDTO){
        if (userService.checkCredential(userDTO)){
            return ResponseEntity.ok("User Exists");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }
    }
}
