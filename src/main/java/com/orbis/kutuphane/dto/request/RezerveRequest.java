package com.orbis.kutuphane.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RezerveRequest {
    Long kullaniciId;
    Long kitapKopyaId;
    String baslangic;
    String bitis;
}
