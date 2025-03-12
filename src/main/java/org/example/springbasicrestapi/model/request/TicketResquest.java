package org.example.springbasicrestapi.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.springbasicrestapi.model.Entity.TicketStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TicketResquest {
    private String passengerName;
    private String travelDate;
    private String sourceStation;
    private String destinationStation;
    private double price;
    private boolean paymentStatus;
    private TicketStatus ticketStatus;
    private String seatNumber;
}
