package com.example.ticketbooker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartData {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;
    private Long ticketCount;
    private Long orderCount;
    private BigDecimal revenue;

    // Constructor matching the SELECT new query
    public ChartData(java.sql.Date paymentDate, long ticketCount, long orderCount, BigDecimal revenue) {
        this.paymentDate = paymentDate.toLocalDate();
        this.ticketCount = ticketCount;
        this.orderCount = orderCount;
        this.revenue =  revenue;
    }
}
