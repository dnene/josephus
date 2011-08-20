from collections import deque
def find(chainlength = 40, kill = 3):
    return findlast(deque(range(1,chainlength+1)),3,1)

def findlast(chain,nth,counter) :
    if len(chain) == 1 : 
        return chain[0]
    elif counter == 1 : 
        #print chain.popleft(), " dies"
        chain.popleft()
        return findlast(chain,nth,counter+1)
    else : 
        head = chain.popleft()
        #print head, " survives"
        chain.append(head)
        return findlast(chain,nth,1 if counter == nth else counter + 1)

import time
def run_iterations(iterations, times) :
    print '(element recursive)' 
    for t in range(times) :
        start = time.time()
        for i in range(iterations) :
            find()
        end = time.time()
        print ((end - start) * 1000000 / iterations)

print find()
ITER = 1000000
run_iterations(ITER,10)
