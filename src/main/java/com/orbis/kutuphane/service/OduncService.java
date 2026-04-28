package com.orbis.kutuphane.service;

import com.orbis.kutuphane.GENEL;
import com.orbis.kutuphane.entity.*;
import com.orbis.kutuphane.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OduncService {

    private final OduncRepo oduncRepo;
    private final KitapKopyaRepo kitapKopyaRepo;
    private final KullaniciRepo kullaniciRepo;
    private final AyarlarService ayarlarService;

    @Transactional
    public Odunc kitapOduncAl(Long kullaniciId, Long kitapKopyaId) {
        Kullanici kullanici = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (kullanici.isYasakli()) {
            throw new RuntimeException("Bu kullanıcı yasaklıdır!");
        }

        KitapKopya kitapKopya = kitapKopyaRepo.findById(kitapKopyaId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        if (kitapKopya.getMusaitAdet() <= 0) {
            throw new RuntimeException("Kitap şu an müsait değil");
        }

        int gun = ayarlarService.getAyarlar().getVarsayilanOduncGunu();

        Odunc odunc = new Odunc();
        odunc.setKitapKopya(kitapKopya);
        odunc.setKullanici(kullanici);
        odunc.setAlisTarihi(LocalDate.now());
        odunc.setIadeTarihi(LocalDate.now().plusDays(gun));
        odunc.setDurum(GENEL.OduncDurum.ODUNC_ALINDI);
        odunc.setCezaTutari(0.0);
        odunc.setNormalUcret(0.0);
        odunc.setToplamUcret(0.0);

        kitapKopya.setMusaitAdet(kitapKopya.getMusaitAdet() - 1);
        kitapKopya.setOduncteAdet(kitapKopya.getOduncteAdet() + 1);
        kitapKopyaRepo.save(kitapKopya);

        return oduncRepo.save(odunc);
    }

    @Transactional
    public Odunc kitapRezervasyon(Long kullaniciId, Long kitapKopyaId, LocalDate baslangic) {
        if (baslangic.isBefore(LocalDate.now())) {
            throw new RuntimeException("Başlangıç tarihi bugünden önce olamaz");
        }

        Kullanici kullanici = kullaniciRepo.findById(kullaniciId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        if (kullanici.isYasakli()) {
            throw new RuntimeException("Bu kullanıcı yasaklıdır!");
        }

        KitapKopya kitapKopya = kitapKopyaRepo.findById(kitapKopyaId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        List<Odunc> cakisanlar = oduncRepo.findCakisanRezervasyonlar(kitapKopyaId, baslangic, baslangic.plusDays(5));
        if (cakisanlar.size() >= kitapKopya.getAdet()) {
            throw new RuntimeException("Belirtilen tarihler arasında kitap müsait değil.");
        }

        int gun = ayarlarService.getAyarlar().getVarsayilanOduncGunu();

        Odunc odunc = new Odunc();
        odunc.setKitapKopya(kitapKopya);
        odunc.setKullanici(kullanici);
        odunc.setAlisTarihi(baslangic);
        odunc.setIadeTarihi(baslangic.plusDays(gun));
        odunc.setDurum(GENEL.OduncDurum.REZERVE);
        odunc.setCezaTutari(0.0);
        odunc.setNormalUcret(0.0);
        odunc.setToplamUcret(0.0);

        return oduncRepo.save(odunc);
    }

    @Transactional
    public Odunc rezervdenOduncAl(Long oduncId) {

        Odunc odunc = oduncRepo.findById(oduncId)
                .orElseThrow(() -> new RuntimeException("Kayıt bulunamadı"));

        if (odunc.getDurum() != GENEL.OduncDurum.REZERVE) {
            throw new RuntimeException("Bu kayıt rezerv değil");
        }

        KitapKopya kitap = odunc.getKitapKopya();

        if (kitap.getMusaitAdet() <= 0) {
            throw new RuntimeException("Kitap şu an müsait değil");
        }

        int gun = ayarlarService.getAyarlar().getVarsayilanOduncGunu();

        odunc.setAlisTarihi(LocalDate.now());
        odunc.setIadeTarihi(LocalDate.now().plusDays(gun));
        odunc.setDurum(GENEL.OduncDurum.ODUNC_ALINDI);
        odunc.setNormalUcret(0.0);
        odunc.setCezaTutari(0.0);
        odunc.setToplamUcret(0.0);

        kitap.setMusaitAdet(kitap.getMusaitAdet() - 1);
        kitap.setOduncteAdet(kitap.getOduncteAdet() + 1);
        kitapKopyaRepo.save(kitap);

        return oduncRepo.save(odunc);
    }


    @Transactional
    public Odunc kitapIadeEt(Long oduncId) {

        Odunc odunc = oduncRepo.findById(oduncId)
                .orElseThrow(() -> new RuntimeException("Kayıt bulunamadı"));

        if (odunc.getDurum() == GENEL.OduncDurum.IADE_EDILDI || odunc.getDurum() == GENEL.OduncDurum.REZERVE_IPTAL) {
            throw new RuntimeException("Bu kayıt zaten kapatılmış");
        }

        if (odunc.getDurum() == GENEL.OduncDurum.REZERVE) {
            odunc.setDurum(GENEL.OduncDurum.REZERVE_IPTAL);
            return oduncRepo.save(odunc);
        }

        odunc.setGercekIadeTarihi(LocalDate.now());

        double gunlukFiyat = ayarlarService.getAyarlar().getGunlukUcret();
        double gunlukCeza = ayarlarService.getAyarlar().getGunlukCezaUcreti();

        long maxGun = ChronoUnit.DAYS.between(
                odunc.getAlisTarihi(),
                odunc.getIadeTarihi()
        );

        long gercekGun = ChronoUnit.DAYS.between(
                odunc.getAlisTarihi(),
                odunc.getGercekIadeTarihi()
        );

        long gecikmeGun = Math.max(0,
                ChronoUnit.DAYS.between(
                        odunc.getIadeTarihi(),
                        odunc.getGercekIadeTarihi()
                )
        );

        double normalUcret;
        double ceza = 0;

        if (gecikmeGun > 0) {
            normalUcret = maxGun * gunlukFiyat;
            ceza = gecikmeGun * gunlukCeza;
            odunc.setDurum(GENEL.OduncDurum.GECIKTI);
        } else {
            long odenecekGun = Math.max(1, gercekGun);
            normalUcret = odenecekGun * gunlukFiyat;
            odunc.setDurum(GENEL.OduncDurum.IADE_EDILDI);
        }

        odunc.setNormalUcret(normalUcret);
        odunc.setCezaTutari(ceza);
        odunc.setToplamUcret(normalUcret + ceza);

        KitapKopya kitap = odunc.getKitapKopya();
        kitap.setOduncteAdet(kitap.getOduncteAdet() - 1);
        kitap.setMusaitAdet(kitap.getMusaitAdet() + 1);

        kitapKopyaRepo.save(kitap);

        return oduncRepo.save(odunc);
    }

    @Transactional
    public Odunc rezervIptalEt(Long oduncId) {
        Odunc odunc = oduncRepo.findById(oduncId)
                .orElseThrow(() -> new RuntimeException("Kayıt bulunamadı"));

        if (odunc.getDurum() != GENEL.OduncDurum.REZERVE) {
            throw new RuntimeException("Bu kayıt rezervasyon değil");
        }

        odunc.setDurum(GENEL.OduncDurum.REZERVE_IPTAL);
        return oduncRepo.save(odunc);
    }

}