package org.prgms.kdt.kdtspringorder.voucher;

import org.prgms.kdt.kdtspringorder.voucher.Voucher;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public class FixedAmountVoucher implements Voucher {
    private static final long MAX_VOUCHER_AMOUNT = 10000;
    private final UUID voucherID;
    private final long amount;

    public FixedAmountVoucher(UUID voucherID, long amount) {
        if(amount <= 0) throw new IllegalArgumentException("Amount should be positive");
        if(amount > MAX_VOUCHER_AMOUNT) throw new IllegalArgumentException("Amount should be less than %d".formatted(MAX_VOUCHER_AMOUNT));

        this.voucherID = voucherID;
        this.amount = amount;
    }

    @Override
    public UUID getVoucherId() {
        return voucherID;
    }

    @Override
    public long discount(long beforeDiscount){
        var discountAmount = beforeDiscount - amount;
        return discountAmount < 0 ? 0 : discountAmount;
    }
}
