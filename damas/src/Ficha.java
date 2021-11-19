public class Ficha{
    private Posicion pos;
    private String name;

    public Ficha(int i, int j, String name){
        pos=new Posicion(i,j);
        this.name=name;
    }
    public void setPos(int i, int j){
        pos.changePos(i, j);
    }
    public String getName(){
        return name;
    }
}
