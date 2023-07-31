package com.springboot.blog.controller;

import com.springboot.blog.payload.ToaNhaDto;
import com.springboot.blog.service.ToaNhaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/toanha")
public class ToaNhaController {
    private ToaNhaService toaNhaService;

    public ToaNhaController(ToaNhaService toaNhaService) {
        this.toaNhaService = toaNhaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ToaNhaDto> addToaNha(@RequestBody ToaNhaDto toaNhaDto){
        System.out.println("ok");
        ToaNhaDto saveToaNha = toaNhaService.addToaNha(toaNhaDto);
        System.out.println(toaNhaDto.getName());
        System.out.println(saveToaNha.getName());
        return new ResponseEntity<>(saveToaNha, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<ToaNhaDto> getToaNha(@PathVariable("id") Long toaNhaId){
        ToaNhaDto toaNhaDto = toaNhaService.getToaNha(toaNhaId);
        return ResponseEntity.ok(toaNhaDto);
    }
    @GetMapping
    public ResponseEntity<List<ToaNhaDto>> getAllToaNha(){
        return ResponseEntity.ok(toaNhaService.getAllToaNha());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<ToaNhaDto> updateToaNha(@RequestBody ToaNhaDto toaNhaDto,
                                                      @PathVariable("id") Long toaNhaId){
        return ResponseEntity.ok(toaNhaService.updateToaNha(toaNhaDto, toaNhaId));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteToaNha(@PathVariable("id") Long toaNhaId){
        toaNhaService.deleteToaNha(toaNhaId);
        return ResponseEntity.ok("Toa nha deleted successfully!.");
    }
}
