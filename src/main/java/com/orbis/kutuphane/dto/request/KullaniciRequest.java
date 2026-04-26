package com.orbis.kutuphane.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KullaniciRequest {

    @NotBlank(message = "Ad alanı boş olamaz")
    private String ad;

    private String soyad;

    @Email(message = "Geçerli bir email giriniz")
    private String eposta;

    @Pattern(regexp = "^[0-9]{10,11}$", message = "Geçerli bir telefon numarası giriniz")
    private String telefon;

    @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
    private String sifre;
}
