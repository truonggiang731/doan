package com.springboot.blog.service;

import com.springboot.blog.payload.ToaNhaDto;

import java.util.List;

public interface ToaNhaService {
    ToaNhaDto addToaNha(ToaNhaDto toaNhaDto);

    ToaNhaDto getToaNha(Long toaNhaId);

    List<ToaNhaDto> getAllToaNha();

    ToaNhaDto updateToaNha(ToaNhaDto toaNhaDto, Long toaNhaId);

    void deleteToaNha(Long toaNhaId);
}
