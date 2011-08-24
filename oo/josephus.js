/*
$ /usr/local/src/v8/d8 josephus.js
26
object oriented
2.557
2.572
2.577
2.578
2.578
2.578
2.578
2.572
2.572
2.571
*/

function Soldier(previousSoldier,number){
   this.number = number

   if (previousSoldier != null){

      this.next = previousSoldier.next
      previousSoldier.next = this

      this.previous = previousSoldier
      this.next.previous = this

   } else {
      this.next = this
      this.previous = this
   }
}


Soldier.prototype.countoff = function(shout){
   if (shout.wasKth()) {

      this.previous.next = this.next
      this.next.previous = this.previous
   } 
   return this.next 
} 


function countoffSoldiers(n,kth) {
   var soldier = null	
   for (var i = 1; i <= n; i++) soldier = new Soldier(soldier,i) 

   soldier = soldier.next // set to the first Soldier constructed

   var shout = new Shoutout(kth) 

   while (soldier.next != soldier) 
      soldier = soldier.countoff(shout)

   return soldier.number
} 



//function Shoutout(n) { this.kth = n; this.k = 1 }   // standard Josephus problem
function Shoutout(n) { this.kth = n; this.k = n }

Shoutout.prototype.wasKth = function(){
   var wasKth = this.k == this.kth
   if (wasKth) this.k = 1; else this.k++;
   return wasKth
}


//-----------------------------------------------------------------------------

function run_iterations(iterations, times) {
    for (var t = 0 ; t < times ; t++) {
        var start = new Date()
        for(var i = 0 ; i < iterations ; i++) {
            countoffSoldiers(40,3)
        }
        var end = new Date()
        print ((end.getTime() - start.getTime()) * 1000 / ITERS)
    }
}

print(countoffSoldiers(40,3))
ITERS = 1000000
print ("object oriented")
run_iterations(ITERS,10)
