package queue;

import node.Node;

public class SimpleQueue<T> implements QueueInterface<T>{

    private Node<T> first;
    private Node<T> last;
    private int nElems;

    //Constructor que inicializa first y last a null y el nElems a 0.
    public SimpleQueue(){
        first=null;
        last=null;
        nElems=0;
    }

    //Añade un elemento al final de la cola
    public void push(T element){
        Node<T> newNode=new Node<T>(element);
        if(nElems==0){
            first=newNode;
            last=newNode;
        }
        else{
            last.setNext(newNode);
            last=newNode;
        }
        nElems++;
    }

    //Elimina y devuelve el primer elemento de la cola. Si esta está vacía, devuelve null.
    public T poll(){
        if(nElems==0)
            return null;
        Node<T> aux=first;
        first=first.next();
        nElems--;
        return aux.getElem();
    }

    //Devuelve el primer elemento de la cola. Si esta está vacía, devuelve null.
    public T peek(){
        if(nElems!=0)
            return first.getElem();
        return null;
    }

    //Elimina todos los elementos de la cola.
    public void clear(){
        first=null;
        last=null;
        nElems=0;
    }

    //Devuelve el número de elementos de la cola.
    public int size(){
        return nElems;
    }

    //Devuelve un String con los elementos de la cola.
    public String toString(){
        String auxString="";
        if(nElems==0)
            return auxString;
        Node<T> aux=first;
        for(int i=0; i<nElems; i++){
            if(nElems==1)
                auxString=""+aux.getElem();
            else
            if(i==0)
                auxString=""+aux.getElem();
            else
                auxString=auxString+", "+aux.getElem();
            if(i!=nElems-1)
                aux=aux.next();
        }
        return (String) auxString;
    }

}
