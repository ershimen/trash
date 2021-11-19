class Juego{
    public static void main(String[] args){
        String jugador1="Jugador1";
        String jugador2="Jugador2";

        Partida partida1=new Partida(jugador1, jugador2);

        partida1.jugar();

    }
}
