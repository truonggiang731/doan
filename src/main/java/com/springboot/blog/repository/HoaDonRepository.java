package com.springboot.blog.repository;

import com.springboot.blog.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    List<HoaDon> findHoaDonByHopDongId(long hopDongId);

}
