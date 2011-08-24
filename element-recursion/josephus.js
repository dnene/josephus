/*
$ /usr/local/src/v8/d8 josephus.js
26
element recursion
7.496
7.517
7.522
7.527
7.526
7.524
7.526
7.527
7.524
7.526
*/


function countoff(soldiers, kth, k) {
   var i = soldiers.shift()

   if (soldiers.length > 0){
      if (k != kth){
         k++
         soldiers.push(i)
      } else {
         k = 1
      }
      return countoff(soldiers, kth, k)

   } else {
      return i
   }
}


function countoffSoldiers(n, kth) {
   var soldiers = []
   for (var i = 0; i < n; i++) soldiers[i] = i+1

   //return countoff(soldiers, kth, 1); // standard Josephus problem
   return countoff(soldiers, kth, kth)
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
print ("element recursion")
run_iterations(ITERS,10)
