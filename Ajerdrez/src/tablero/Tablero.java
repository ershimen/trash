package tablero;

import piezas.Pieza;
import tablero.Casilla;
import tablero.Posicion;
import java.util.ArrayList;

public class Tablero{
    private Casilla[][] tablero=new Casilla[8][8];


    public Tablero(){
        String auxCodigo;
        Figura aux;

        //peones
        for(int i=0; i<8; i++){
            auxCodigo="NP"+(i+1);
            aux= new Figura(auxCodigo, "peon", "negro");
            tablero[1][i]=new Casilla(aux, 1, i);
        }
        for(int i=0; i<8; i++){
            auxCodigo="BP"+(i+1);
            aux= new Figura(auxCodigo, "peon", "blanco");
            tablero[6][i]=new Casilla(aux, 6, i);
        }
        //torres
        aux=new Figura("NT1", "torre", "negro");
        tablero[0][0]=new Casilla(aux, 0, 0);
        aux=new Figura("NT2", "torre", "negro");
        tablero[0][7]=new Casilla(aux, 0 ,7);

        aux=new Figura("BT1", "torre", "blanco");
        tablero[7][0]=new Casilla(aux, 7, 0);
        aux=new Figura("BT2", "torre", "blanco");
        tablero[7][7]=new Casilla(aux, 7, 7);

        //caballos
        aux=new Figura("NC1", "caballo", "negro");
        tablero[0][1]=new Casilla(aux, 0, 1);
        aux=new Figura("NC2", "caballo", "negro");
        tablero[0][6]=new Casilla(aux, 0, 6);

        aux=new Figura("BC1", "caballo", "blanco");
        tablero[7][1]=new Casilla(aux, 7, 1);
        aux=new Figura("BC2", "caballo", "blanco");
        tablero[7][6]=new Casilla(aux, 7, 6);

        //alfiles
        aux=new Figura("NA1", "alfil", "negro");
        tablero[0][2]=new Casilla(aux, 0, 2);
        aux=new Figura("NA2", "alfil", "negro");
        tablero[0][5]=new Casilla(aux, 0, 5);

        aux=new Figura("BA1", "alfil", "blanco");
        tablero[7][2]=new Casilla(aux, 7, 2);
        aux=new Figura("BA2", "alfil", "blanco");
        tablero[7][5]=new Casilla(aux, 7, 5);

        //reinas
        aux=new Figura("NRI", "reina", "negro");
        tablero[0][3]=new Casilla(aux, 0, 3);

        aux=new Figura("BRI", "reina", "blanco");
        tablero[7][3]=new Casilla(aux, 7, 3);

        //reyes
        aux=new Figura("NRE", "rey", "negro");
        tablero[0][4]=new Casilla(aux, 0, 4);

        aux=new Figura("BRE", "rey", "blanco");
        tablero[7][4]=new Casilla(aux, 7, 4);

        for(int i=2; i<6; i++){
            for(int j=0; j<8; j++){
                tablero[i][j]=new Casilla(i, j);
            }
        }
    }

    public void imprimir(){
        String aux="|";
        System.out.println();
        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println("  _________________________________");
        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if(tablero[i][j].getFigura()!=null){
                    aux=aux+tablero[i][j].getFigura().getCodigo()+"|";
                }
                else{
                    aux=aux+"   |";
                }
            }
            System.out.println((8-i)+" "+aux+" "+(8-i));
            System.out.println("  _________________________________");
            aux="|";
        }

        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println();
    }

    public Pieza getPiezaAt(int fila, int columna){
        return tablero[fila][columna].getPieza();
    }

    public boolean movimientoValido(Posicion origen, Posicion destino){
        int filaOrigen=origen.getFila();
        int columnaOrigen=origen.getColumna();
        int filaDestino=destino.getFila();
        int columnaDestino=destino.getColumna();
        return (filaDestino>=0 && filaDestino<8 && columnaDestino>=0 && columnaDestino<8
                && (getPiezaAt(filaOrigen,columnaOrigen).getEquipo()!=getPiezaAt(filaDestino,columnaDestino).getEquipo()));
    }

    public ArrayList<Casilla> moverA(ArrayList<Casilla> resultado, int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino){

        Pieza piezaOrigen=getPiezaAt(filaOrigen, columnaOrigen);
        Pieza piezaDestino=getPiezaAt(filaDestino, columnaDestino);
        if(pieza==null || (piezaDestino.getEquipo()!=piezaOrigen.getEquipo())){
            resultado.add(new Casilla(filaDestino, columnaDestino));
        }
        return resultado;

    }
    public ArrayList<Casilla> moverHasta(ArrayList<Casilla> resultado, Posicion origen, int incFila, int incCol){

        int fila=origen.getFila()+incFila;
        int columna=origen.getColumna()+incCol;

        Pieza pieza=getPiezaAt(fila, columna);
        Posicion pos=new Posicion(fila, columna);

        boolean aux=false;

        while(movimientoValido(origen, pos) && !aux){
            pos.set(filaOrigen, columnaOrigen);
            pieza=getPiezaAt(fila, columna);

            moverHasta(resultado, origen.getFila(), origen.getColumna(), fila, columna);

            if(pieza!=null){
                aux=true;
            }
            fila=fila+incFila;
            columna=columna+incCol;
        }

    }

    public void mover(Posicion origen, Posicion destino){
        int filaOrigen=origen.getFila();
        int columnaOrigen=origen.getColumna();
        int filaDestino=destino.getFila();
        int columnaDestino=destino.getColumna();

        Pieza piezaOrigen=getPiezaAt(filaOrigen, columnaOrigen);
        Pieza piezaDestino=getPiezaAt(filaDestino, columnaDestino);

        tipoDePieza=piezaOrigen.getTipoPieza();
        switch(tipoDePieza){
            case PEON:



        }
    }
}
