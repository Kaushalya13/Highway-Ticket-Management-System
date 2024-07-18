package lk.ijse.gdse.ticketservice.dto;

import lk.ijse.gdse.ticketservice.Enum.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTO {
    private String ticketId;
    private String vehicleId;
    private String userId;
    private Date issueDate;
    private TicketStatus status;
}