package com.orbis.kutuphane.repository;

import com.orbis.kutuphane.entity.Kitaplar;
import com.orbis.kutuphane.entity.Odunc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface KitapRepo extends JpaRepository<Kitaplar, Long> {
    @Query("SELECT k FROM Kitaplar k WHERE " +
            "(:ad IS NULL OR k.ad LIKE %:ad%) AND " +
            "(:yazar IS NULL OR k.yazar.ad LIKE %:yazar%) AND " +
            "(:yayinevi IS NULL OR k.yayinevi.ad LIKE %:yayinevi%)")
    List<Kitaplar> filtreliKitaplar(@Param("ad") String ad,
                                  @Param("yazar") String yazar,
                                  @Param("yayinevi") String yayinevi);
//todo buraya bakman lazım
    // Kütüphane bazlı envanter raporu (Müsait kitaplar gruplanmış)
    @Query("SELECT kk.kitap.ad as kitapAd, COUNT(kk) as adet FROM KitapKopya kk " +
            "WHERE kk.sube.id = :subeId            " +
            "GROUP BY kk.kitap.ad")
    List<Map<String, Object>> findMusaitKitaplarRaporu(@Param("subeId") Long subeId);

    @Query("SELECT o FROM Odunc o WHERE o.kitapKopya.sube.id = :subeId AND o.durum = com.orbis.kutuphane.GENEL.OduncDurum.ODUNC_ALINDI")
    List<Odunc> findOduncteOlanlarRaporu(@Param("subeId") Long subeId);
    
    List<Kitaplar> findBySubeId(Long subeId);
}