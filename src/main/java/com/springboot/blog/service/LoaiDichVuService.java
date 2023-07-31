package com.springboot.blog.service;

import com.springboot.blog.payload.LoaiDichVuDto;

import java.util.List;

public interface LoaiDichVuService {
    LoaiDichVuDto addLoaiDichVu(LoaiDichVuDto loaiDichVuDto);
    LoaiDichVuDto getLoaiDichVuById(Long id);
    LoaiDichVuDto updateLoaiDichVu(LoaiDichVuDto loaiDichVuDto, Long id);
    List<LoaiDichVuDto> getAllLoaiDichVu();
    void deleteLoaiDichVu(Long id);

}
