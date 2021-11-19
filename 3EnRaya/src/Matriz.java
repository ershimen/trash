class Matriz{
    private String[][]matriz;

    public Matriz(){
        matriz=new String[3][3];
    }
    public void rellenar(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                matriz[i][j]=".";
            }
        }
    }
    public void imprimir(){
        for(int i=0; i<3; i++){
            System.out.println(matriz[i][0] +" "+matriz[i][1] +" "+matriz[i][2]);
        }
    }
    public void imprimirDefault(){
        System.out.println("1 2 3");
        System.out.println("4 5 6");
        System.out.println("7 8 9");

    }
    public String getElem(int i, int j){
        return matriz[i][j];
    }
    public void jugar(int i, int j, String x){
        matriz[i][j]=x;
    }
    public boolean ocupado(int pos){
        int i=0,j=0;
        switch(pos){
            case 1: i=0;j=0;break;
            case 2: i=0;j=1;break;
            case 3: i=0;j=2;break;
            case 4: i=1;j=0;break;
            case 5: i=1;j=1;break;
            case 6: i=1;j=2;break;
            case 7: i=2;j=0;break;
            case 8: i=2;j=1;break;
            case 9: i=2;j=2;break;
        }
        return matriz[i][j]!=".";
    }
    public boolean comprobar(){
        if(matriz[0][0]!="." || matriz[0][1]!="." || matriz[0][2]!="."){ //fila 0
            if(matriz[0][0] == matriz[0][1] && matriz[0][0] == matriz[0][2]){
                return true;
            }
            else{
                if(matriz[1][0]!="." || matriz[1][1]!="." || matriz[1][2]!="."){ //fila 1
                    if(matriz[1][0] == matriz[1][1] && matriz[1][0] == matriz[1][2]){
                        return true;
                    }
                    else{
                        if(matriz[2][0]!="." || matriz[2][1]!="." || matriz[2][2]!="."){ //fila 2
                            if(matriz[2][0] == matriz[2][1] && matriz[2][0] == matriz[2][2]){
                                return true;
                            }
                            else{
                                if(matriz[0][0]!="." || matriz[1][0]!="." || matriz[2][0]!="."){ //columna 0
                                    if(matriz[0][0] == matriz[1][0] && matriz[0][0] == matriz[2][0]){
                                        return true;
                                    }
                                    else{
                                        if(matriz[0][1]!="." || matriz[1][1]!="." || matriz[2][1]!="."){ //columna 1
                                            if(matriz[0][1] == matriz[1][1] && matriz[0][1] == matriz[2][1]){
                                                return true;
                                            }
                                            else{
                                                if(matriz[0][2]!="." || matriz[1][2]!="." || matriz[2][2]!="."){ //columna 2
                                                    if(matriz[0][2] == matriz[1][2] && matriz[0][2] == matriz[2][2]){
                                                        return true;
                                                    }
                                                    else{
                                                        if(matriz[0][0]!="." || matriz[1][1]!="." || matriz[2][2]!="."){ //diagonal principal
                                                            if(matriz[0][0] == matriz[1][1] && matriz[0][0] == matriz[2][2]){
                                                                return true;
                                                            }
                                                            else{
                                                                if(matriz[0][2]!="." || matriz[1][1]!="." || matriz[2][0]!="."){ //diagonal secundaria
                                                                    if(matriz[0][2] == matriz[1][1] && matriz[0][2] == matriz[2][0]){
                                                                        return true;
                                                                    }
                                                                    else{
                                                                        return false;
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
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
