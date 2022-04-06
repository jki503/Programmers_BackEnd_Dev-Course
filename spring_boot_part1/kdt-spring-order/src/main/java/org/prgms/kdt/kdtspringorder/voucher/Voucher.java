package org.prgms.kdt.kdtspringorder.voucher;

import java.util.UUID;


public interface Voucher {

    UUID getVoucherId();
    long discount(long beforeDiscount);
}
