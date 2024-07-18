package lk.ijse.gdse.paymentservice.service;

import lk.ijse.gdse.paymentservice.dto.PaymentDTO;

public interface PaymentService {
    void save(PaymentDTO paymentDTO);
    boolean isExistUser(String userId);
    boolean isExistTicket(String ticketId);

}
