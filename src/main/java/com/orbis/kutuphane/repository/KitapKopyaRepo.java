package com.orbis.kutuphane.repository;

import com.orbis.kutuphane.GENEL;
import com.orbis.kutuphane.entity.KitapKopya;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitapKopyaRepo extends JpaRepository<KitapKopya, Long> {

    // Müsait olan kopyaları getir
    List<KitapKopya> findByDurum(GENEL.KitapDurum durum);

    // Belirli kitabın müsait kopyaları
    List<KitapKopya> findByKitapIdAndDurum(Long kitapId, GENEL.KitapDurum durum);
}