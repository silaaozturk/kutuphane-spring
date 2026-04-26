package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.entity.Odunc;
import com.orbis.kutuphane.service.OduncService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odunc")
public class OduncController {

    private final OduncService oduncService;

    public OduncController(OduncService oduncService) {
        this.oduncService = oduncService;
    }

    // 📌 Kitap kirala
    @PostMapping("/kirala")
    public Odunc kirala(
            @RequestParam Long kullaniciId,
            @RequestParam Long kitapKopyaId,
            @RequestParam int gun
    ) {
        return oduncService.kitapKirala(kullaniciId, kitapKopyaId, gun);
    }

    // 📌 Kitap iade
    @PostMapping("/iade")
    public Odunc iade(
            @RequestParam Long oduncId,
            @RequestParam double gunlukCeza
    ) {
        return oduncService.kitapIadeEt(oduncId, gunlukCeza);
    }

    // 📌 Kitap rezerve
    @PostMapping("/rezerve")
    public Odunc rezerve(
            @RequestParam Long kullaniciId,
            @RequestParam Long kitapKopyaId
    ) {
        return oduncService.kitapRezerveEt(kullaniciId, kitapKopyaId);
    }
}