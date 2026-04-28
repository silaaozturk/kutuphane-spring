package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.entity.Ayarlar;
import com.orbis.kutuphane.service.AyarlarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ayarlar")
@RequiredArgsConstructor
public class AyarlarController {

    private final AyarlarService ayarlarService;

    @GetMapping
    public Ayarlar getAyarlar() {
        return ayarlarService.getAyarlar();
    }

    @PostMapping
    public Ayarlar guncelle(@RequestBody Ayarlar ayarlar) {
        return ayarlarService.guncelle(ayarlar);
    }
}
