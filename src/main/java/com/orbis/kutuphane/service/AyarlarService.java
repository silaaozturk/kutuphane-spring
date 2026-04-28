package com.orbis.kutuphane.service;

import com.orbis.kutuphane.entity.Ayarlar;
import com.orbis.kutuphane.repository.AyarlarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AyarlarService {

    private final AyarlarRepo ayarlarRepo;

    public Ayarlar getAyarlar() {
        return ayarlarRepo.findAll().stream().findFirst().orElseGet(() -> {
            Ayarlar yeni = new Ayarlar();
            yeni.setVarsayilanOduncGunu(10);
            yeni.setGunlukUcret(20.0);
            yeni.setGunlukCezaUcreti(50.0);
            return ayarlarRepo.save(yeni);
        });
    }

    public Ayarlar guncelle(Ayarlar yeniAyarlar) {
        Ayarlar mevcut = getAyarlar();
        mevcut.setVarsayilanOduncGunu(yeniAyarlar.getVarsayilanOduncGunu());
        mevcut.setGunlukUcret(yeniAyarlar.getGunlukUcret());
        mevcut.setGunlukCezaUcreti(yeniAyarlar.getGunlukCezaUcreti());
        return ayarlarRepo.save(mevcut);
    }
}
