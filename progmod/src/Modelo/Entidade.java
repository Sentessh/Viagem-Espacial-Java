package Modelo;

import java.util.*;

//CLASSE ABSTRATA - BASE P/ TODAS AS ENTIDADES DO SISTEMA
public abstract class Entidade {
    protected String idUnico;

    public Entidade(){
        //IDENTIFICADOR ÚNICO UNIVERSAL
        this.idUnico = UUID.randomUUID().toString();
    }
    /*METODO ABSTRATO - FORNÇA AS SUBCLASSES A TER
    O MÉTODO toString*/
    public abstract String toString();
}

