import java.util.Scanner;
class Partida{
    private Matriz matriz;
    private String jugador1;
    private String jugador2;

    private int[] espaciosOcupados;

    private Scanner sc = new Scanner(System.in);

    public Partida(String jugador1, String jugador2){
        matriz=new Matriz();
        matriz.rellenar();
        if(jugador1!=jugador2){
            this.jugador1=jugador1;
            this.jugador2=jugador2;
        }
        else{
            this.jugador1=jugador1+"(1)";
            this.jugador2=jugador2+"(2)";
        }
        espaciosOcupados=new int[9];
    }

    public void imprimir(){
        System.out.println();
        matriz.imprimir();
        System.out.println();
    }

    public void jugar(){

        matriz.imprimirDefault();
        System.out.println();
        matriz.imprimir();
        System.out.println();
        System.out.println("//////////////////");
        int counter=0;
        int x;
        boolean done;
        while(counter<9 && !matriz.comprobar()){
            done=false;
            if(counter%2==0){
                System.out.println("Turno de "+jugador1);
                while(!done){
                    x=sc.nextInt();
                    if(x>0 && x<=9){
                        while(matriz.ocupado(x)){
                            System.out.println("Posicion ocupada, pruebe otra");
                            matriz.imprimir();
                            x=sc.nextInt();
                        }

                        switch(x){
                            case 1: matriz.jugar(0,0,"X");break;
                            case 2: matriz.jugar(0,1,"X");break;
                            case 3: matriz.jugar(0,2,"X");break;
                            case 4: matriz.jugar(1,0,"X");break;
                            case 5: matriz.jugar(1,1,"X");break;
                            case 6: matriz.jugar(1,2,"X");break;
                            case 7: matriz.jugar(2,0,"X");break;
                            case 8: matriz.jugar(2,1,"X");break;
                            case 9: matriz.jugar(2,2,"X");break;
                        }
                        done=true;
                    }
                    else{
                        System.out.println("Indice incorrecto");
                    }
                }
            }
            else{
                System.out.println("Turno de "+jugador2);
                while(!done){
                    x=sc.nextInt();
                    if(x>=0 && x<=9){
                        while(matriz.ocupado(x)){
                            System.out.println("------------------");
                            System.out.println("Posicion ocupada, pruebe otra");
                            matriz.imprimir();
                            x=sc.nextInt();
                        }

                        switch(x){
                            case 1: matriz.jugar(0,0,"O");break;
                            case 2: matriz.jugar(0,1,"O");break;
                            case 3: matriz.jugar(0,2,"O");break;
                            case 4: matriz.jugar(1,0,"O");break;
                            case 5: matriz.jugar(1,1,"O");break;
                            case 6: matriz.jugar(1,2,"O");break;
                            case 7: matriz.jugar(2,0,"O");break;
                            case 8: matriz.jugar(2,1,"O");break;
                            case 9: matriz.jugar(2,2,"O");break;
                        }
                        done=true;
                    }
                    else{
                        System.out.println("Indice incorrecto");
                    }
                }
            }
            counter++;
            System.out.println();
            matriz.imprimir();
            System.out.println();
            System.out.println("//////////////////");
        }
        if(matriz.comprobar()){
            if(counter%2==0){
                System.out.println("Ha ganado "+jugador2);
            }
            else{
                System.out.println("Ha ganado "+jugador1);
            }
        }
        if(counter==9 && !matriz.comprobar()){
            System.out.println("Empate");
        }
    }
}
