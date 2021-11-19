import java.io.File;
import java.util.Scanner;

public class Jugador{
    private String nombre;
    private Personaje[] personajes=new Personaje[27];
    private File file;
    private Scanner sc;


    private int aciertos;
    private int fallos;
    private int alive;
    private int dead;
    private int ww;

    public Jugador(String nombre){
        this.nombre=nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public void setFile(String path) throws Exception{
        file=new File(path);
        sc=new Scanner(file);
    }
    public String getEstadoPersonaje(int index){
        return personajes[index].getEstado();
    }
    public void imprimirFile(){
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    }

    public void setPersonajesYEstados(){
        String aux1;
        String aux2;
        int i=0;
        while(sc.hasNextLine()){
            aux1=sc.next();
            aux2=sc.next();
            personajes[i]=new Personaje(aux1, aux2);
            sc.nextLine();
            i++;
        }
    }
    public void imprimir(){
        System.out.println();
        System.out.println("-------------"+nombre+"-------------");
        for(int i=0; i<27; i++){
            System.out.println(personajes[i].toString());
        }
        System.out.println();
    }
    public void info(){
        for(int i=0; i<27; i++){
            if("a".equals(personajes[i].getEstado())){
                alive++;
            }
            else if("d".equals(personajes[i].getEstado())){
                dead++;
            }
            else{
                ww++;
            }
        }
        System.out.println("  Alive: "+alive);
        System.out.println("  Dead: "+dead);
        System.out.println("  White walker: "+ww);
        System.out.println();
    }
    public void aciertos(Jugador real){
        aciertos=0;
		fallos=0;
        for(int i=0; i<27; i++){
            if(!"-".equals(real.getEstadoPersonaje(i))){
                if(personajes[i].getEstado().equals(real.getEstadoPersonaje(i))){
                    aciertos++;
                }
                else{
                    fallos++;
                }
            }
        }
        System.out.println("  Aciertos: "+aciertos);
        System.out.println("  Fallos: "+fallos);
        System.out.println();
    }
    public void comparar(Jugador real){
        String[] estadoPersonajes=new String[27];
        for(int i=0; i<27; i++){
            if(personajes[i].getEstado().equals(real.getEstadoPersonaje(i))){
                estadoPersonajes[i]="acierto";
            }
            else{
                estadoPersonajes[i]="fallo";
            }
        }
        System.out.println("----------"+nombre+" ha acertado con:");
        for(int k=0; k<27; k++){
            if("acierto".equals(estadoPersonajes[k])){
                System.out.println("  "+personajes[k].getNombre());
            }
        }
        System.out.println();
        System.out.println("----------"+nombre+" ha fallado con:");
        for(int j=0; j<27; j++){
            if("fallo".equals(estadoPersonajes[j])){
                if(!"-".equals(real.getEstadoPersonaje(j))){
                    System.out.println(" "+personajes[j].getNombre()+
                        ": "+real.getEstadoPersonaje(j));
                }
            }
        }
        System.out.println();
    }
    public void resultados(Jugador real, Jugador[] participantes){
        for(int i=0; i<participantes.length; i++){
            System.out.println(participantes[i].getNombre());
            participantes[i].aciertos(real);
        }
    }

    public void recuento(Jugador real, Jugador[] participantes){
        if(participantes[0].aciertos>participantes[1].aciertos &&
            participantes[0].aciertos>participantes[2].aciertos){
                if(participantes[1].aciertos>participantes[2].aciertos){
                    System.out.println("Mas aciertos: "+participantes[0].getNombre());
                    System.out.println("Segundo puesto: "+participantes[1].getNombre());
                    System.out.println("Tercer puesto: "+participantes[2].getNombre());
                }
                else{
                    System.out.println("Mas aciertos: "+participantes[0].getNombre());
                    System.out.println("Segundo puesto: "+participantes[2].getNombre());
                    System.out.println("Tercer puesto: "+participantes[1].getNombre());
                }
            }
        else if(participantes[1].aciertos>participantes[0].aciertos &&
            participantes[1].aciertos>participantes[2].aciertos){
                if(participantes[0].aciertos>participantes[2].aciertos){
                    System.out.println("Mas aciertos: "+participantes[1].getNombre());
                    System.out.println("Segundo puesto: "+participantes[0].getNombre());
                    System.out.println("Tercer puesto: "+participantes[2].getNombre());
                }
                else{
                    System.out.println("Mas aciertos: "+participantes[1].getNombre());
                    System.out.println("Segundo puesto: "+participantes[2].getNombre());
                    System.out.println("Tercer puesto: "+participantes[0].getNombre());
                }
            }
        else if(participantes[2].aciertos>participantes[0].aciertos &&
            participantes[2].aciertos>participantes[1].aciertos){
                if(participantes[1].aciertos>participantes[0].aciertos){
                    System.out.println("Mas aciertos: "+participantes[2].getNombre());
                    System.out.println("Segundo puesto: "+participantes[1].getNombre());
                    System.out.println("Tercer puesto: "+participantes[0].getNombre());
                }
                else{
                    System.out.println("Mas aciertos: "+participantes[2].getNombre());
                    System.out.println("Segundo puesto: "+participantes[0].getNombre());
                    System.out.println("Tercer puesto: "+participantes[1].getNombre());
                }
            }
    }
}
