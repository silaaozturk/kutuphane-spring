package com.orbis.kutuphane;

public class GENEL {

    public enum Rol {
        ADMIN(0),
        UYE(1);
        private final int deger;

        Rol(int deger) {
            this.deger = deger;
        }

        public int getDeger() {
            return deger;
        }
    }

    public enum OduncDurum {
        ODUNC_ALINDI,
        IADE_EDILDI,
        GECIKTI,
        REZERVE,
        REZERVE_IPTAL
    }

}
