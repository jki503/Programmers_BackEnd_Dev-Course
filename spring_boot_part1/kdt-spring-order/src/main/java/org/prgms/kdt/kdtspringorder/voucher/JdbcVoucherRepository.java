package org.prgms.kdt.kdtspringorder.voucher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Qualifier("jdbc")
public class JdbcVoucherRepository implements VoucherRepository{

    private final Map<UUID, Voucher> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<Voucher> findById(UUID voucherID) {
        return Optional.ofNullable(storage.get(voucherID));
    }

    @Override
    public Voucher insert(Voucher voucher) {
        storage.put(voucher.getVoucherId(), voucher);
        return voucher;
    }
}
