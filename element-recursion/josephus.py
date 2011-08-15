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

print find()
import time
ITER = 100000
start = time.time()
for i in range(ITER):
    find()
end = time.time()
print 'Time per iteration = %s microseconds (element recursive)' % ((end - start) * 1000000 / ITER)
