package com.orbis.kutuphane.repository;

import com.orbis.kutuphane.GENEL;
import com.orbis.kutuphane.entity.Odunc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OduncRepo extends JpaRepository<Odunc, Long> {

    List<Odunc> findByDurum(GENEL.OduncDurum durum);

    List<Odunc> findByKullaniciId(Long kullaniciId);

    List<Odunc> findByKitapKopyaIdAndDurum(Long kitapKopyaId, GENEL.OduncDurum durum);
}