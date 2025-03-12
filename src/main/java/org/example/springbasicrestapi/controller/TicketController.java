package org.example.springbasicrestapi.controller;

import org.example.springbasicrestapi.model.Entity.ApiResponse;
import org.example.springbasicrestapi.model.Entity.TicketStatus;
import org.example.springbasicrestapi.model.request.TicketResquest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.springbasicrestapi.model.Entity.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    List<Ticket> tickets=new ArrayList<>();

    int id=4;
    public TicketController() {
        tickets.add(new Ticket(1,"Sros Lina","2024-11-11","Station E","Station F",70,false,TicketStatus.CANCELED,"EF3"));
        tickets.add(new Ticket(2,"Pork Sivheng","2025-01-01","Station A","Station B",170,true,TicketStatus.COMPLETED,"AB1"));
        tickets.add(new Ticket(3,"Dara Sokchhay","2025-02-01","Station C","Station D",80,false,TicketStatus.BOOKED,"CD2"));
    }


    //i)   Create a Ticket
    @PostMapping
    public List<Ticket> postTickets(@RequestBody TicketResquest ticket) {
        tickets.add(new Ticket(id++,ticket.getPassengerName(),ticket.getTravelDate(),
                ticket.getSourceStation(),ticket.getDestinationStation(),ticket.getPrice(),ticket.isPaymentStatus(),
                ticket.getTicketStatus(),ticket.getSeatNumber()));
        return tickets;
    }


    //ii)   Retrieve All Tickets
    @GetMapping
    public ResponseEntity<ApiResponse<List<Ticket>>> getAllTickets() {
        ApiResponse<List<Ticket>> response =new ApiResponse<>(true,"Success",HttpStatus.OK,tickets, LocalDateTime.now());
        return ResponseEntity.ok(response);

    }




    //iii)   Retrieve a Ticket by Id(using @PathVariable)
    @GetMapping("/{ticket-Id}")
    public Ticket getTicketById(@PathVariable ("ticket-Id") Integer id) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId() == id) {
                return ticket;
            }
        }
        return null;
    }


    //iv)   Search for a Ticket by Passenger Name (using @RequestParam)
    @GetMapping("/search")
    public Ticket searchTickets(@RequestParam("search") String search) {
        for (Ticket ticket : tickets) {
            if (ticket.getPassengerName().toLowerCase().contains(search.toLowerCase())) {
                return ticket;
            }
        }
        return null;
    }


    //v)  Filter Tickets by Ticket Status and Travel Date (using @RequestParam)
    @GetMapping("/filter")
    public Ticket filterTickets(@RequestParam TicketStatus ticketStatus, @RequestParam String travelDate ) {
        for (Ticket ticket:tickets){
            if (ticket.getTicketStatus().equals(ticketStatus) && ticket.getTravelDate().equals(travelDate)){
                return ticket;
            }
        }
        return null;
    }



    //vi)  Update a Ticket by ID
    @PutMapping("/{ticket-id}")
    public Ticket updateTicket(@PathVariable ("ticket-id") Integer id,@RequestBody TicketResquest ticketResquest) {
        for(Ticket ticket : tickets) {
            if (ticket.getTicketId() == id) {
                ticket.setPassengerName(ticketResquest.getPassengerName());
                ticket.setTravelDate(ticketResquest.getTravelDate());
                ticket.setSourceStation(ticketResquest.getSourceStation());
                ticket.setDestinationStation(ticketResquest.getDestinationStation());
                ticket.setPrice(ticketResquest.getPrice());
                ticket.setPaymentStatus(ticketResquest.isPaymentStatus());
                ticket.setTicketStatus(ticketResquest.getTicketStatus());
                ticket.setSeatNumber(ticketResquest.getSeatNumber());
                return ticket;
            }
        }
        return null;
    }


    // vii)  Delete a Ticket by ID
    @DeleteMapping("/{ticket-id}")
    public void deleteTicket(@PathVariable ("ticket-id") Integer id){
        for(Ticket ticket:tickets) {
            if (ticket.getTicketId()==id){
                tickets.remove(ticket);
                return;
            }
        }

    }

    //Bonus



    //b)   Create multiple tickets in a single request.
    @PostMapping("/bulk")
    public List<Ticket> bulkTicket(@RequestBody List<Ticket> mulktickets) {
        tickets.addAll(id, mulktickets);
        return tickets;
    }

    // c)   Update payment status for multiple ticket IDs.

//    @PutMapping
//    public List<Ticket> updateBulkTicket(@RequestBody Boolean paymentStatus,@RequestBody Ticket id) {
//        if( )) {}
//
//        return tickets;
//    }



}
