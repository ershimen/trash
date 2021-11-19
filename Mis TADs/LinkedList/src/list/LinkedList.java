//Autor: Cerezo Pomykol, Jan
package list;

import node.Node;

/**
 * Implementación de un TAD Lista
 * basada en una cadena simplemente enlazada
 */
public class LinkedList<E> implements ListInterface<E> {

  private Node<E> head;
  private int nElems;

  /**
   * Construye una lista vacía
   *
   * <br><B>PRE:</B> Cierto
   * <br><B>POST:</B> crea una lista vacía.
   *
   */
  public LinkedList(){
    head = null;
    nElems = 0;
  }

  /**
   * Coloca un nuevo elemento en la posición insertIndex
   *
   * <br><B>PRE:</B> insertIndex EN {0..size()}
   * <br><B>POST:</B> devuelve la lista this con element en la pos insertIndex
   * y los elementos que antes estaban en pos &gt;=insertIndex, ahora en pos+1.
   *
   * @throws IndexOutOfBoundsException
   */
  public void add(int insertIndex, E element) throws IndexOutOfBoundsException {
    if(insertIndex<0 || insertIndex>size())
      throw new IndexOutOfBoundsException();
    Node<E> newNode= new Node<E>(element);
    if(head==null){
      head=newNode;
      newNode.setNext(newNode);
    }
    else{
      Node<E> aux=head;
      for (int i=0; i<insertIndex; i++)
        aux=aux.next();
      newNode.setNext(aux.next());
      aux.setNext(newNode);
      if(insertIndex==nElems)
        head=newNode;
    }
    nElems++;
  }

  /**
   * Lectura indexada de una posición de la lista
   *
   * <br><B>PRE:</B> insertIndex EN {0..size()-1}
   * <br><B>POST:</B> devuelve una ref al elemento que está en la pos index.
   *
   * @throws IndexOutOfBoundsException
   */
  public E get(int getIndex) throws IndexOutOfBoundsException {
    if(getIndex<0 || getIndex>=size())
      throw new IndexOutOfBoundsException();
    Node<E> aux=head;
    for (int i=0; i<getIndex && aux.next()!=null; i++)
      aux=aux.next();
    return aux.next().getElem();
  }

  /**
   * No. de elementos en la lista
   *
   * <br><B>PRE:</B> cierto
   * <br><B>POST:</B> devuelve el no. de elems que hay en la lista.
   */
  public int size() {
    return nElems;
  }

  /**
   * Escritura indexada en una posición de la lista
   *
   * <br><B>PRE:</B> insertIndex EN {0..size()-1}
   * <br><B>POST:</B> coloca element en la posición insertIndex de la lista destruyendo
   * el elemento que había en esa posición.
   *
   * @throws IndexOutOfBoundsException
   */
  public void set(int insertIndex, E element) throws IndexOutOfBoundsException {
    if(insertIndex<0 || insertIndex>=size())
      throw new IndexOutOfBoundsException();
    if(nElems==0)
      head=new Node<E>(element);
    else{
      Node<E> aux=head;
      for (int i=0; i<insertIndex; i++)
        aux=aux.next();
      aux.next().setElem(element);
    }
  }

  /**
   * Posición de un elemento dentro de la lista
   *
   * <br><B>PRE:</B> Cierto
   * <br><B>POST:</B> devuelve una ref al primer elemento de la lista
   * que es igual a search (equals), o -1 si no existe ningún elemento igual a search.
   *
   */
  public int indexOf(E search) {
    if(nElems==0)
      return -1;
    boolean encontrado=false;
    Node<E> aux=head.next();
    int i=0;
    while(i<nElems && !encontrado){
      encontrado=search.equals(aux.getElem());
      aux=aux.next();
      i++;
    }
    if(!encontrado)
      return -1;
    return i-1;
  }

  /**
   * Extracción de un elemento de la lista dada su posición
   *
   * <br><B>PRE:</B> removalIndex EN {0..size()-1}
   * <br><B>POST:</B> extrae el elemento que está en la pos removalIndex.
   *
   * @throws IndexOutOfBoundsException
   */
  public void removeElementAt(int removalIndex) throws IndexOutOfBoundsException {
    if(removalIndex<0 || removalIndex>=size())
      throw new IndexOutOfBoundsException();
    if(head!=null){
      if(nElems==1)
        head=null;
      Node<E> aux=head;
      for(int i=0; i<removalIndex; i++)
        aux=aux.next();
      aux.setNext(aux.next().next());
      if(removalIndex==nElems-1)
        head=aux;
      nElems--;
    }
  }

  /**
   * Extracción de un elemento de la lista dado un elemento igual (equals)
   *
   * <br><B>PRE:</B> cierto
   * <br><B>POST:</B> extrae el primer elemento que sea igual a element (equals) y devuelve cierto,
   * si existe. Y si no existe, devuelve falso.
   *
   */
  public boolean remove(E element) {
    int aux=indexOf(element);
    if(indexOf(element)!=-1){
      removeElementAt(aux);
      return true;
    }
    return false;
  }

  /**
   * Devuelve un string con los elementos de la lista
   *
   * <br><B>PRE:</B> Cierto
   * <br><B>POST:</B> devuelve un string con los elementos de la lista separados
   * por comas.
   */
  public String toString() {
    if(nElems==0)
        return "";
    String ret="[";
    Node<E> aux=head;
    for(int i=0; i<nElems; i++){
      if(i==nElems-1){
        aux=aux.next();
        ret=ret+" "+aux.getElem()+" ";
      }
      else{
        aux=aux.next();
        ret=ret+" "+aux.getElem()+",";
      }
    }
    return ret+"]";
  }

  /**
   * <br><B>PRE:</B> Cierto
   * <br><B>POST:</B> Devuelve una referencia al último elemento de la lista ("head")
   * Método auxiliar de "equals(Object obj)" para obtener "head" del parámetro.
   * Si existiera un método get() (distinto al ya implementado) que devolviera un nodo
   * en vez de el elemento de ese nodo, no sería necesario este método.
   *
   */
  public Node<E> getHead(){
    return head;
  }

  /**
   * Igualdad de listas
   *
   * <br><B>PRE:</B> Cierto
   * <br><B>POST:</B> indica si obj es igual a this. Dos listas son iguales
   * si las secuencias de objetos almacenados en ellas son iguales. Cada par de objetos
   * son comparados con el método equals de la clase a la que pertenecen estos objetos.
   *
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object obj) {
    boolean ret=true;
    Node<E> aux=head;
    // Estaría bien tener un método get(int getIndex) que devolviera el nodo, en vez del elemento,
    // como hace get(int getIndex). El método implementado get(int getIndex) debería llamarse
    // getElemAt o algo así. Si existiera el método get (que devuelve un nodo) no haría falta
    // utilizar el método getHead().
    Node<E> aux1=((LinkedList<E>) obj).getHead();
    for(int i=0; i<nElems && ret; i++){
      aux=aux.next();
      aux1=aux1.next();
      ret=ret&&aux.getElem().equals(aux1.getElem());
    }
    return ret;
  }
}
