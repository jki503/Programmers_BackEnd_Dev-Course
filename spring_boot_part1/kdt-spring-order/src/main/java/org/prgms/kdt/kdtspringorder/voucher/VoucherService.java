package org.prgms.kdt.kdtspringorder.voucher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class VoucherService {

    //@Autowired  필드 주입
    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    /*
    // 생성자 주입 단 생성자가 하나에만 Autowried 달아야하고 4.3부터는 @Autowired 생략 가능한데 하나만 가능
    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }


    @Autowired // setter 주입
    public void setVoucherRepository(VoucherRepository voucherRepository){
        this.voucherRepository = voucherRepository;
    }
    */

    public Voucher getVoucher(UUID voucherID) {
        return voucherRepository
                .findById(voucherID)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("Can't not find voucher for {0}", voucherID)));
    }

    public void useVoucher(Voucher voucher) {

    }

}
