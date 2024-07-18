package lk.ijse.gdse.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.paymentservice.conversion.ConversionData;
import lk.ijse.gdse.paymentservice.dto.PaymentDTO;
import lk.ijse.gdse.paymentservice.repository.PaymentDAO;
import lk.ijse.gdse.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class PaymentServiceIMPL implements PaymentService {
    @Autowired
    private ConversionData conversionData;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public void save(PaymentDTO paymentDTO) {
        paidStatus(paymentDTO.getTicketId());
        paymentDAO.save(conversionData.convertToEntity(paymentDTO));
    }

    @Override
    public boolean isExistUser(String userId) {
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
    public boolean isExistTicket(String ticketId) {
        String url = "http://ticket-service/api/v1/ticket/existTicket/"+ticketId;

        try{
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

    private void paidStatus(String ticketId){
        String url = "http://ticket-service/api/v1/ticket/update/"+ticketId;
        restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
    }
}
