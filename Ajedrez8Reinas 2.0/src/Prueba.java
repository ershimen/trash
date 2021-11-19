public class Prueba{
    public static void main(String[] args) {
        //long start = System.currentTimeMillis();


        Tablero tablero1=new Tablero();
        tablero1.imprimir();

        long start1 = System.currentTimeMillis();
        tablero1.probar1();
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        tablero1.probar2();
        long end2 = System.currentTimeMillis();

        long start3 = System.currentTimeMillis();
        tablero1.probar3();
        long end3 = System.currentTimeMillis();
/*
        long start4 = System.currentTimeMillis();
        tablero1.probar4();
        long end4 = System.currentTimeMillis();
*/
        System.out.println("Tiempo 1: "+(end1-start1)+" ms");
        System.out.println("Tiempo 2: "+(end2-start2)+" ms");
        System.out.println("Tiempo 3: "+(end3-start3)+" ms");
        //System.out.println("Tiempo 4: "+(end4-start4)+" ms");
        
        //long end = System.currentTimeMillis();
        //System.out.println("Tiempo: "+(end-start)+" ms.");
    }
}
