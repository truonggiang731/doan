package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DichVuDto {
    private long id;

    @NotEmpty
    private long donGia;

    @NotEmpty
    private String tenDichVu;

    @NotEmpty
    private long trangThai;

    @Schema(
            description = "loai dich vu"
    )
    @NotEmpty
    private long loaiDichVuId;
}
