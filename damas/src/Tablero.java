public class Tablero{
    private Ficha[][] matrizFiguras=new Ficha[8][8];

    public Tablero(){
        int contador=0;
        String nameAux;
        while(contador<4){
            nameAux="N"+(contador+1);
            matrizFiguras[0][1+2*contador]=new Ficha(0,1+2*contador,  nameAux);
            contador++;
        }
        contador=0;
        while(contador<4){
            nameAux="N"+(contador+5);
            matrizFiguras[1][2*contador]=new Ficha(1,2*contador,  nameAux);
            contador++;
        }

        contador=0;
        while(contador<4){
            nameAux="B"+(contador+1);
            matrizFiguras[7][2*contador]=new Ficha(7,2*contador,  nameAux);
            contador++;
        }
        contador=0;
        while(contador<4){
            nameAux="B"+(contador+5);
            matrizFiguras[6][1+2*contador]=new Ficha(6,1+2*contador,  nameAux);
            contador++;
        }
    }
    public void imprimir(){
        int aux=0;
        String auxString="";
        System.out.println("    A   B   C   D   E   F   G   H");
        for(int i=0; i<8; i++){
            aux=8-i;
            auxString=aux+" |";
            System.out.println("  _________________________________");
            for(int j=0; j<8; j++){
                if(matrizFiguras[i][j]!=null){
                    auxString=auxString+matrizFiguras[i][j].getName()+" |";
                }
                else{
                    auxString=auxString+"   |";
                }
            }
            System.out.println(auxString+""+aux);
        }
        System.out.println("  _________________________________");
        System.out.println("    A   B   C   D   E   F   G   H");
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println();
    }
    public void mover(Posicion origen, Posicion destino){
        matrizFiguras[destino.getI()][destino.getJ()]=matrizFiguras[origen.getI()][origen.getJ()];
        matrizFiguras[origen.getI()][origen.getJ()]=null;
    }
}
