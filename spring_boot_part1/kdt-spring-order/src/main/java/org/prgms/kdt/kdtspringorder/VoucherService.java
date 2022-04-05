package org.prgms.kdt.kdtspringorder;

import java.text.MessageFormat;
import java.util.UUID;

public class VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public Voucher getVoucher(UUID voucherID) {
        return voucherRepository
                .findById(voucherID)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Can't not find voucher for {0}", voucherID)));
    }

    public void useVoucher(Voucher voucher) {

    }

}
