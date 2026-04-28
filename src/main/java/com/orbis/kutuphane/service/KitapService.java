package com.orbis.kutuphane.service;

import com.orbis.kutuphane.dto.request.KitaplarDTO;
import com.orbis.kutuphane.entity.*;
import com.orbis.kutuphane.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KitapService {

    private final KitapRepo kitapRepo;
    private final SubeRepo subeRepo;
    private final KitapKopyaRepo kitapKopyaRepo;
    private final YazarRepo yazarRepo;
    private final YayineviRepo yayineviRepo;


    public List<Kitaplar> kitaplariGetir(String ad, String yazar, String yayinevi) {
        return kitapRepo.filtreliKitaplar(ad,yazar, yayinevi);
    }

    public Kitaplar kitapOlustur(Kitaplar kitap, Long yazarId,Long subeId,Long yayineviId) {
        Yazar yazar = yazarRepo.findById(yazarId).orElseThrow(() -> new RuntimeException("Yazar bulunamadı: " + yazarId));
        YayinEvi yayinEvi = yayineviRepo.findById(yayineviId).orElseThrow(() -> new RuntimeException("YayinEvi bulunamadı: " + yayineviId));
        Sube sube = subeRepo.findById(subeId).orElseThrow(() -> new RuntimeException("Sube bulunamadı: " + subeId));
        kitap.setYazar(yazar);
        kitap.setSube(sube);
        kitap.setYayinevi(yayinEvi);
        return kitapRepo.save(kitap);
    }

    public KitapKopya kitapEkle(KitapKopya kitapKopya,Long kitapId,Long subeId) {
        Kitaplar kitap = kitapRepo.findById(kitapId).orElseThrow(() -> new RuntimeException("Kitap bulunamadı: " + kitapId));
        Sube sube = subeRepo.findById(subeId).orElseThrow(() -> new RuntimeException("Sube bulunamadı: " + subeId));
        kitapKopya.setKitap(kitap);
        kitapKopya.setSube(sube);
        return kitapKopyaRepo.save(kitapKopya);
    }

    public Map<String, Object> subeBazliEnvanterRaporu(Long subeId) {
        Map<String, Object> rapor = new HashMap<>();
        rapor.put("musaitKitaplar", kitapRepo.findMusaitKitaplarRaporu(subeId));
        rapor.put("oduncteOlanlar", kitapRepo.findOduncteOlanlarRaporu(subeId));
        return rapor;
    }

    public List<Kitaplar> subeBazliKitapListele(Long subeId) {
        return kitapRepo.findBySubeId(subeId);
    }


}