public class Tablero{
    private Casilla[][] tablero;
    private boolean[] posValidas=new boolean[8];

    public Tablero(){
        tablero=new Casilla[8][8];
        Posicion aux;

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                aux=new Posicion(i,j);
                tablero[i][j]=new Casilla(aux);
            }
        }

        for(int i=0; i<posValidas.length; i++){
            posValidas[i]=true;
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

    public void probar(){

        int contador=0;     //numero de soluciones
        int aux=0;          //numero de iteraciones
        boolean bol;

        for(int i0=0; i0<8; i0++){
            if(posValidas[i0]==true){
                posValidas[i0]=false;
                setTableroFila(0,i0,"000");
                for(int i1=0; i1<8; i1++){
                    if(posValidas[i1]==true){
                        posValidas[i1]=false;
                        setTableroFila(1,i1,"000");
                        for(int i2=0; i2<8; i2++){
                            if(posValidas[i2]==true){
                                posValidas[i2]=false;
                                setTableroFila(2,i2,"000");
                                for(int i3=0; i3<8; i3++){
                                    if(posValidas[i3]==true){
                                        posValidas[i3]=false;
                                        setTableroFila(3,i3,"000");
                                        for(int i4=0; i4<8; i4++){
                                            if(posValidas[i4]==true){
                                                posValidas[i4]=false;
                                                setTableroFila(4,i4,"000");
                                                for(int i5=0; i5<8; i5++){
                                                    if(posValidas[i5]==true){
                                                        posValidas[i5]=false;
                                                        setTableroFila(5,i5,"000");
                                                        for(int i6=0; i6<8; i6++){
                                                            if(posValidas[i6]==true){
                                                                posValidas[i6]=false;
                                                                setTableroFila(6,i6,"000");
                                                                for(int i7=0; i7<8; i7++){
                                                                    if(posValidas[i7]==true){
                                                                        posValidas[i7]=false;
                                                                        setTableroFila(7,i7,"000");

                                                                        aux++;
                                                                        //System.out.println(aux);
                                                                        if(tableroSinAtacar()){
                                                                            contador++;
                                                                            System.out.println();
                                                                            System.out.println(contador);
                                                                            imprimir();
                                                                        }

                                                                        posValidas[i7]=true;
                                                                        setTableroFila(7,i7,"___");
                                                                    }
                                                                }
                                                                posValidas[i6]=true;
                                                                setTableroFila(6,i6,"___");
                                                            }
                                                        }
                                                        posValidas[i5]=true;
                                                        setTableroFila(5,i5,"___");
                                                    }
                                                }
                                                posValidas[i4]=true;
                                                setTableroFila(4,i4,"___");
                                            }
                                        }
                                        posValidas[i3]=true;
                                        setTableroFila(3,i3,"___");
                                    }
                                }
                                posValidas[i2]=true;
                                setTableroFila(2,i2,"___");
                            }
                        }
                        posValidas[i1]=true;
                        setTableroFila(1,i1,"___");
                    }
                }
                posValidas[i0]=true;
                setTableroFila(0,i0,"___");
            }
        }
    }
}
