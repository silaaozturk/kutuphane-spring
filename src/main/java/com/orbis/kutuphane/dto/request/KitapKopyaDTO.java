package com.orbis.kutuphane.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitapKopyaDTO {

    @NotBlank
    private String barkodNo;

    private int adet;

    private Long kitapId;

    private Long subeId;

}
