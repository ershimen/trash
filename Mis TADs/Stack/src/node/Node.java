package node;

public class Node<T>{
    private T element;
    private Node<T> next;

    public Node(T elem){
        this.element=elem;
    }

    public Node(T elem, Node<T> next){
        this.element=elem;
        this.next=next;
    }

    public Node <T> next (){
        return this.next;
    }

    public T getElem(){
        return this.element;
    }

    public void setNext (Node<T> next){
        this.next=next;
    }

    public void setElem(T elem){
        this.element = elem;
    }
}
