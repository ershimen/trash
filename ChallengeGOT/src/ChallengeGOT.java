//import java.io.File;
//import java.util.Scanner;

public class ChallengeGOT{
    public static void main(String[] args) throws Exception{

        Jugador real=new Jugador("Real");
        real.setFile("D:\\Escritorio\\I\\Java projects\\ChallengeGOT\\datos\\Real.txt");
        //real.imprimirFile();
        real.setPersonajesYEstados();
        //real.imprimir();
        //real.info();

        System.out.println();
        System.out.println("////////////////////////////////////////////////");

        Jugador juanjo=new Jugador("Juanjo");
        juanjo.setFile("D:\\Escritorio\\I\\Java projects\\ChallengeGOT\\datos\\Juanjo.txt");
        //juanjo.imprimirFile();
        juanjo.setPersonajesYEstados();
        juanjo.imprimir();
        juanjo.info();
        juanjo.aciertos(real);
        juanjo.comparar(real);

        System.out.println();
        System.out.println("////////////////////////////////////////////////");

        Jugador alvaro=new Jugador("Alvaro");
        alvaro.setFile("D:\\Escritorio\\I\\Java projects\\ChallengeGOT\\datos\\Alvaro.txt");
        //alvaro.imprimirFile();
        alvaro.setPersonajesYEstados();
        alvaro.imprimir();
        alvaro.info();
        alvaro.aciertos(real);
        alvaro.comparar(real);

        System.out.println();
        System.out.println("////////////////////////////////////////////////");

		Jugador jan=new Jugador("Jan");
		jan.setFile("D:\\Escritorio\\I\\Java projects\\ChallengeGOT\\datos\\Jan.txt");
		//jan.imprimirFile();
		jan.setPersonajesYEstados();
		jan.imprimir();
        jan.info();
        jan.aciertos(real);
        jan.comparar(real);

        System.out.println();
        System.out.println("////////////////////////////////////////////////");

        Jugador[] participantes=new Jugador[3];
        participantes[0]=juanjo;
        participantes[1]=alvaro;
        participantes[2]=jan;

        real.resultados(real, participantes);

        System.out.println("////////////////////////////////////////////////");
        real.recuento(real, participantes);
    }
}
