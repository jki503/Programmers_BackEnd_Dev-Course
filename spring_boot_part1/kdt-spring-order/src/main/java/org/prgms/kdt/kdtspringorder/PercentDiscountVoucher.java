package org.prgms.kdt.kdtspringorder;

import java.util.UUID;

public class PercentDiscountVoucher implements Voucher{
    private final UUID voucherID;
    private final long percent;

    public PercentDiscountVoucher(UUID voucherID, long percent) {
        this.voucherID = voucherID;
        this.percent = percent;
    }
    @Override
    public UUID getVoucherId() {
        return voucherID;
    }

    @Override
    public long discount(long beforeDiscount) {
        return beforeDiscount * (percent / 100);
    }
}
