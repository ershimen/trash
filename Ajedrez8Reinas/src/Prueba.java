public class Prueba{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        Tablero tablero1=new Tablero();
        tablero1.imprimir();
        tablero1.probar();


        long end = System.currentTimeMillis();
        System.out.println("Tiempo: "+(end-start)+" ms.");
    }
}
