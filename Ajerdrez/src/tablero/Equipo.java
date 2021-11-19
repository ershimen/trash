package tablero;

public enum Color{BANCO, NEGRO}

public class Equipo{

    private Color color;

    public Equipo(Color color){
        this.color=color;
    }
    public Color getEquipo(){
        return color;
    }

}
