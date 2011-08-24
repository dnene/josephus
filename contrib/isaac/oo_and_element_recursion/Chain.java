public class Chain {

    public static void runIterations(int iterations, int times) {
        for(int t = 0 ; t < times; t++) {
            System.gc();
            long start = System.nanoTime();
            for(int i = 0; i < iterations ; i++) {
                Soldier.countoffSoldiers(40,3);
            }
            long end = System.nanoTime();
            System.out.println(((end - start) * 1.0)/ (iterations * 1000 ));
        }
    }

    public static void main(String[] args) {
        int ITER = 1000000;
        System.out.println("Element Recursion Object Oriented");
        System.out.println(Soldier.countoffSoldiers(40,3) );
        runIterations(ITER,10);
    }
}
