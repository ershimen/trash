package tablero;

import piezas.Figura;

public class Casilla{

    private Pieza pieza;
    private int fila;
    private int columna;

    public Casilla(int fila, int columna){
        this.fila=fila;
        this.columna=columna;
    }
    public Pieza getPieza(){
        return figura;
    }
    public void setPieza(Pieza pieza){
        this.pieza=pieza;
    }
    public void quitarPieza(){
        pieza=null;
    }
}
