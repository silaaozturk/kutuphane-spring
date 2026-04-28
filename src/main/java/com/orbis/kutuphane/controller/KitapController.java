package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.dto.request.KitapKopyaDTO;
import com.orbis.kutuphane.dto.request.KitaplarDTO;
import com.orbis.kutuphane.entity.KitapKopya;
import com.orbis.kutuphane.entity.Kitaplar;
import com.orbis.kutuphane.service.KitapService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kitap")
@RequiredArgsConstructor
public class KitapController {

    private final KitapService kitapService;
    private final ModelMapper modelMapper;

    @GetMapping("/listele")
    public List<Kitaplar> listele(@RequestParam(required = false) String ad,
                                  @RequestParam(required = false) String yazar,
                                  @RequestParam(required = false) String yayinevi) {
        return kitapService.kitaplariGetir(ad, yazar, yayinevi);
    }


    @PostMapping("/olustur")
    public Kitaplar olustur(@RequestBody KitaplarDTO request) {
        Kitaplar kitaplar = modelMapper.map(request, Kitaplar.class);
        return kitapService.kitapOlustur(kitaplar, request.getYazarId(), request.getSubeId(), request.getYayineviId());
    }

    @PostMapping("/kitapekle")
    public KitapKopya ekle(@RequestBody KitapKopyaDTO kitapKopyaDTO) {
        KitapKopya kitapKopya = new KitapKopya();
        kitapKopya.setBarkodNo(kitapKopyaDTO.getBarkodNo());
        kitapKopya.setAdet(kitapKopyaDTO.getAdet());
        kitapKopya.setOduncteAdet(0);
        kitapKopya.setMevcut(true);
        kitapKopya.setMusaitAdet(kitapKopyaDTO.getAdet());

        return kitapService.kitapEkle(kitapKopya, kitapKopyaDTO.getKitapId(), kitapKopyaDTO.getSubeId());
    }

    @GetMapping("/rapor/envanter/{subeId}")
    public Map<String, Object> envanterRaporu(@PathVariable Long subeId) {
        return kitapService.subeBazliEnvanterRaporu(subeId);
    }

    @GetMapping("/sube/{subeId}")
    public List<Kitaplar> subeBazliListele(@PathVariable Long subeId) {
        return kitapService.subeBazliKitapListele(subeId);
    }
}