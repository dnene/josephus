@Typed package gpptest

class ChainGpp2
{
    static class Person
    {
        int count
        Person prev, next

        Person(int count)
        {
            this.count = count + 1
        }

        int shout(int shout, int nth)
        {
            if (shout == 1) {
                prev.next = next
                next.prev = prev
                return 2;
            }
            else if (shout == nth) {
                return 1;
            } else {
                return shout + 1;
            }
        }
    }

    Person first

    ChainGpp2(int size)
    {
        Person last
        for (int i = 0 ; i < size ; i++)
        {
            Person current = [i]
            if (!first) first = current;
            if (last)
            {
                last.next = current
                current.prev = last
            }
            last = current
        }
        first.prev = last
        last.next = first
    }

    void kill(int nth)
    {
        def current = first
        def shout = 1
        while(current.next !== current)
        {
            shout = current.shout(shout, nth)
            current = current.next
        }
        first = current
    }
}

def runIterations(int iterations, int times) {
    for (int t = 0 ; t < times ; t++) {
        System.gc();
        long start = System.nanoTime();
        for (int i = 0 ; i < iterations ; i++) {
            ChainGpp2 chain = [40]
            chain.kill(3);
        }
        long end = System.nanoTime();
        System.out.println(((end - start) * 1.0 / (iterations * 1000)));
    }
}


ChainGpp2 chain = [40]
chain.kill(3)
println(chain.first.count)
def ITER = 1000000
runIterations(ITER,10)
