package Modelo;

import java.util.*;

public abstract class Entidade {
    protected String idUnico;

    public Entidade(){
        this.idUnico = UUID.randomUUID().toString();
    }

    public abstract String toString();
}

