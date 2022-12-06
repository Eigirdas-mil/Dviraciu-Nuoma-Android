package com.eigirdasmil.dviraciunuoma.ds;

public enum DviracioTipasEnum {
    MIESTO,
    PLENTINIS,
    SPORTINIS;

    public static DviracioTipasEnum fromInteger(int x) {
        switch(x) {
            case 0:
                return MIESTO;
            case 1:
                return PLENTINIS;
            case 2:
                return SPORTINIS;
        }
        return null;
    }
}
