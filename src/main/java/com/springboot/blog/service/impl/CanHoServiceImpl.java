package com.springboot.blog.service.impl;

import com.springboot.blog.entity.*;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CanHoDto;
import com.springboot.blog.payload.CanHoResponse;
import com.springboot.blog.repository.CanHoRepository;
import com.springboot.blog.repository.LoaiCanHoRepository;
import com.springboot.blog.repository.ToaNhaRepository;
import com.springboot.blog.repository.UserRepository;
import com.springboot.blog.service.CanHoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CanHoServiceImpl implements CanHoService {

    private CanHoRepository canHoRepository;

    private ModelMapper modelMapper;

    private LoaiCanHoRepository loaiCanHoRepository;

    private ToaNhaRepository toaNhaRepository;

    private UserRepository userRepository;


    public CanHoServiceImpl(CanHoRepository canHoRepository, ModelMapper modelMapper, LoaiCanHoRepository loaiCanHoRepository, ToaNhaRepository toaNhaRepository, UserRepository userRepository){
        this.canHoRepository = canHoRepository;
        this.modelMapper = modelMapper;
        this.loaiCanHoRepository = loaiCanHoRepository;
        this.toaNhaRepository = toaNhaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CanHoResponse getAllCanHo(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<CanHo> canHos = canHoRepository.findAll(pageable);

        // get content for page object
        List<CanHo> listOfCanHo = canHos.getContent();

        List<CanHoDto> content= listOfCanHo.stream().map(canHo -> mapToDTO( canHo)).collect(Collectors.toList());

        CanHoResponse canHoReponse = new CanHoResponse();
        canHoReponse.setContent(content);
        canHoReponse.setPageNo(canHos.getNumber());
        canHoReponse.setPageSize(canHos.getSize());
        canHoReponse.setTotalElements(canHos.getTotalElements());
        canHoReponse.setTotalPages(canHos.getTotalPages());
        canHoReponse.setLast(canHos.isLast());

        return canHoReponse;
    }
    @Override
    public CanHoDto addCanHo(CanHoDto canHoDto) {
        LoaiCanHo loaiCanHo = loaiCanHoRepository.findById(canHoDto.getLoaiCanHoId())
                .orElseThrow(() -> new ResourceNotFoundException("Loai Can Ho","id", canHoDto.getLoaiCanHoId()));
        ToaNha toaNha = toaNhaRepository.findById((canHoDto.getToaNhaId()))
                .orElseThrow(()-> new ResourceNotFoundException("Toa Nha","id",canHoDto.getToaNhaId()));

        CanHo canHo = mapToEntity(canHoDto);
        canHo.setLoaiCanHo(loaiCanHo);
        canHo.setToaNha(toaNha);
        CanHo newCanHo = canHoRepository.save(canHo);

        CanHoDto canHoResponse = mapToDTO(newCanHo);
        return canHoResponse;
    }



    private CanHoDto mapToDTO(CanHo newCanHo) {
        CanHoDto canHoDto = modelMapper.map(newCanHo, CanHoDto.class);
        return  canHoDto;
    }

    private CanHo mapToEntity(CanHoDto canHoDto) {
        CanHo canHo = modelMapper.map(canHoDto, CanHo.class);
        return canHo;
    }

    @Override
    public CanHoDto getCanHoById(long id) {
        CanHo canHo = canHoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can Ho", "id", id));
        return mapToDTO(canHo);
    }

    @Override
    public void deleteCanHo(long id) {
        CanHo canHo = canHoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can Ho", "id", id));
        canHoRepository.delete(canHo);
    }

    @Override
    public CanHoDto updateCanHo(CanHoDto canHoDto, long id) {
        CanHo canHo = canHoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can Ho", "id", id));

        LoaiCanHo loaiCanHo = loaiCanHoRepository.findById(canHoDto.getLoaiCanHoId())
                .orElseThrow(() -> new ResourceNotFoundException("Loai Can Ho", "id", canHoDto.getLoaiCanHoId()));
        ToaNha toaNha = toaNhaRepository.findById(canHoDto.getToaNhaId())
                .orElseThrow(() -> new ResourceNotFoundException("Toa Nha", "id", canHoDto.getToaNhaId()));


        canHo.setTenCanHo(canHoDto.getTenCanHo());
        canHo.setGhiChu(canHoDto.getGhiChu());
        canHo.setLoaiCanHo(loaiCanHo);
        canHo.setToaNha(toaNha);
        CanHo updatedCanHo = canHoRepository.save(canHo);
        return mapToDTO(updatedCanHo);
    }

    @Override
    public List<CanHoDto> getCanHoByToaNhaId(Long toaNhaId) {
        ToaNha toaNha = toaNhaRepository.findById(toaNhaId)
                .orElseThrow(() -> new ResourceNotFoundException("Toa Nha", "id", toaNhaId));

        List<CanHo> canHos = canHoRepository.findByToaNhaId(toaNhaId);

        return canHos.stream().map(canHo -> mapToDTO(canHo))
                .collect(Collectors.toList());
    }

    @Override
    public List<CanHoDto> getCanHoByLoaiCanHoId(Long loaiCanHoId) {

        LoaiCanHo loaiCanHo = loaiCanHoRepository.findById(loaiCanHoId)
                .orElseThrow(() -> new ResourceNotFoundException("Loai Can Ho", "id", loaiCanHoId));

        List<CanHo> canHos = canHoRepository.findByLoaiCanHoId(loaiCanHoId);

        return canHos.stream().map(canHo -> mapToDTO(canHo))
                .collect(Collectors.toList());
    }
}
