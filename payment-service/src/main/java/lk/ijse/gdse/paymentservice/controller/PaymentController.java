package lk.ijse.gdse.paymentservice.controller;

import lk.ijse.gdse.paymentservice.dto.PaymentDTO;
import lk.ijse.gdse.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/health")
    public String healthCheck(){
        return "Payment Health Check";
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PaymentDTO paymentDTO){
        if (paymentService.isExistUser(paymentDTO.getUserId())){
            if (paymentService.isExistTicket(paymentDTO.getTicketId())){
                paymentService.save(paymentDTO);
                return ResponseEntity.ok("Payment Successfully");
            }else {
                return ResponseEntity.ok("Ticket does not exist");
            }
        }else {
            return ResponseEntity.ok("User does not exist");
        }
    }
}
