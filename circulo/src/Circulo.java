import list.LinkedList;
import java.util.Scanner;

public class Circulo{
    public static int calcular(LinkedList<Integer> lista){
        if(lista.size()!=1){
            boolean aux=lista.size()%2!=0;
            for(int i=1; i<lista.size(); i++){
                lista.removeElementAt(i);
            }
            if(aux)
                lista.removeElementAt(0);
            calcular(lista);
        }
        return lista.get(0);
    }
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        LinkedList<Integer> lista = new LinkedList<Integer>();
        System.out.print("Num: ");
        int num=sc.nextInt();
        for(int i=0; i<num; i++)
            lista.add(lista.size(), i);

        System.out.println("Se ha creado la lista");
        if(num!=0)
            System.out.println("Resultado: "+calcular(lista));
        else
            System.out.println("Hay 0 elementos");

    }
}
