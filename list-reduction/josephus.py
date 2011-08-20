def shout(chain,nth,counter):
    while len(chain)>1:
        chain, counter = [x for n,x in enumerate(chain) if (counter + n) % nth != 0], (n + counter + 1) % nth
    return chain[0]

import time
def run_iterations(iterations, times) :
    print '(list reduction)' 
    for t in range(times) :
        start = time.time()
        for i in range(iterations) :
            shout(range(1,41),3,0)
        end = time.time()
        print ((end - start) * 1000000 / iterations)

print shout(range(1,41),3,0)
ITER = 1000000
run_iterations(ITER,10)
