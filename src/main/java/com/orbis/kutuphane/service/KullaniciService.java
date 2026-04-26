package com.orbis.kutuphane.service;

import com.orbis.kutuphane.GENEL;
import com.orbis.kutuphane.entity.Kullanici;
import com.orbis.kutuphane.repository.KullaniciRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KullaniciService {

        @Autowired
        private KullaniciRepo kullaniciRepo;

        public Kullanici kullaniciGuncelle(Long id,Kullanici guncelKullanici) {
           Kullanici mevcutKullanici = kullaniciRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Böyle Bir Kullanıcı Bulunmamakta!"));
            mevcutKullanici.setAd(guncelKullanici.getAd());
            mevcutKullanici.setSoyad(guncelKullanici.getSoyad());
            mevcutKullanici.setEposta(guncelKullanici.getEposta());
            mevcutKullanici.setRol(guncelKullanici.getRol());
            mevcutKullanici.setTelefon(guncelKullanici.getTelefon());
            mevcutKullanici.setYasakli(guncelKullanici.isYasakli());

            if (guncelKullanici.getSifre() != null && !guncelKullanici.getSifre().isEmpty()) {
                mevcutKullanici.setSifre(guncelKullanici.getSifre());
            }

            return kullaniciRepo.save(mevcutKullanici);
        }

        public Kullanici kullaniciKaydet(Kullanici kullanici,GENEL.Rol rol) {
            if (kullaniciRepo.existsByEposta(kullanici.getEposta())) {
                throw new RuntimeException("Bu e-posta zaten kullanımda!");
            }
            kullanici.setAd(kullanici.getAd().trim());
            kullanici.setSoyad(kullanici.getSoyad().trim());
            kullanici.setSifre(kullanici.getSifre().trim());
            kullanici.setTelefon(kullanici.getTelefon());
            kullanici.setEposta(kullanici.getEposta().trim());
            kullanici.setRol(rol);
            kullanici.setYasakli(false);

            return kullaniciRepo.save(kullanici);
        }
}
