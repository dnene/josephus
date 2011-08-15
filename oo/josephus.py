class Person(object):
    def __init__(self,count):
        self.count = count + 1;
        self.prev = None

        self.next = None
    def shout(self,shout,nth):
        if (shout == 1):
            self.prev.next = self.next
            self.next.prev = self.prev
            return 2
        if shout == nth : return 1
        else: return shout + 1
    def __str__(self) :
        return str(self.count)

class Chain(object):
    def __init__(self,size):
        self.first = None
        last = None
        for i in range(size):
            current = Person(i)
            if self.first == None : self.first = current
            if last != None :
                last.next = current
                current.prev = last
            last = current
        self.first.prev = last
        last.next = self.first
    def kill(self,nth):
        current = self.first
        shout = 1 
        while current.next != current:
            shout = current.shout(shout,nth)
            current = current.next
        self.first = current
        return current

import time
chain = Chain(40)
chain.kill(3)
print chain.first
ITER = 100000
for i in range(ITER):
    chain = Chain(40)
    chain.kill(3)
start = time.time()
for i in range(ITER):
    chain = Chain(40)
    chain.kill(3)
end = time.time()
print 'Time per iteration = %s microseconds (oo)' % ((end - start) * 1000000 / ITER)

