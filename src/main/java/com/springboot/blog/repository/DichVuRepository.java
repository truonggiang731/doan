package com.springboot.blog.repository;

import com.springboot.blog.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DichVuRepository extends JpaRepository<DichVu, Long> {
    Optional<DichVu> findDichVuById(Long id);

    List<DichVu> findDichVuByLoaiDichVu(Long loaiDichVuId);
}
