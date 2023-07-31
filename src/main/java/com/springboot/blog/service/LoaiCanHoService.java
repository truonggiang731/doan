package com.springboot.blog.service;

import com.springboot.blog.payload.LoaiCanHoDto;

import java.util.List;

public interface LoaiCanHoService {
    LoaiCanHoDto addLoaiCanHo(LoaiCanHoDto loaiCanHoDto);

    LoaiCanHoDto getLoaiCanHo(Long loaiCanHoId);

    List<LoaiCanHoDto> getAllLoaiCanHo();

    LoaiCanHoDto updateLoaiCanHo(LoaiCanHoDto loaiCanHoDto, Long loaiCanHoId);

    void deleteLoaiCanHo(Long loaiCanHoId);
}
