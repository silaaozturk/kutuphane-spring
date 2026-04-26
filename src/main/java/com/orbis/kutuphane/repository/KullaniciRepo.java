package com.orbis.kutuphane.repository;

import com.orbis.kutuphane.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepo extends JpaRepository<Kullanici, Long> {
    Optional<Kullanici> findByEpostaAndSifre(String eposta, String sifre);
    boolean existsByEposta(String eposta);
}