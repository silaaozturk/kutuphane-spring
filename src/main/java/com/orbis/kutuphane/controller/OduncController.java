package com.orbis.kutuphane.controller;

import com.orbis.kutuphane.dto.request.RezerveRequest;
import com.orbis.kutuphane.entity.Odunc;
import com.orbis.kutuphane.service.OduncService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/odunc")
@AllArgsConstructor
public class OduncController {

    private final OduncService oduncService;


    @PostMapping("/rezerve")
    public Odunc rezerve(RezerveRequest request) {
        return oduncService.kitapRezervasyon(request.getKullaniciId(), request.getKitapKopyaId(), LocalDate.parse(request.getBaslangic()));
    }

    @PostMapping("/odunc-al")
    public Odunc oduncAl(
            @RequestParam Long kullaniciId,
            @RequestParam Long kitapKopyaId
    ) {
        return oduncService.kitapOduncAl(kullaniciId, kitapKopyaId);
    }

    @PostMapping("/rezervden-odunc-al")
    public Odunc rezervdenOduncAl(@RequestParam Long oduncId) {
        return oduncService.rezervdenOduncAl(oduncId);
    }

    @PostMapping("/teslim")
    public Odunc teslim(@RequestParam Long oduncId) {
        return oduncService.kitapIadeEt(oduncId);
    }


    @PostMapping("/rezerve-iptal")
    public Odunc rezerveIptal(@RequestParam Long oduncId) {
        return oduncService.rezervIptalEt(oduncId);
    }
}