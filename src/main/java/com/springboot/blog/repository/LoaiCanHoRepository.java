package com.springboot.blog.repository;

import com.springboot.blog.entity.LoaiCanHo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoaiCanHoRepository extends JpaRepository<LoaiCanHo, Long> {
}
