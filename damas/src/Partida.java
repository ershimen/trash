public class Partida{
    public static void main(String[] args) {
        Tablero tablero1=new Tablero();
        tablero1.imprimir();

        Posicion origen=new Posicion(1,0);
        Posicion destino=new Posicion(2,1);
        tablero1.mover(origen, destino);
        tablero1.imprimir();
    }
}
