"""
$ python josephus.py
26
element recursion
50.3187770844
50.3241479397
50.3240659237
50.3192489147
50.3268041611
50.3255558014
50.2993578911
50.2927269936
50.2895271778
50.2902379036
"""

from collections import deque

def countoff(soldiers, kth, k):
   i = soldiers.popleft()

   if len(soldiers):
      if k != kth:
         k += 1
         soldiers.append(i)
      else:
         k = 1

      return countoff(soldiers, kth, k)

   else:
      return i


def countoffSoldiers(n, kth):
   soldiers = deque( range(1,n+1) )

   #return countoff(soldiers, kth, 1) # standard Josephus problem
   return countoff(soldiers, kth, kth)


#-----------------------------------------------------------------------------

import time
def run_iterations(iterations, times) :
    print "element recursion" 
    for t in range(times) :
        start = time.time()
        for i in range(iterations) :
            countoffSoldiers(40,3)
        end = time.time()
        print ((end - start) * 1000000 / iterations)

print countoffSoldiers(40,3)
ITER = 1000000
run_iterations(ITER,10)

