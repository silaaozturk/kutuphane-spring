package com.orbis.kutuphane.service;

import com.orbis.kutuphane.entity.YayinEvi;
import com.orbis.kutuphane.repository.YayineviRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YayinEviService {
    private final YayineviRepo yayineviRepo;

    public List<YayinEvi> tumYayinevleriniGetir() {
        return yayineviRepo.findAll();
    }

    public YayinEvi yayineviEkle(YayinEvi yayinevi) {
        return yayineviRepo.save(yayinevi);
    }
}
