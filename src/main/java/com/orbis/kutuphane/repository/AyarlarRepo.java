package com.orbis.kutuphane.repository;

import com.orbis.kutuphane.entity.Ayarlar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AyarlarRepo extends JpaRepository<Ayarlar, Long> {
}
