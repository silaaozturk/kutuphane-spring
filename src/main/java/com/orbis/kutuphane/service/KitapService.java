package com.orbis.kutuphane.service;

import com.orbis.kutuphane.entity.Kitaplar;
import com.orbis.kutuphane.repository.KitapRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KitapService {

    private final KitapRepo kitapRepo;

    public KitapService(KitapRepo kitapRepo) {
        this.kitapRepo = kitapRepo;
    }

    public List<Kitaplar> tumKitaplariGetir(String ad, String yazar, String yayinevi) {
        return kitapRepo.filtreliKitaplar(ad,yazar, yayinevi);
    }

    public Kitaplar kitapEkle(Kitaplar kitap) {
        return kitapRepo.save(kitap);
    }

    public Map<String, Object> subeBazliEnvanterRaporu(Long subeId) {
        Map<String, Object> rapor = new HashMap<>();
        rapor.put("musaitKitaplar", kitapRepo.findMusaitKitaplarRaporu(subeId));
        rapor.put("oduncteOlanlar", kitapRepo.findOduncteOlanlarRaporu(subeId));
        return rapor;
    }
}