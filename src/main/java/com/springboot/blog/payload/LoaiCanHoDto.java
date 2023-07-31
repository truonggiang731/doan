package com.springboot.blog.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiCanHoDto {
    private long id;
    private String name;
    private String dientich;
}
