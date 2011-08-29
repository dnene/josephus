"""
$ python josephus.py
26
element recursion
43.7426130772
43.728605032
43.7135281563
43.7130749226
43.7156639099
43.7254951
43.7201170921
43.6938109398
43.693862915
43.6937048435
"""

from collections import deque

def countoff(soldiers, kth, k):
   i = soldiers.popleft()

   if soldiers:
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

