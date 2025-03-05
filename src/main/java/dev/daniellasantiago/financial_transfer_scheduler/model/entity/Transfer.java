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

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private BigDecimal taxa;
    private BigDecimal valorTotal;
    private LocalDate dataAgendamento;
    private LocalDate dataTransferencia;
}
