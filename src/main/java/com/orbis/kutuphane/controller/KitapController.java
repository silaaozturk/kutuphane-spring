package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.entity.Kitaplar;
import com.orbis.kutuphane.service.KitapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/kitap")
@RequiredArgsConstructor
public class KitapController {

    private final KitapService kitapService;

    @GetMapping("/listele")
    public List<Kitaplar> listele(@RequestParam(required = false) String ad,
                                  @RequestParam(required = false) String yazar,
                                  @RequestParam(required = false) String yayinevi) {
        return kitapService.tumKitaplariGetir(ad, yazar, yayinevi);
    }


    @PostMapping("/ekle")
    public Kitaplar ekle(@RequestBody Kitaplar kitap) {
        return kitapService.kitapEkle(kitap);
    }

    @GetMapping("/rapor/envanter/{subeId}")
    public Map<String, Object> envanterRaporu(@PathVariable Long subeId) {
        return kitapService.subeBazliEnvanterRaporu(subeId);
    }
}