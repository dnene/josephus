public class Chain
{
    private Person first = null;

    public Chain(int size)
    {
        Person last = null;
        Person current = null;
        for (int i = 0 ; i < size ; i++)
        {
            current = new Person(i);
            if (first == null) first = current;
            if (last != null)
            {
                last.setNext(current);
                current.setPrev(last);
            }
            last = current;
        }
        first.setPrev(last);
        last.setNext(first);
    }

    public Person kill(int nth)
    {
        Person current = first;
        int shout = 1;
        while(current.getNext() != current)
        {
            shout = current.shout(shout, nth);
            current = current.getNext();
        }
        first = current;

        return current;
    }

    public Person getFirst()
    {
        return first;
    }

    public static void runIterations(int iterations, int times) {
        for (int t = 0 ; t < times ; t++) {
            System.gc();
            long start = System.nanoTime();
            for (int i = 0 ; i < iterations ; i++) {
                Chain chain = new Chain(40);
                chain.kill(3);
            }
            long end = System.nanoTime();
            System.out.println(((end - start) * 1.0 / (iterations * 1000)));
        }
    }

    public static void main(String[] args)
    {
        Chain chain = new Chain(40);
        chain.kill(3);
        System.out.println(chain.getFirst().getCount());
        int ITER = 1000000;
        runIterations(ITER,10);
    }
}
