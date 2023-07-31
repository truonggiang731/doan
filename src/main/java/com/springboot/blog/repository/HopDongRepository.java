package com.springboot.blog.repository;

import com.springboot.blog.entity.HopDong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HopDongRepository extends JpaRepository<HopDong, Long> {
    Optional<HopDong> findHopDongById(long id);

    List<HopDong> findHopDongByUserId(Long Id);

    List<HopDong> findHopDongByCanHoId(Long Id);

    List<HopDong> findHopDongByDichVuId(Long Id);
}
