package com.orbis.kutuphane.dto.request;
import lombok.Data;

@Data
public class GirisRequest {
    private String eposta;
    private String sifre;
}