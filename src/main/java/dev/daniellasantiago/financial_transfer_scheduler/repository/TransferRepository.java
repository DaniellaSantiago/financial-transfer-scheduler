package dev.daniellasantiago.financial_transfer_scheduler.repository;

import dev.daniellasantiago.financial_transfer_scheduler.model.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
