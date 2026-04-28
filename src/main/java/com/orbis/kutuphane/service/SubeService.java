package com.orbis.kutuphane.service;

import com.orbis.kutuphane.entity.Sube;
import com.orbis.kutuphane.repository.SubeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubeService {

    private final SubeRepo subeRepo;

    public Sube subeEkle(Sube sube) {
        return subeRepo.save(sube);
    }

    public List<Sube> tumSubeleriGetir() {
        return subeRepo.findAll();
    }
}
