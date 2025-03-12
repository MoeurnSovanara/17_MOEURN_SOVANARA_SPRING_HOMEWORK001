package org.example.springbasicrestapi.model.Entity;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    private static int count=1;
    private int ticketId;
    private String passengerName;
    private String travelDate;
    private String sourceStation;
    private String destinationStation;
    private double price;
    private boolean paymentStatus;
    private TicketStatus ticketStatus;
    private String seatNumber;

    public Ticket(String passengerName, String travelDate, String sourceStation, String destinationStation, double price, boolean paymentStatus, TicketStatus ticketStatus, String seatNumber) {
        this.ticketId = count++;
        this.passengerName = passengerName;
        this.travelDate = travelDate;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.price = price;
        this.paymentStatus = paymentStatus;
        this.ticketStatus = ticketStatus;
        this.seatNumber = seatNumber;
    }
}
