package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.entity.Sube;
import com.orbis.kutuphane.service.SubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sube")
@RequiredArgsConstructor
public class SubeController {

    private final SubeService subeService;

    @PostMapping("/ekle")
    public Sube ekle(@RequestBody Sube sube) {
        return subeService.subeEkle(sube);
    }

    @GetMapping("/liste")
    public List<Sube> liste() {
        return subeService.tumSubeleriGetir();
    }
}
