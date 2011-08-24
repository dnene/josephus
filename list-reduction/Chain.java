public class Chain {

   public static int countoffSoldiers(int n, int kth) 
   {
      int[] soldiers = new int[n];
      for (int i = 0 ; i < n ; i++) soldiers[i] = i+1;

      //int k = kth-1, survivedLastRound = n;   // standard Josephus problem, 0th indexing
      int k = 0, survivedLastRound = n;

      while (survivedLastRound > 1) {
         int survived = 0;

         for (int i = 0 ; i < survivedLastRound; i++)
            if (i != k) {
               soldiers[survived++] = soldiers[i];

            } else {
               k += kth;
            }

         k -= survivedLastRound; // wrap around
         survivedLastRound = survived;
      }

      return soldiers[0];
   }


//-----------------------------------------------------------------------------

    public static void runIterations(int iterations, int times) {
        for(int t = 0 ; t < times; t++) {
            System.gc();
            long start = System.nanoTime();
            for(int i = 0; i < iterations ; i++) {
                countoffSoldiers(40,3);
            }
            long end = System.nanoTime();
            System.out.println(((end - start) * 1.0)/ (iterations * 1000 ));
        }
    }

    public static void main(String[] args) {
        int ITER = 1000000;
        System.out.println("List Reduction Array");
        System.out.println(countoffSoldiers(40,3) );
        runIterations(ITER,10);
    }
}

