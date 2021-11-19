public class Posicion{
    private int fila;
    private int columna;

    public Posicion(int f, int c){
        fila=f;
        columna=c;
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
}
