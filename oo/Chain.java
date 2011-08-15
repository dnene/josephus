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
    public static void main(String[] args)
    {
        Chain chain = new Chain(40);
        chain.kill(3);
        System.out.println(chain.getFirst().getCount());

        for (int i = 0 ; i < 100000 ; i++)
        {
            chain = new Chain(40);
            chain.kill(3);
        }
        int ITER = 1000000;
        long start = System.nanoTime();
        for (int i = 0 ; i < ITER ; i++)
        {
            chain = new Chain(40);
            chain.kill(3);
        }
        long end = System.nanoTime();
        System.out.println("Time per iteration = " + ((end - start) / (ITER )) + " nanoseconds.");
    }
}
