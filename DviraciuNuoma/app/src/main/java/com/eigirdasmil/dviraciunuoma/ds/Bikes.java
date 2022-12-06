package com.eigirdasmil.dviraciunuoma.ds;

import java.io.Serializable;
import java.util.UUID;

public class Bikes implements Serializable {
    public UUID id;
    public String dviracioPavadinimas;
    public DviracioTipasEnum dviracioTipas;
    public Double dviracioKaina;
    public String dviracioSpecifikacija;
    public DviracioStatusasEnum dviracioStatusas;
}
