package com.springboot.blog.service.impl;

import com.springboot.blog.entity.CanHo;
import com.springboot.blog.entity.LoaiCanHo;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CanHoDto;
import com.springboot.blog.payload.LoaiCanHoDto;
import com.springboot.blog.repository.CanHoRepository;
import com.springboot.blog.repository.LoaiCanHoRepository;
import com.springboot.blog.service.LoaiCanHoService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoaiCanHoServiceImpl implements LoaiCanHoService {

    private LoaiCanHoRepository loaiCanHoRepository;
    private ModelMapper modelMapper;

    public LoaiCanHoServiceImpl(LoaiCanHoRepository loaiCanHoRepository, ModelMapper modelMapper){
        this.loaiCanHoRepository = loaiCanHoRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public LoaiCanHoDto addLoaiCanHo(LoaiCanHoDto loaiCanHoDto){
        LoaiCanHo loaiCanHo = modelMapper.map(loaiCanHoDto, LoaiCanHo.class);
        LoaiCanHo saveLoaiCanHo = loaiCanHoRepository.save(loaiCanHo);
        return modelMapper.map(saveLoaiCanHo, LoaiCanHoDto.class);
    }

    @Override
    public LoaiCanHoDto getLoaiCanHo(Long loaiCanHoId) {
        LoaiCanHo loaiCanHo = loaiCanHoRepository.findById(loaiCanHoId)
                .orElseThrow(()-> new ResourceNotFoundException("loai can ho", "id", loaiCanHoId));

        return modelMapper.map(loaiCanHo, LoaiCanHoDto.class);
    }

    @Override
    public List<LoaiCanHoDto> getAllLoaiCanHo() {
        List<LoaiCanHo> loaiCanHos = loaiCanHoRepository.findAll();
        return loaiCanHos.stream().map(loaiCanHo -> modelMapper.map(loaiCanHo, LoaiCanHoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LoaiCanHoDto updateLoaiCanHo(LoaiCanHoDto loaiCanHoDto, Long loaiCanHoId) {
        LoaiCanHo loaiCanHo = loaiCanHoRepository.findById(loaiCanHoId)
                .orElseThrow(()-> new ResourceNotFoundException("loai can ho", "id", loaiCanHoId));
        loaiCanHo.setName(loaiCanHoDto.getName());
        loaiCanHo.setDientich(loaiCanHo.getDientich());
        LoaiCanHo updateLoaiCanHo = loaiCanHoRepository.save(loaiCanHo);
         return modelMapper.map(updateLoaiCanHo,LoaiCanHoDto.class);
    }

    @Override
    public void deleteLoaiCanHo(Long loaiCanHoId) {
        LoaiCanHo loaiCanHo = loaiCanHoRepository.findById(loaiCanHoId)
                .orElseThrow(()-> new ResourceNotFoundException("loai can ho", "id", loaiCanHoId));

        loaiCanHoRepository.delete(loaiCanHo);

    }
}
