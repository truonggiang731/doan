package com.springboot.blog.repository;

import com.springboot.blog.entity.CanHo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CanHoRepository extends JpaRepository<CanHo, Long> {

    Optional<CanHo> findById(Long Id);

    List<CanHo> findByToaNhaId(Long toaNhaId);

    List<CanHo> findByLoaiCanHoId(Long loaiCanHoId);
}
