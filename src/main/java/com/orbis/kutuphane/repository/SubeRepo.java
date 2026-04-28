package com.orbis.kutuphane.repository;

import com.orbis.kutuphane.entity.Sube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubeRepo extends JpaRepository<Sube, Long> {
}
