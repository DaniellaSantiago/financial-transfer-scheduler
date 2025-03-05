package dev.daniellasantiago.financial_transfer_scheduler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @GetMapping("/check")
    public String healthcheck(){
        return "Success";
    }
}
