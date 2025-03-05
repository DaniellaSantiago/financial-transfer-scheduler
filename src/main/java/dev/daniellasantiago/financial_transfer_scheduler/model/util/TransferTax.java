package dev.daniellasantiago.financial_transfer_scheduler.model.util;

import java.math.BigDecimal;

public enum TransferTax {
    SAME_DAY(BigDecimal.valueOf(3.00), BigDecimal.valueOf(0.025)),
    DAYS_1_TO_10(BigDecimal.valueOf(12.00), BigDecimal.ZERO),
    DAYS_11_TO_20(BigDecimal.ZERO, BigDecimal.valueOf(0.082)),
    DAYS_21_TO_30(BigDecimal.ZERO, BigDecimal.valueOf(0.069)),
    DAYS_31_TO_40(BigDecimal.ZERO, BigDecimal.valueOf(0.047)),
    DAYS_41_TO_50(BigDecimal.ZERO, BigDecimal.valueOf(0.017));

    private final BigDecimal fixedTax;
    private final BigDecimal percentage;

    TransferTax(BigDecimal fixedTax, BigDecimal percentage) {
        this.fixedTax = fixedTax;
        this.percentage = percentage;
    }

    public BigDecimal calculateTax(BigDecimal value) {
        return fixedTax.add(value.multiply(percentage));
    }
}
