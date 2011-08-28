@Typed package gpptest

import sun.security.util.Debug

class ChainGpp2
{
    static class Person
    {
        final int count
        Person prev, next

        Person(int count)
        {
            this.count = count+1
        }

        void remove() {
            prev.next = next
            next.prev = prev
        }
    }

    Person first

    ChainGpp2(int size)
    {
        def last = (first = [0])
        for (int i = 1 ; i < size ; i++)
        {
            last = ((last.next = [i]).prev = last).next
        }
        first.prev = last
        last.next = first
    }

    void kill(int nth)
    {
        def shout = nth
        def current = first
        for(; current.next !== current; current = current.next)
        {
            if (shout++ == nth) {
                current.remove()
                shout = 1
            }
        }
        first = current
    }
}

def runIterations(int iterations, int times) {
    for (int t = 0 ; t < times ; t++) {
        System.gc()
        def start = System.nanoTime()
        for (int i = 0 ; i < iterations ; i++) {
            ChainGpp2 chain = [40]
            chain.kill(3)
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
