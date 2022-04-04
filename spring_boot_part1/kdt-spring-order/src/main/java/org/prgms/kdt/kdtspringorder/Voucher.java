package org.prgms.kdt.kdtspringorder;

import java.util.UUID;

public interface Voucher {

    UUID getVoucherId();
    long discount(long beforeDiscount);
}
