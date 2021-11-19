package piezas;

import tablero.Equipo;


public enum TipoPieza{PEON, TORRE, CABALLO, ALFIL, REINA, REY}

public class Pieza{

    private Equipo equipo;
    private TipoPieza tipoPieza;
    private Jugador jugador;

    public Pieza(TipoPieza tipoPieza, Jugador jugador, Equipo equipo){
        this.equipo=equipo;
        this.jugador=jugador;
        this.tipoPieza=tipoPieza;
    }
    public Equipo getEquipo(){
        return equipo;
    }
    public TipoPieza getTipoPieza(){
        return tipoPieza;
    }
}
