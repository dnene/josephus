import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Chain {

   private static int[] soldiers(int n) 
   {
      int[] a = new int[n];
      for (int i = 0 ; i < a.length ; i++) a[i] = i+1;
      return a;
   }

    public static int countoffSoldiersRecursion(int n, int kth) 
    {
        LinkedList<Integer> soldiers = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) soldiers.add( new Integer(i+1) );
        
        return countoff( soldiers.listIterator(0), soldiers, kth, kth ); 
    }


    private static int countoff(
        ListIterator<Integer> ring, LinkedList<Integer> reset, int kth, int k ) 
    { 
        if (ring.hasNext())
        {
            ring.next();

            if (k != kth) {
                return countoff(ring, reset, kth, ++k); 

            } else {
                ring.remove();
                return countoff(ring, reset, kth, 1); 
            }

        } else {

            if (ring.nextIndex() > 1)

            // the only way to reset an iterator is to create a new one from the list
                return countoff(reset.listIterator(0), reset, kth, k);

            else
                return ring.previous().intValue();
        }
    }

    public static void runIterationsRecursive(int iterations, int times) {
        for(int t = 0 ; t < times; t++) {
            System.gc();
            long start = System.nanoTime();
            for(int i = 0; i < iterations ; i++) {
                countoffSoldiersRecursion(40,3);
            }
            long end = System.nanoTime();
            System.out.println(((end - start) * 1.0)/ (iterations * 1000 ));
        }
    }

    public static void main(String[] args) {
        int ITER = 1000000;
        System.out.println("Element Recursion");
        System.out.println(countoffSoldiersRecursion(40,3) );
        runIterationsRecursive(ITER,10);
    }
}
