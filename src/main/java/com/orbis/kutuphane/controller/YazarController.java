package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.entity.Yazar;
import com.orbis.kutuphane.service.YazarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/yazar")
@RequiredArgsConstructor
public class YazarController {

    private final YazarService yazarService;

    @PostMapping("/ekle")
    public Yazar ekle(@RequestBody Yazar yazar) {
        return yazarService.yazarEkle(yazar);
    }

    @GetMapping("/liste")
    public List<Yazar> liste() {
        return yazarService.yazarListele();
    }
}
