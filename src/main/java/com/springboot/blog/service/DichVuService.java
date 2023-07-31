package com.springboot.blog.service;

import com.springboot.blog.entity.DichVu;
import com.springboot.blog.payload.DichVuDto;

import java.util.List;

public interface DichVuService {
    DichVuDto addDichVu(DichVuDto dichVuDto);
    DichVuDto getDichVuById(Long id);
    List<DichVuDto> getDichVuByLoaiDichVuId(Long loaiDichVuId);
    void deleteDichVu(Long id);
    DichVuDto updateDichVu(DichVuDto dichVuDto, long id);
    List<DichVuDto> getAllDichVu();

}
