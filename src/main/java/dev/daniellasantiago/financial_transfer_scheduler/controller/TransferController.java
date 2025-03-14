package dev.daniellasantiago.financial_transfer_scheduler.controller;

import dev.daniellasantiago.financial_transfer_scheduler.model.entity.Transfer;
import dev.daniellasantiago.financial_transfer_scheduler.service.TransferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Transfer> scheduleTransfer(@RequestBody Transfer transfer) {
        Transfer scheduling = transferService.scheduleTransfer(transfer);
        return ResponseEntity.ok(scheduling);
    }

    @GetMapping
    public ResponseEntity<Page<Transfer>> listTransfers(Pageable pageable) {
        return ResponseEntity.ok(transferService.listTransfers(pageable));
    }

    @GetMapping("/check")
    public String healthcheck(){
        return "Success";
    }
}
