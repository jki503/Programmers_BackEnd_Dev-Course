package org.prgms.kdt.kdtspringorder;

import java.util.UUID;

public class FixedAmountVoucher implements Voucher{
    private final UUID voucherID;
    private final long amount;

    public FixedAmountVoucher(UUID voucherID, long amount) {
        this.voucherID = voucherID;
        this.amount = amount;
    }

    @Override
    public UUID getVoucherId() {
        return null;
    }
    @Override
    public long discount(long beforeDiscount){
        return beforeDiscount - amount;
    }
}
