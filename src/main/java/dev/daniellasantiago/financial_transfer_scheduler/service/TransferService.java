package dev.daniellasantiago.financial_transfer_scheduler.service;

import dev.daniellasantiago.financial_transfer_scheduler.model.entity.Transfer;
import dev.daniellasantiago.financial_transfer_scheduler.model.util.TransferTax;
import dev.daniellasantiago.financial_transfer_scheduler.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferService {
    private final TransferRepository transferRepository;

    public TransferService(TransferRepository repository) {
        this.transferRepository = repository;
    }

    public Transfer scheduleTransfer(Transfer transfer) {
        long rangeOfDays = ChronoUnit.DAYS.between(transfer.getDateScheduling(), transfer.getDateTransference());
        TransferTax tax = findTax(rangeOfDays);

        if (tax == null) {
            throw new IllegalArgumentException("No fees apply for this transfer! Transfer not authorized!");
        }

        BigDecimal taxValue = tax.calculateTax(transfer.getValue());
        transfer.setTax(taxValue);
        transfer.setTotalValue(transfer.getValue().add(taxValue));

        return transferRepository.save(transfer);
    }

    public List<Transfer> listTransfers() {
        return transferRepository.findAll();
    }

    private TransferTax findTax(long days) {
        if (days == 0) return TransferTax.SAME_DAY;
        if (days >= 1 && days <= 10) return TransferTax.DAYS_1_TO_10;
        if (days >= 11 && days <= 20) return TransferTax.DAYS_11_TO_20;
        if (days >= 21 && days <= 30) return TransferTax.DAYS_21_TO_30;
        if (days >= 31 && days <= 40) return TransferTax.DAYS_31_TO_40;
        if (days >= 41 && days <= 50) return TransferTax.DAYS_41_TO_50;

        return null;
    }
}
