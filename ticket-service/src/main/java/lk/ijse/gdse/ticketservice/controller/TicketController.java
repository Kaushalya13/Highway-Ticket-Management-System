package lk.ijse.gdse.ticketservice.controller;

import lk.ijse.gdse.ticketservice.Enum.TicketStatus;
import lk.ijse.gdse.ticketservice.dto.TicketDTO;
import lk.ijse.gdse.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @GetMapping("/health")
    public String healthCheck(){
        return "Ticket Health Check";
    }

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody TicketDTO ticketDTO){
        ticketDTO.setStatus(TicketStatus.UNPAID);
        if (ticketService.isExistUser(ticketDTO.getUserId())){
            if (ticketService.isExistVehicle(ticketDTO.getVehicleId())){
                ticketService.save(ticketDTO);
                return ResponseEntity.ok("Ticket Added Successfully");
            }else {
                return ResponseEntity.ok("Vehicle does not exist ");
            }
        }else {
            return ResponseEntity.ok("User does not exits");
        }
    }

    @PutMapping("/update/{ticketId}")
    public ResponseEntity<?> updateTicketStatus(@PathVariable String ticketId){
        ticketService.update(ticketId);
        return ResponseEntity.ok("Ticket Status Updated Successfully");
    }

    @GetMapping("/getAllTicket")
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(ticketService.getAll());
    }


    @GetMapping("/existTicket/{ticketId}")
    public ResponseEntity<?> existTicket(@PathVariable String ticketId){
        if (ticketService.isExistTicket(ticketId)){
            return ResponseEntity.ok("Ticket Exists");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket does not Exists");
        }
    }
}
