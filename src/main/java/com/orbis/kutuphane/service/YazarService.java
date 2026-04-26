package com.orbis.kutuphane.service;

import com.orbis.kutuphane.entity.Yazar;
import com.orbis.kutuphane.repository.YazarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class YazarService {

    private final YazarRepo yazarRepo;

    public Yazar yazarEkle(Yazar yazar) {
        return yazarRepo.save(yazar);
    }

    public List<Yazar> yazarListele () {
        return yazarRepo.findAll();
    }
}
