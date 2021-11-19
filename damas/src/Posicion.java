public class Posicion{
    private int i,j;

    public Posicion(int i, int j){
        this.i=i;
        this.j=j;
    }
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }
    public void changePos(int i, int j){
        this.i=i;
        this.j=j;
    }
}
