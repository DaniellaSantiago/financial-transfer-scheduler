package dev.daniellasantiago.financial_transfer_scheduler.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountOrigin;
    private String accountDestination;
    private BigDecimal value;
    private BigDecimal tax;
    private BigDecimal totalValue;
    private LocalDate dateScheduling;
    private LocalDate dateTransference;
}
