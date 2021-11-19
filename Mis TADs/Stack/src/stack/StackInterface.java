package stack;

public interface StackInterface<T>{
    //Añade un elemento al principio de la cola
    public void push(T element);

    //Elimina y devuelve el primer elemento de la cola. Si esta está vacía, devuelve null.
    public T poll();

    //Devuelve el primer elemento de la cola. Si esta está vacía, devuelve null.
    public T peek();

    //Elimina todos los elementos de la cola.
    public void clear();

    //Devuelve el número de elementos de la cola.
    public int size();

    //Devuelve un String con los elementos de la cola (el de la derecha es el primero).
    public String toString();

}
