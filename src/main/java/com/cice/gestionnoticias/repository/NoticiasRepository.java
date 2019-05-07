package com.cice.gestionnoticias.repository;

import com.cice.gestionnoticias.repository.entity.NoticiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiasRepository extends JpaRepository<NoticiaEntity, Long> {
}
