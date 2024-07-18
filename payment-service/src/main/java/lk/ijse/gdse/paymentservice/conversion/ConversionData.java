package lk.ijse.gdse.paymentservice.conversion;

import lk.ijse.gdse.paymentservice.dto.PaymentDTO;
import lk.ijse.gdse.paymentservice.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversionData {
    private final ModelMapper modelMapper;

    public Payment convertToEntity(PaymentDTO paymentDTO){
        return modelMapper.map(paymentDTO, Payment.class);
    }

    public List<PaymentDTO> convertToDTO(List<Payment> payments){
        return modelMapper.map(payments, List.class);
    }
}
