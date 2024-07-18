package lk.ijse.gdse.ticketservice.service;

import lk.ijse.gdse.ticketservice.dto.TicketDTO;
import java.util.List;
public interface TicketService {
    void save(TicketDTO ticketDTO);
    void update(String ticketId);
    List<TicketDTO> getAll();
    boolean isExistUser(String userId);
    boolean isExistVehicle(String vehicleId);
    boolean isExistTicket(String ticketId);
}
