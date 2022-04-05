package org.prgms.kdt.kdtspringorder;

import java.util.Optional;
import java.util.UUID;

public interface VoucherRepository {
    public Optional<Voucher> findById(UUID voucherID);
}
