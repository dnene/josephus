import groovypp.concurrent.FList

@Typed(fastArrays = true)
class Chain2 {

    private static int[] soldiers(int n) {
        def a = new int[n]
        for (int i = 0; i < a.length; i++)
            a[i] = i + 1;
        a
    }

    static int countoffSoldiersReduction(int n, int kth) {
        def s = soldiers(n)
        def k = 0, count = n;

        while (count > 1) {
            def m = 0

            for (int i = 0; i < count; i++)
                if (i != k) {
                    s[m++] = s[i]
                } else {
                    k += kth
                }

            k -= count

            count = m
        }

        s[0]
    }

    static int countoffSoldiersRecursion(int n, int kth) {
        LinkedList<Integer> soldiers = []
        for ( int i = 0; i < n; i++)
            soldiers.add(i + 1)

        countoff(soldiers.listIterator(0), soldiers, kth, kth)
    }

    static int countoff(ListIterator<Integer> ring, LinkedList<Integer> reset, int kth, int k) {
        if (ring.hasNext()) {
            ring.next()

            if (k == kth) {
                ring.remove();
                k = 0
            }
            countoff(ring, reset, kth, k+1)


        } else {
            if (ring.nextIndex() > 1)

            // the only way to reset an iterator is to create a new one from the list
                countoff(reset.listIterator(0), reset, kth, k)

            else
                ring.previous()
        }
    }

    static void runIterationsReduction(int iterations, int times) {
        for (int t = 0; t < times; t++) {
            System.gc();
            def start = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                countoffSoldiersReduction(40, 3);
            }
            def end = System.nanoTime();
            System.out.println(((end - start) * 1.0) / (iterations * 1000));
        }
    }

    static void runIterationsRecursive(int iterations, int times) {
        for (int t = 0; t < times; t++) {
            System.gc();
            def start = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                countoffSoldiersRecursion(40, 3);
            }
            def end = System.nanoTime();
            System.out.println(((end - start) * 1.0) / (iterations * 1000));
        }
    }


    static void main(String[] args) {
        def ITER = 1000000;
        println("List Reduction");
        println(countoffSoldiersReduction(40, 3));
        runIterationsReduction(ITER, 10);
        println("Element Recursion");
        println(countoffSoldiersRecursion(40, 3));
        runIterationsRecursive(ITER, 10);
    }
}