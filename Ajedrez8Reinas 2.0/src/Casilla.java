public class Casilla{

    private Posicion posicion;
    private String figura;

    public Casilla(Posicion posicion){
        figura="___";
        this.posicion=posicion;
    }
    public String getFigura(){
        return figura;
    }
    public Posicion getPosicion(){
        return posicion;
    }
    public void setFigura(String figura){
        this.figura=figura;
    }
}
