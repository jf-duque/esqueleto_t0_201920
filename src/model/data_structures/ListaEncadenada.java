package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEncadenada<Item> implements Iterable<Item> {
	
	private int n;
	private Node first;
	private Node last;
	
	private class Node {
		private Item item;
		private Node next;
	}
	
	public ListaEncadenada()
	{
		first = null;
		last = null;
		n = 0;
		//assert check();
	}
	
	public boolean isEmpty() {
        return first == null;
    }
	
	public int size() {
        return n;     
    }
	
	public Item peek() throws Exception {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }
	
	public void agregar(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
        //assert check();
    }
	
	public Item eliminar() throws Exception {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
       //assert check();
        return item;
    }
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    } 
	
	private boolean check() {
        if (n < 0) {
            return false;
        }
        else if (n == 0) {
            if (first != null) return false;
            if (last  != null) return false;
        }
        else if (n == 1) {
            if (first == null || last == null) return false;
            if (first != last)                 return false;
            if (first.next != null)            return false;
        }
        else {
            if (first == null || last == null) return false;
            if (first == last)      return false;
            if (first.next == null) return false;
            if (last.next  != null) return false;

            // check internal consistency of instance variable n
            int numberOfNodes = 0;
            for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != n) return false;

            // check internal consistency of instance variable last
            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (last != lastNode) return false;
        }

        return true;
    } 

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator(); 
	}
	
	private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	
	public static void main(String[] args) {
		ListaEncadenada<String> queue = new ListaEncadenada<String>();
     //   while (!StdIn.isEmpty()) {
       //     String item = StdIn.readString();
           // if (!item.equals("-"))
        //        queue.enqueue(item);
      //      else if (!queue.isEmpty())
       //         StdOut.print(queue.dequeue() + " ");
        //}
      //  StdOut.println("(" + queue.size() + " left on queue)");
    }
}

