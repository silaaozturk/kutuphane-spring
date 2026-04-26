package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.GENEL;
import com.orbis.kutuphane.dto.request.GirisRequest;
import com.orbis.kutuphane.dto.request.KullaniciRequest;
import com.orbis.kutuphane.dto.response.GirisResponse;
import com.orbis.kutuphane.entity.Kullanici;
import com.orbis.kutuphane.repository.KullaniciRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.orbis.kutuphane.service.KullaniciService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/kullanici")
@RequiredArgsConstructor
public class KullaniciController {

    private final KullaniciService kullaniciService;
    private final KullaniciRepo kullaniciRepo;
    private final ModelMapper modelMapper;
    


    @PostMapping("/giris")
    public GirisResponse girisYap(@RequestBody GirisRequest istek) {
        Kullanici uye = kullaniciService.girisYap(istek.getEposta(), istek.getSifre());

        return new GirisResponse(uye.getAd(), uye.getSoyad(),uye.getId(), (int) uye.getRol().ordinal(), "Giriş başarılı.");
    }

    @PostMapping("/uyeKayit")
    public ResponseEntity<Kullanici> uyeKayit(@RequestBody KullaniciRequest request) {
        Kullanici kullanici =  modelMapper.map(request, Kullanici.class);
        return ResponseEntity.ok(kullaniciService.kullaniciKaydet(kullanici, GENEL.Rol.UYE));
    }

    @GetMapping("/kullaniciBilgi/{id}")
    public Optional<Kullanici> kullaniciBilgi(@PathVariable Long id) {
        return kullaniciRepo.findById( id);
    }

    @GetMapping("/tumUyeler")
    public List<Kullanici> tumUyeler(){
        return kullaniciRepo.findAll();
    }

    @PutMapping("/guncelle/{id}")
    public ResponseEntity<Kullanici> uyeGuncelle(@PathVariable Long id, @RequestBody Kullanici guncelVeriler) {
        return ResponseEntity.ok(kullaniciService.kullaniciGuncelle(id, guncelVeriler));
    }
}