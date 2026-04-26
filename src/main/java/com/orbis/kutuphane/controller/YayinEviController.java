package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.entity.YayinEvi;
import com.orbis.kutuphane.service.YayinEviService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/yayinevi")
@RequiredArgsConstructor
public class YayinEviController {

    private final YayinEviService yayinEviService;

    @PostMapping("/ekle")
    public YayinEvi ekle(@RequestBody YayinEvi yayinEvi) {
        return yayinEviService.yayineviEkle(yayinEvi);
    }

    @GetMapping("/liste")
    public List<YayinEvi> liste() {
        return yayinEviService.tumYayinevleriniGetir();
    }

}
