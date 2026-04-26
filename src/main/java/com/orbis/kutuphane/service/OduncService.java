package com.orbis.kutuphane.service;

import com.orbis.kutuphane.GENEL;
import com.orbis.kutuphane.entity.*;
import com.orbis.kutuphane.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class OduncService {

    private final OduncRepo oduncRepo;
    private final KitapKopyaRepo kitapKopyaRepo;
    private final KullaniciRepo kullaniciRepo;

    public OduncService(OduncRepo oduncRepo,
                        KitapKopyaRepo kitapKopyaRepo,
                        KullaniciRepo kullaniciRepo) {
        this.oduncRepo = oduncRepo;
        this.kitapKopyaRepo = kitapKopyaRepo;
        this.kullaniciRepo = kullaniciRepo;
    }

    // 📌 Kitap kirala
    public Odunc kitapKirala(Long kullaniciId, Long kitapKopyaId, int gun) {

        Kullanici kullanici = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (kullanici.isYasakli()) {
            throw new RuntimeException("Bu kullanıcı yasaklıdır ve kitap ödünç alamaz!");
        }

        KitapKopya kitapKopya = kitapKopyaRepo.findById(kitapKopyaId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        if (kitapKopya.getDurum() != GENEL.KitapDurum.MUSAIT) {
            throw new RuntimeException("Kitap müsait değil");
        }

        Odunc odunc = new Odunc();
        odunc.setKitapKopya(kitapKopya);
        odunc.setKullanici(kullanici);
        odunc.setAlisTarihi(LocalDate.now());
        odunc.setIadeTarihi(LocalDate.now().plusDays(gun));
        odunc.setDurum(GENEL.OduncDurum.ODUNC_ALINDI);
        odunc.setCezaTutari(0.0);

        kitapKopya.setDurum(GENEL.KitapDurum.ODUNCTE);
        kitapKopyaRepo.save(kitapKopya);

        return oduncRepo.save(odunc);
    }


    public Odunc kitapIadeEt(Long oduncId, double gunlukCeza) {

        Odunc odunc = oduncRepo.findById(oduncId)
                .orElseThrow(() -> new RuntimeException("Kayıt bulunamadı"));

        odunc.setGercekIadeTarihi(LocalDate.now());

        long gecikmeGun = ChronoUnit.DAYS.between(
                odunc.getIadeTarihi(),
                odunc.getGercekIadeTarihi()
        );

        if (gecikmeGun > 0) {
            odunc.setDurum(GENEL.OduncDurum.GECIKTI);
            odunc.setCezaTutari(gecikmeGun * gunlukCeza);
        } else {
            odunc.setDurum(GENEL.OduncDurum.IADE_EDILDI);
        }

        // kitap tekrar müsait
        KitapKopya kitap = odunc.getKitapKopya();
        kitap.setDurum(GENEL.KitapDurum.MUSAIT);

        kitapKopyaRepo.save(kitap);

        return oduncRepo.save(odunc);
    }

    // 📌 Kitap Rezerve Et
    public Odunc kitapRezerveEt(Long kullaniciId, Long kitapKopyaId) {
        Kullanici kullanici = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (kullanici.isYasakli()) {
            throw new RuntimeException("Bu kullanıcı yasaklıdır ve rezervasyon yapamaz!");
        }

        KitapKopya kitapKopya = kitapKopyaRepo.findById(kitapKopyaId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        if (kitapKopya.getDurum() != GENEL.KitapDurum.MUSAIT) {
            throw new RuntimeException("Kitap şu an rezerve edilemez (Müsait değil)");
        }

        Odunc rezervasyon = new Odunc();
        rezervasyon.setKitapKopya(kitapKopya);
        rezervasyon.setKullanici(kullanici);
        rezervasyon.setAlisTarihi(LocalDate.now());
        rezervasyon.setDurum(GENEL.OduncDurum.REZERVE);

        kitapKopya.setDurum(GENEL.KitapDurum.REZERVE);
        kitapKopyaRepo.save(kitapKopya);

        return oduncRepo.save(rezervasyon);
    }
}