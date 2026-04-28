package com.orbis.kutuphane.repository;

import com.orbis.kutuphane.GENEL;
import com.orbis.kutuphane.entity.Odunc;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OduncRepo extends JpaRepository<Odunc, Long> {

    List<Odunc> findByDurum(GENEL.OduncDurum durum);

    List<Odunc> findByKullaniciId(Long kullaniciId);

    List<Odunc> findByKitapKopyaIdAndDurum(Long kitapKopyaId, GENEL.OduncDurum durum);

    @Query("SELECT o FROM Odunc o WHERE o.kitapKopya.id = :kitapKopyaId " +
           "AND o.durum IN (com.orbis.kutuphane.GENEL.OduncDurum.REZERVE, com.orbis.kutuphane.GENEL.OduncDurum.ODUNC_ALINDI) " +
           "AND ((o.alisTarihi <= :bitis AND o.iadeTarihi >= :baslangic))")
    List<Odunc> findCakisanRezervasyonlar(@Param("kitapKopyaId") Long kitapKopyaId, 
                                          @Param("baslangic") LocalDate baslangic, 
                                          @Param("bitis") LocalDate bitis);
}