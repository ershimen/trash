public class Personaje{
    private String estado;
    private String personaje;

    public Personaje(String personaje, String estado){
        this.personaje=personaje;
        this.estado=estado;
    }
    public String toString(){
        String aux="Estado incorrecto";
        switch(estado){
            case "a": aux="    "+personaje+": alive"; break;
            case "d": aux="    "+personaje+": dead"; break;
            case "w": aux="    "+personaje+": white walker"; break;
            case "-": aux="    "+personaje+": ---------"; break;
            default : aux="    "+personaje+": estado incorrecto"; break;
        }
        return aux;
    }
    public String getEstado(){
        return estado;
    }
    public String getNombre(){
        return personaje;
    }
}
