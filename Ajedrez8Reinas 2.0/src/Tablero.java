import java.util.ArrayList;

public class Tablero{
    private Casilla[][] tablero;

    ArrayList<Integer> filasVisitables=new ArrayList<>();
    ArrayList<Integer> columnasVisitables=new ArrayList<>();

    ArrayList<Integer> diagonalesVisitablesPrin=new ArrayList<>();
    ArrayList<Integer> diagonalesVisitablesSec=new ArrayList<>();


    public Tablero(){
        tablero=new Casilla[8][8];
        Posicion aux;

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                aux=new Posicion(i,j);
                tablero[i][j]=new Casilla(aux);
            }
        }
        for(int i=0; i<8; i++){
            columnasVisitables.add(i);
        }
        for(int i=0; i<16; i++){
            diagonalesVisitablesPrin.add(i);
            diagonalesVisitablesSec.add(i);
        }

    }

    public void imprimir(){
        String aux;

        System.out.println();
        System.out.println("_________________________________");
        for(int i=0; i<8; i++){
            aux="|";
            for(int j=0; j<8; j++){
                aux=aux+tablero[i][j].getFigura()+"|";
            }
            System.out.println(aux);
            System.out.println("_________________________________");
        }
        System.out.println();
    }


    public boolean diagonalAbajoDerecha(int f, int c){
        boolean aux=true;
        int i=f+1;
        int j=c+1;
        while(i<8 && j<8 && aux){
            if(tablero[i][j].getFigura()!="___"){
                aux=false;
            }
            i++;
            j++;
        }
        return aux;
    }
    public boolean diagonalAbajoIzquierda(int f, int c){
        boolean aux=true;
        int i=f+1;
        int j=c-1;
        while(i<8 && j>=0 && aux){
            if(tablero[i][j].getFigura()!="___"){
                aux=false;
            }
            i++;
            j--;
        }
        return aux;
    }
    public boolean diagonalArribaDerecha(int f, int c){
        boolean aux=true;
        int i=f-1;
        int j=c+1;
        while(i>=0 && j<8 && aux){
            if(tablero[i][j].getFigura()!="___"){
                aux=false;
            }
            i--;
            j++;
        }
        return aux;
    }
    public boolean diagonalArribaIzquierda(int f, int c){
        boolean aux=true;
        int i=f-1;
        int j=c-1;
        while(i>=0 && j>=0 && aux){
            if(tablero[i][j].getFigura()!="___"){
                aux=false;
            }
            i--;
            j--;
        }
        return aux;
    }

    public boolean diagonales(int f, int c){
        boolean aux;
        aux=diagonalAbajoDerecha(f,c) && diagonalArribaDerecha(f,c) && diagonalAbajoIzquierda(f,c) && diagonalArribaIzquierda(f,c);
        return aux;
    }

    public boolean tableroSinAtacar(){
        boolean aux=true;

        //fila 0
        for(int i=0; i<8; i++){
            if(tablero[0][i].getFigura()!="___"){
                aux=diagonales(0,i);
            }
        }
        //fila 1
        for(int i=0; i<8 && aux; i++){
            if(tablero[1][i].getFigura()!="___"){
                aux=aux && diagonales(1,i);
            }
        }
        //fila 2
        for(int i=0; i<8 && aux; i++){
            if(tablero[2][i].getFigura()!="___"){
                aux=aux && diagonales(2,i);
            }
        }
        //fila 3
        for(int i=0; i<8 && aux; i++){
            if(tablero[3][i].getFigura()!="___"){
                aux=aux && diagonales(3,i);
            }
        }
        //fila 4
        for(int i=0; i<8 && aux; i++){
            if(tablero[4][i].getFigura()!="___"){
                aux=aux && diagonales(4,i);
            }
        }
        //fila 5
        for(int i=0; i<8 && aux; i++){
            if(tablero[5][i].getFigura()!="___"){
                aux=aux && diagonales(5,i);
            }
        }
        //fila 6
        for(int i=0; i<8 && aux; i++){
            if(tablero[6][i].getFigura()!="___"){
                aux=aux && diagonales(6,i);
            }
        }
        //fila 7
        for(int i=0; i<8 && aux; i++){
            if(tablero[7][i].getFigura()!="___"){
                aux=aux && diagonales(7,i);
            }
        }
        return aux;
    }

    public void setTableroFila(int fila, int col, String cosa){
        tablero[fila][col].setFigura(cosa);
    }

    public void probar1(){

        int contador=0;     //numero de soluciones
        int aux=0;          //numero de iteraciones




        for(int i0=0; i0<8; i0++){
            if(columnasVisitables.contains(i0)){
                columnasVisitables.remove((Integer)i0);
                setTableroFila(0, i0,"000");
                for(int i1=0; i1<8; i1++){
                    if(columnasVisitables.contains(i1)){
                        columnasVisitables.remove((Integer)i1);
                        setTableroFila(1, i1,"000");
                        for(int i2=0; i2<8; i2++){
                            if(columnasVisitables.contains(i2)){
                                columnasVisitables.remove((Integer)i2);
                                setTableroFila(2, i2,"000");
                                for(int i3=0; i3<8; i3++){
                                    if(columnasVisitables.contains(i3)){
                                        columnasVisitables.remove((Integer)i3);
                                        setTableroFila(3, i3,"000");
                                        for(int i4=0; i4<8; i4++){
                                            if(columnasVisitables.contains(i4)){
                                                columnasVisitables.remove((Integer)i4);
                                                setTableroFila(4, i4,"000");
                                                for(int i5=0; i5<8; i5++){
                                                    if(columnasVisitables.contains(i5)){
                                                        columnasVisitables.remove((Integer)i5);
                                                        setTableroFila(5, i5,"000");
                                                        for(int i6=0; i6<8; i6++){
                                                            if(columnasVisitables.contains(i6)){
                                                                columnasVisitables.remove((Integer)i6);
                                                                setTableroFila(6, i6,"000");
                                                                for(int i7=0; i7<8; i7++){
                                                                    if(columnasVisitables.contains(i7)){
                                                                        columnasVisitables.remove((Integer)i7);
                                                                        setTableroFila(7, i7,"000");

                                                                        aux++;
                                                                        //System.out.println(aux);
                                                                        if(tableroSinAtacar()){
                                                                            contador++;
                                                                            System.out.println();
                                                                            System.out.println(contador);
                                                                            imprimir();
                                                                        }

                                                                        columnasVisitables.add(i7);
                                                                        setTableroFila(7, i7,"___");
                                                                    }
                                                                }
                                                                columnasVisitables.add(i6);
                                                                setTableroFila(6, i6,"___");
                                                            }
                                                        }
                                                        columnasVisitables.add(i5);
                                                        setTableroFila(5, i5,"___");
                                                    }
                                                }
                                                columnasVisitables.add(i4);
                                                setTableroFila(4, i4,"___");
                                            }
                                        }
                                        columnasVisitables.add(i3);
                                        setTableroFila(3, i3,"___");
                                    }
                                }
                                columnasVisitables.add(i2);
                                setTableroFila(2, i2,"___");
                            }
                        }
                        columnasVisitables.add(i1);
                        setTableroFila(1, i1,"___");
                    }
                }
                columnasVisitables.add(i0);
                setTableroFila(0, i0,"___");
            }
        }
    }

    public void probar2(){

        int contador=0;     //numero de soluciones
        int aux=0;          //numero de iteraciones

        for(int i0=0; i0<8; i0++){
            for(int i1=0; i1<8; i1++){
                for(int i2=0; i2<8; i2++){
                    for(int i3=0; i3<8; i3++){
                        for(int i4=0; i4<8; i4++){
                            for(int i5=0; i5<8; i5++){
                                for(int i6=0; i6<8; i6++){
                                    for(int i7=0; i7<8; i7++){
                                        if(columnasVisitables.contains(i0)){
                                            columnasVisitables.remove((Integer)i0);
                                            setTableroFila(0, i0,"000");
                                            if(columnasVisitables.contains(i1)){
                                                columnasVisitables.remove((Integer)i1);
                                                setTableroFila(1, i1,"000");
                                                if(columnasVisitables.contains(i2)){
                                                    columnasVisitables.remove((Integer)i2);
                                                    setTableroFila(2, i2,"000");
                                                    if(columnasVisitables.contains(i3)){
                                                        columnasVisitables.remove((Integer)i3);
                                                        setTableroFila(3, i3,"000");
                                                        if(columnasVisitables.contains(i4)){
                                                            columnasVisitables.remove((Integer)i4);
                                                            setTableroFila(4, i4,"000");
                                                            if(columnasVisitables.contains(i5)){
                                                                columnasVisitables.remove((Integer)i5);
                                                                setTableroFila(5, i5,"000");
                                                                if(columnasVisitables.contains(i6)){
                                                                    columnasVisitables.remove((Integer)i6);
                                                                    setTableroFila(6, i6,"000");
                                                                    if(columnasVisitables.contains(i7)){
                                                                        columnasVisitables.remove((Integer)i7);
                                                                        setTableroFila(7, i7,"000");

                                                                        aux++;
                                                                        //System.out.println(aux);
                                                                        if(tableroSinAtacar()){
                                                                            contador++;
                                                                            System.out.println();
                                                                            System.out.println(contador);
                                                                            imprimir();
                                                                        }

                                                                        columnasVisitables.add(i7);
                                                                        setTableroFila(7, i7,"___");
                                                                    }
                                                                    columnasVisitables.add(i6);
                                                                    setTableroFila(6, i6,"___");
                                                                }
                                                                columnasVisitables.add(i5);
                                                                setTableroFila(5, i5,"___");
                                                            }
                                                            columnasVisitables.add(i4);
                                                            setTableroFila(4, i4,"___");
                                                        }
                                                        columnasVisitables.add(i3);
                                                        setTableroFila(3, i3,"___");
                                                    }
                                                    columnasVisitables.add(i2);
                                                    setTableroFila(2, i2,"___");
                                                }
                                                columnasVisitables.add(i1);
                                                setTableroFila(1, i1,"___");
                                            }
                                            columnasVisitables.add(i0);
                                            setTableroFila(0, i0,"___");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }





    }

    public void probar3(){

        int contador=0;     //numero de soluciones
        int aux=0;          //numero de iteraciones



        for(int i0=0; i0<8; i0++){
            columnasVisitables.remove((Integer)i0);
            diagonalesVisitablesSec.remove((Integer)(7-i0));
            diagonalesVisitablesPrin.remove((Integer)i0);
            setTableroFila(0, i0,"000");
            for(int i1=0; i1<8; i1++){
                if(columnasVisitables.contains(i1) && diagonalesVisitablesSec.contains(8-i1) && diagonalesVisitablesPrin.contains(1+i1)){
                    columnasVisitables.remove((Integer)i1);
                    diagonalesVisitablesSec.remove((Integer)(8-i1));
                    diagonalesVisitablesPrin.remove((Integer)(1+i1));
                    setTableroFila(1, i1,"000");
                    for(int i2=0; i2<8; i2++){
                        if(columnasVisitables.contains(i2) && diagonalesVisitablesSec.contains(9-i2) && diagonalesVisitablesPrin.contains(2+i2)){
                            columnasVisitables.remove((Integer)i2);
                            diagonalesVisitablesSec.remove((Integer)(9-i2));
                            diagonalesVisitablesPrin.remove((Integer)(2+i2));
                            setTableroFila(2, i2,"000");
                            for(int i3=0; i3<8; i3++){
                                if(columnasVisitables.contains(i3) && diagonalesVisitablesSec.contains(10-i3) && diagonalesVisitablesPrin.contains(3+i3)){
                                    columnasVisitables.remove((Integer)i3);
                                    diagonalesVisitablesSec.remove((Integer)(10-i3));
                                    diagonalesVisitablesPrin.remove((Integer)(3+i3));
                                    setTableroFila(3, i3,"000");
                                    for(int i4=0; i4<8; i4++){
                                        if(columnasVisitables.contains(i4) && diagonalesVisitablesSec.contains(11-i4) && diagonalesVisitablesPrin.contains(4+i4)){
                                            columnasVisitables.remove((Integer)i4);
                                            diagonalesVisitablesSec.remove((Integer)(11-i4));
                                            diagonalesVisitablesPrin.remove((Integer)(4+i4));
                                            setTableroFila(4, i4,"000");
                                            for(int i5=0; i5<8; i5++){
                                                if(columnasVisitables.contains(i5) && diagonalesVisitablesSec.contains(12-i5) && diagonalesVisitablesPrin.contains(5+i5)){
                                                    columnasVisitables.remove((Integer)i5);
                                                    diagonalesVisitablesSec.remove((Integer)(12-i5));
                                                    diagonalesVisitablesPrin.remove((Integer)(5+i5));
                                                    setTableroFila(5, i5,"000");
                                                    for(int i6=0; i6<8; i6++){
                                                        if(columnasVisitables.contains(i6) && diagonalesVisitablesSec.contains(13-i6) && diagonalesVisitablesPrin.contains(6+i6)){
                                                            columnasVisitables.remove((Integer)i6);
                                                            diagonalesVisitablesSec.remove((Integer)(13-i6));
                                                            diagonalesVisitablesPrin.remove((Integer)(6+i6));
                                                            setTableroFila(6, i6,"000");
                                                            for(int i7=0; i7<8; i7++){
                                                                if(columnasVisitables.contains(i7) && diagonalesVisitablesSec.contains(14-i7) && diagonalesVisitablesPrin.contains(7+i7)){
                                                                    columnasVisitables.remove((Integer)i7);
                                                                    diagonalesVisitablesSec.remove((Integer)(14-i7));
                                                                    diagonalesVisitablesPrin.remove((Integer)(7+i7));
                                                                    setTableroFila(7, i7,"000");

                                                                    aux++;
                                                                    //System.out.println(aux);
                                                                    if(tableroSinAtacar()){
                                                                        contador++;
                                                                        System.out.println();
                                                                        System.out.println(contador);
                                                                        imprimir();
                                                                    }

                                                                    columnasVisitables.add(i7);
                                                                    diagonalesVisitablesSec.add(14-i7);
                                                                    diagonalesVisitablesPrin.add(7+i7);
                                                                    setTableroFila(7, i7,"___");
                                                                }
                                                            }
                                                            columnasVisitables.add(i6);
                                                            diagonalesVisitablesSec.add(13-i6);
                                                            diagonalesVisitablesPrin.add(6+i6);
                                                            setTableroFila(6, i6,"___");
                                                        }
                                                    }
                                                    columnasVisitables.add(i5);
                                                    diagonalesVisitablesSec.add(12-i5);
                                                    diagonalesVisitablesPrin.add(5+i5);
                                                    setTableroFila(5, i5,"___");
                                                }
                                            }
                                            columnasVisitables.add(i4);
                                            diagonalesVisitablesSec.add(11-i4);
                                            diagonalesVisitablesPrin.add(4+i4);
                                            setTableroFila(4, i4,"___");
                                        }
                                    }
                                    columnasVisitables.add(i3);
                                    diagonalesVisitablesSec.add(10-i3);
                                    diagonalesVisitablesPrin.add(3+i3);
                                    setTableroFila(3, i3,"___");
                                }
                            }
                            columnasVisitables.add(i2);
                            diagonalesVisitablesSec.add(9-i2);
                            diagonalesVisitablesPrin.add(2+i2);
                            setTableroFila(2, i2,"___");
                        }
                    }
                    columnasVisitables.add(i1);
                    diagonalesVisitablesSec.add(8-i1);
                    diagonalesVisitablesPrin.add(1+i1);
                    setTableroFila(1, i1,"___");
                }
            }
            columnasVisitables.add(i0);
            diagonalesVisitablesSec.add(7-i0);
            diagonalesVisitablesPrin.add(i0);
            setTableroFila(0, i0,"___");

        }
    }

    public void probar4(){
        bucle(7, 0, columnasVisitables, diagonalesVisitablesPrin, diagonalesVisitablesSec);
    }
    public void bucle(int ciclos, int num, ArrayList<Integer> col, ArrayList<Integer> diagPrin, ArrayList<Integer> diagSec){

        if(num==ciclos){
            if(tableroSinAtacar()){
                System.out.println();
                imprimir();
            }
        }
        else{
            for(int i=0; i<8; i++){
                if(col.contains(i) && diagSec.contains(7+num-i) && diagPrin.contains(num+i)){
                    col.remove((Integer)i);
                    diagSec.remove((Integer)(num-8));
                    diagPrin.remove((Integer)(1+i));
                    setTableroFila((7-num), i,"000");

                    bucle(ciclos, num+1, col, diagPrin, diagSec);

                    col.add(i);
                    diagSec.add(7+num-i);
                    diagPrin.add(num+i);
                    setTableroFila((7-num), i,"___");
                }
            }
        }
    }
}
