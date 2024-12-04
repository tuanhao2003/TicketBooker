package com.example.ticketbooker.Service.ServiceImp;

import com.example.ticketbooker.DTO.Ticket.*;
import com.example.ticketbooker.DTO.Users.UserResponse;
import com.example.ticketbooker.Entity.Tickets;
import com.example.ticketbooker.Entity.Users;
import com.example.ticketbooker.Repository.TicketRepo;
import com.example.ticketbooker.Service.TicketService;
import com.example.ticketbooker.Util.Enum.TicketStatus;
import com.example.ticketbooker.Util.Mapper.TicketMapper;
import com.example.ticketbooker.Util.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImp implements TicketService {
    @Autowired
    private TicketRepo ticketRepository;

    @Override
    public boolean addTicket(AddTicketRequest dto) {
        try {
            Tickets ticket = TicketMapper.fromAdd(dto);
            ticketRepository.save(ticket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateTicket(UpdateTicketRequest dto) {
        try {
            Tickets ticket = TicketMapper.fromUpdate(dto);
            ticketRepository.save(ticket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTicket(TicketIdRequest dto) {
        try {
            ticketRepository.deleteById(dto.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public TicketResponse getAllTickets() {
        TicketResponse result = new TicketResponse();
        try {
            result = TicketMapper.toResponseDTO(this.ticketRepository.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public TicketResponse getTicketById(TicketIdRequest dto) {
        TicketResponse result = new TicketResponse();
        try {
            result = TicketMapper.toResponseDTO(this.ticketRepository.findAllById(dto.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    @Override
    public PaymentInforResponse getPaymentInfo(PaymentInforRequest request) {
        try {
            Optional<Tickets> ticketOptional = this.ticketRepository.findById(request.getTicketId());
            if (ticketOptional.isPresent()) {
                Tickets ticket = ticketOptional.get();

                // Kiểm tra trạng thái của ticket
                if (ticket.getTicketStatus() == TicketStatus.BOOKED) {
                    // Kiểm tra số điện thoại
                    if (ticket.getCustomerPhone().equals(request.getCustomerPhone())) {
                        return TicketMapper.toPaymentInfor(ticket);
                    } else {
                        System.out.println("Số điện thoại không trùng khớp.");
                    }
                } else {
                    System.out.println("Trạng thái ticket không hợp lệ: " + ticket.getTicketStatus());
                }
            } else {
                System.out.println("Không tìm thấy ticket với ID: " + request.getTicketId());
            }
        } catch (Exception e) {
            System.out.println("Lỗi trong quá trình xử lý: " + e.getMessage());
        }
        return null;
    }



    @Override
    public TicketResponse getTicketsByAccountId(int accountId) {
        TicketResponse result = new TicketResponse();
        try {
            List<Tickets> tickets = ticketRepository.findAllByBookerId(accountId);
            result.setTicketsCount(tickets.size());
            result.setListTickets(new ArrayList<>(tickets));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public TicketResponse searchTickets(int accountId, Integer ticketId, LocalDate departureDate, String route, TicketStatus status) {
        TicketResponse result = new TicketResponse();
        try {
            List<Tickets> tickets = ticketRepository.searchTickets(accountId, ticketId, departureDate, route, status);
            result.setTicketsCount(tickets.size());
            result.setListTickets(new ArrayList<>(tickets));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public TicketStatsDTO getTicketStats(String period, LocalDate selectedDate) {
        LocalDate previousDate = getPreviousDate(period, selectedDate);

        int currentPeriodCount = getTicketCountByPeriod(period, selectedDate);
        int previousPeriodCount = getTicketCountByPeriod(period, previousDate);

        return TicketStatsDTO.builder()
                .period(period)
                .selectedDate(selectedDate)
                .currentPeriodTicketCount(currentPeriodCount)
                .previousPeriodTicketCount(previousPeriodCount)
                .build();
    }


    private int getTicketCountByPeriod(String period, LocalDate date) {
        LocalDateTime start = null;
        LocalDateTime end = null;

        switch (period) {
            case "Day":
                start = date.atStartOfDay();
                end = date.plusDays(1).atStartOfDay();
                break;
            case "Month":
                start = date.withDayOfMonth(1).atStartOfDay();
                end = date.plusMonths(1).withDayOfMonth(1).atStartOfDay();
                break;
            case "Year":
                start = date.withDayOfYear(1).atStartOfDay();
                end = date.plusYears(1).withDayOfYear(1).atStartOfDay();
                break;
        }

        return ticketRepository.countTicketsByPaymentTimeBetween(start, end);
    }


    private LocalDate getPreviousDate(String period, LocalDate date) {
        switch (period) {
            case "Day":
                return date.minusDays(1);
            case "Month":
                return date.minusMonths(1);
            case "Year":
                return date.minusYears(1);
            default:
                return date;
        }
    }
}
