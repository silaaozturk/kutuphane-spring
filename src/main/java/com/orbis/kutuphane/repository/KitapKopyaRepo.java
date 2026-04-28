package com.orbis.kutuphane.repository;

import com.orbis.kutuphane.entity.KitapKopya;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KitapKopyaRepo extends JpaRepository<KitapKopya, Long> {

    List<KitapKopya> findByMusaitAdetGreaterThan(int musaitAdet);

    // Belirli kitabın müsait kopyaları
    Optional<KitapKopya> findByIdAndMusaitAdetGreaterThan(Long kitapId, int adet);
}