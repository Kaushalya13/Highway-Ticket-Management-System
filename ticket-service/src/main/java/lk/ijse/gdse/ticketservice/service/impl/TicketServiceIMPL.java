package lk.ijse.gdse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.ticketservice.Enum.TicketStatus;
import lk.ijse.gdse.ticketservice.conversion.ConversionData;
import lk.ijse.gdse.ticketservice.dto.TicketDTO;
import lk.ijse.gdse.ticketservice.entity.Ticket;
import lk.ijse.gdse.ticketservice.repository.TicketDAO;
import lk.ijse.gdse.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceIMPL implements TicketService {
    @Autowired
    private ConversionData conversionData;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public void save(TicketDTO ticketDTO) {
        ticketDAO.save(conversionData.convertToEntity(ticketDTO));
    }

    @Override
    public void update(String ticketId) {
        Optional<Ticket> id = ticketDAO.findById(ticketId);
        if (id.isPresent()){
            Ticket ticket = id.get();
            ticket.setStatus(TicketStatus.PAID);
            ticketDAO.save(ticket);
        }
    }

    @Override
    public List<TicketDTO> getAll() {
        return conversionData.convertToDTO(ticketDAO.findAll());
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
    public boolean isExistVehicle(String vehicleId) {
        String url = "http://vehicle-service/api/v1/vehicle/existVehicle/"+vehicleId;

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
        return ticketDAO.existsById(ticketId);
    }
}
