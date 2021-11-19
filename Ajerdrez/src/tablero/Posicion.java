package tablero;

public class Posicion{

    private int fila;
    private int columna;

    public Posicion(int fila, int columna){
        this.fila=fila;
        this.columna=columna;
    }
    public int getFila(){
        return fila;
    }
    public int getColumna(){
        return columna;
    }
    public void set(int fila, int col){
        this.fila=fila;
        this.columna=col;
    }
    public String toString(){
        return "("+fila+","+columna+")";
    }
    public String toCodigo(){
        String aux="";
        switch(columna){
            case 0: aux="A";break;
            case 1: aux="B";break;
            case 2: aux="C";break;
            case 3: aux="D";break;
            case 4: aux="E";break;
            case 5: aux="F";break;
            case 6: aux="G";break;
            case 7: aux="H";break;
        }
        switch (fila){
            case 0: aux=aux+"8";break;
            case 1: aux=aux+"7";break;
            case 2: aux=aux+"6";break;
            case 3: aux=aux+"5";break;
            case 4: aux=aux+"4";break;
            case 5: aux=aux+"3";break;
            case 6: aux=aux+"2";break;
            case 7: aux=aux+"1";break;
        }
        return aux;
    }
}
