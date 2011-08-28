"""
$ python josephus.py
26
object oriented
105.884628057
105.946704865
106.000247002
105.890681982
105.9042871
105.917474985
105.907362938
105.803264856
105.783190012
105.89677
"""

kth = 3; k = 1

class Soldier(object):

   def __init__(self,previousSoldier,number):

      self.number = number;

      if previousSoldier:
         self.next = previousSoldier.next
         previousSoldier.next = self
         self.previous = previousSoldier
         self.next.previous = self
      else:
         self.next = self
         self.previous = self


   def countoff(self):
      global k
      if k == kth:
         self.previous.next = self.next;
         self.next.previous = self.previous
         k = 1
      else:
         k += 1
      return self.next 


def countoffSoldiers(n,nth):
   global kth,k
   #kth = nth; k = 1 # standard Josephus problem
   kth = nth; k = nth

   soldier = None
   for i in (range(1,n+1)):
      soldier = Soldier(soldier,i)

   soldier = soldier.next

   while soldier.next != soldier:
      soldier = soldier.countoff()

   return soldier.number


#-----------------------------------------------------------------------------

import time
def run_iterations(iterations, times) :
    print "object oriented" 
    for t in range(times) :
        start = time.time()
        for i in range(iterations) :
            countoffSoldiers(40,3)
        end = time.time()
        print ((end - start) * 1000000 / iterations)

print countoffSoldiers(40,3)
ITER = 1000000
run_iterations(ITER,10)

