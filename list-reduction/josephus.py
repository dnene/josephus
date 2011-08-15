def shout(chain,nth,counter):
    while len(chain)>1:
        chain, counter = [x for n,x in enumerate(chain) if (counter + n) % nth != 0], (n + counter + 1) % nth
    return chain[0]

import time
ITER = 100000
print shout(range(1,41),3,0)
for i in range(ITER):
    shout(range(1,41),3,0)
start = time.time()
for i in range(ITER):
    shout(range(1,41),3,0)
end = time.time()
print 'Time per iteration = %s microseconds (list reduction)' % ((end - start) * 1000000 / ITER)
