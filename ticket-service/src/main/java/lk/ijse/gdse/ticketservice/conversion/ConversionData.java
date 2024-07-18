package lk.ijse.gdse.ticketservice.conversion;

import lk.ijse.gdse.ticketservice.dto.TicketDTO;
import lk.ijse.gdse.ticketservice.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversionData {
    private final ModelMapper modelMapper;

    public Ticket convertToEntity(TicketDTO ticketDTO){
        return modelMapper.map(ticketDTO, Ticket.class);
    }

    public List<TicketDTO> convertToDTO(List<Ticket> tickets){
        return modelMapper.map(tickets, List.class);
    }
}
