package stack;

import node.Node;

public class SimpleStack<T> implements StackInterface<T>{

    //El top apunta al primer elemento. Los elementos se añaden por el principio.
    private Node<T> top;
    private int nElems;

    //Constructor
    public SimpleStack(){
        top=null;
        nElems=0;
    }

    //Añade un elemento al princpio de la cola
    public void push(T element){
        Node<T> newNode=new Node<T>(element);
        if(nElems==0)
            top=newNode;
        else{
            newNode.setNext(top);
            top=newNode;
        }
        nElems++;
    }

    //Elimina y devuelve el primer elemento de la cola. Si esta está vacía, devuelve null.
    public T poll(){
        if(nElems==0)
            return null;
        Node<T> aux=top;
        top=top.next();
        nElems--;
        return aux.getElem();
    }

    //Devuelve el primer elemento de la cola. Si esta está vacía, devuelve null.
    public T peek(){
        if(nElems==0)
            return null;
        return top.getElem();
    }

    //Elimina todos los elementos de la cola.
    public void clear(){
        top=null;
        nElems=0;
    }

    //Devuelve el número de elementos de la cola.
    public int size(){
        return nElems;
    }

    //Devuelve un String con los elementos de la cola (el de la derecha es el primero).
    public String toString(){
        String auxString="";
        if(nElems==0)
            return auxString;
        Node<T> aux=top;
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
