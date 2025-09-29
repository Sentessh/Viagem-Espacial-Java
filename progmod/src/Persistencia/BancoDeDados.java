package Persistencia;

import Modelo.Astronauta;
import Modelo.Nave;
import Modelo.Missao;

public class BancoDeDados {
    private Persistente<Astronauta> astronautas;
    private Persistente<Nave> naves;
    private Persistente<Missao> missoes;

    public BancoDeDados() {
        this.astronautas = new Persistente<>();
        this.naves = new Persistente<>();
        this.missoes = new Persistente<>();
    }

    public Persistente<Astronauta> getAstronautas() {
        return astronautas;
    }

    public void setAstronautas(Persistente<Astronauta> astronautas) {
        this.astronautas = astronautas;
    }

    public Persistente<Nave> getNaves() {
        return naves;
    }

    public void setNaves(Persistente<Nave> naves) {
        this.naves = naves;
    }

    public Persistente<Missao> getMissoes() {
        return missoes;
    }

    public void setMissoes(Persistente<Missao> missoes) {
        this.missoes = missoes;
    }
}