package innlevering1;

import java.util.ArrayList; // To be used in implementation  of IntegerStackGeneric
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class IntegerStack implements Iterable<Integer>{
    
    public static enum Type {GENERIC,PRIMITIVE};

    /* Abstract methods */



    /**
     * Adds a new integer to the stack. 
     *
     * EXAMPLE:
     * -------------
     * stack.push(1);
     * stack.push(2);
     * stack.push(3);
     * System.out.println(stack);
     *
     * OUTPUT:
     * ---------------
     * [3, 2, 1]
     */
    public abstract void push(int x);

    /**
     * Removes the most recent integer on the
     * stack, and returns it to the caller.
     *
     * EXAMPLE:
     * -------------
     * stack.push(1);
     * stack.push(2);
     * stack.push(3);
     * System.out.println(stack);
     * System.out.println(stack.pop());
     * System.out.println(stack);
     *
     * OUTPUT:
     * ---------------
     * [3, 2, 1]
     * 3
     * [2, 1]
     *
     * @throws NoSuchElementException if the stack is empty
     */
    public abstract int pop() throws NoSuchElementException;

    /**
     * Returns the number of elements on the stack.
     *
     * EXAMPLE:
     * ----------------------
     *  for(int i = 0; i < 10; i++)
     *      stack.push(i);
     *  System.out.println(stack.size())
     *
     *  OUTPUT:
     *  -------------------
     *  10
     */
    public abstract int size();

    /**
     * Returns true if the integer x is on the stack, 
     * otherwise it returns false.
     *
     * EXAMPLE:
     * -------------------
     *  System.out.println(stack.contains(0));
     *  stack.push(0);
     *  System.out.println(stack.contains(0));
     *
     *  OUTPUT:
     *  -----------------------
     *  false
     *  true
     */
    public abstract boolean contains(int x);

    /**
     * Reverses the order of the stack
     *
     * EXAMPLE:
     * ---------------
     *  stack.push(1);
     *  stack.push(2);
     *
     *  System.out.println(stack);
     *  stack.reverse();
     *  System.out.println(stack);
     *
     *  OUTPUT:
     *  -------------------
     *  [2, 1]
     *  [1, 2]
     */
    public abstract void reverse();

    /**
     * Returns an iterator that allows the caller
     * to iterate through the elements on the stack. 
     *
     * The iterator visits the more recent elements
     * before the less recent element.
     *
     * EXAMPLE:
     * -----------------
     * stack.push(1);
     * stack.push(2);
     *
     * for(Integer i: stack)
     *      System.out.print(i);
     *
     * OUTPUT:
     * ------------------
     *  21
     */
    public abstract Iterator<Integer> iterator();




    /*
     * Some useful methods
     */
    
    /* Dependso on push() */
    public void pushAll(Iterable<Integer> iterable){
        for(Integer i: iterable)
            push(i);
    }

    /* Depends on size() */
    public boolean isEmpty(){
        return !(size() > 0);
    }


    /* Depends on iterator() */
    public String toString(){
        ArrayList<Integer> elements = new ArrayList<>();
        for(Integer i: this)
            elements.add(i);
        return elements.toString();
    } 
    
    /* Factory method: Depends on constructors */
    public static IntegerStack create(Type type){
        IntegerStack stack = null;
        switch (type) {
            case GENERIC: stack = new IntegerStackGeneric(); break;
            case PRIMITIVE: stack = new IntegerStackPrimitive(); break;
        } 
        return stack;
    }



    /* Main method: Runs  some simple tests */
    public static void main(String[] args){
        IntegerStack generic = create(Type.GENERIC);
        IntegerStack primitive = create(Type.PRIMITIVE);
        
        test(generic);
        test(primitive);
    }   

    private static void test(IntegerStack stack){
        System.out.println(stack);
        stack.push(1);
        System.out.println(stack);
        try {
            System.out.printf("POP -> %d%n",stack.pop());
        } catch (NoSuchElementException e){
            System.out.printf("POP -> %s%n",e);
        }
        System.out.println(stack);
    }

}


class IntegerStackGeneric extends IntegerStack {
    
    ArrayList<Integer> stack;
   
    IntegerStackGeneric(){
       stack = new ArrayList<>();
    }

    public void push(int x){
        return;
    }

    public int pop() throws NoSuchElementException {
        throw new NoSuchElementException("IntegerStackGeneric: pop()");
    }
    
    public int size(){
        return -1;
    }

    public boolean contains(int x){
        return false;
    }
    
    public void reverse(){
        return;
    }

    public Iterator<Integer> iterator(){
        return new ArrayList<Integer>().iterator();
    }
}

class IntegerStackPrimitive extends IntegerStack {
    
    int[] stack;
   
    IntegerStackPrimitive(){
       stack = null;
    }

    public void push(int x){
        return;
    }

    public int pop() throws NoSuchElementException {
        throw new NoSuchElementException("IntegerStackGeneric: pop()");
    }

    public int size(){
        return -1;
    }

    public boolean contains(int x){
        return false;
    }
    
    public void reverse(){
        return;
    }

    public Iterator<Integer> iterator(){
        return new Itr();
    }

    private class Itr implements Iterator<Integer> {
        private Itr(){
            
        }
        public boolean hasNext(){
            return false;
        }
        public Integer next(){
            return null;
        }
        public void remove(){
            throw new UnsupportedOperationException("remove");
        }
    }
}
