package com.eigirdasmil.dviraciunuoma.ds;

public enum DviracioStatusasEnum {
    UZIMTAS,
    LAISVAS,
    ISTRINTAS;

    public static DviracioStatusasEnum fromInteger(int x) {
        switch(x) {
            case 0:
                return UZIMTAS;
            case 1:
                return LAISVAS;
            case 2:
                return ISTRINTAS;
        }
        return null;
    }
}
