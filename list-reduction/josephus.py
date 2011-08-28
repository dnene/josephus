"""
$ python josephus.py
26
list reduction
17.4075951576
17.7173249722
17.6679799557
17.723637104
17.6835641861
17.7069940567
17.6745419502
17.7110111713
17.6747300625
17.7083261013
"""

def countoffSoldiers(n,kth):
   soldiers = list(range(1,n+1))

   #k = kth-1; survivedLastRound = n # standard Josephus problem
   k = 0; survivedLastRound = n

   while survivedLastRound > 1:
      survived = 0

      for i in range(survivedLastRound):
         if i != k: 
            soldiers[survived] = soldiers[i]
            survived += 1
         else:
            k += kth

      k -= survivedLastRound # wrap around
      survivedLastRound = survived

   return soldiers[0] 


#-----------------------------------------------------------------------------

import time
def run_iterations(iterations, times) :
    print 'list reduction' 
    for t in range(times) :
        start = time.time()
        for i in range(iterations) :
            countoffSoldiers(40,3)
        end = time.time()
        print ((end - start) * 1000000 / iterations)

print countoffSoldiers(40,3)
ITER = 1000000
run_iterations(ITER,10)

