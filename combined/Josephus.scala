import collection.mutable.ArrayBuffer

/* 
 * Improvements by Rahul Goma Phulore http://twitter.com/missingfaktor http://stackoverflow.com/users/192247/missingfaktor
 *  - Changed for loop to ExtraControls.loop
 *  - Used ArrayBuffer instead of Linked List
 */

object ExtraControls {
  def loop(a: Int, b: Int)(f: Int => Unit): Unit = {
    var i = a
    while(i <= b) {
      f(i)
      i += 1
    }
  }
}

class Person(val count: Int, var prev: Person = null, var next: Person= null) {
  def shout(counter: Int, nth: Int) = {
    if (counter == 1) {
      prev.next = next
      next.prev = prev
    }
    if (counter == nth) 1 else counter + 1
  }
}

class Chain(val count: Int) {
  import ExtraControls.loop

  private var last: Person = null
  private var first: Person = null
  loop(1, count) { i =>
    val p = new Person(i,last)
    if (i == 1) first = p
    else last.next = p
    last = p
  }
  first.prev = last
  last.next = first
  last = null // can be forgotten
  def shout(nth: Int): Int = {
    var counter = 1
    var current = first
    while (current != current.next) {
      counter = current.shout(counter,nth)
      current = current.next
    }
    current.count
  }
}
object Josephus{
  import ExtraControls.loop

  def runIterations(tag: String, iterations: Int, times: Int)(f: => Unit): Unit = {
    println("Running " + tag)
    loop(1, times) { _ =>
      System.gc();
      var start = System.nanoTime()
      loop(1, iterations) { _ => f }
      var end = System.nanoTime()
      println(((end - start) * 1.0) / (iterations * 1000))
    }
  }

  def main(args : Array[String]) : Unit = {
    val ITER = 1000000
    val chain = new Chain(40)
    println(chain.shout(3))

    runIterations("oo",ITER,10) {
      val chain = new Chain(40)
      chain.shout(3)
    }

    println(shoutRecursive(List.range(1, 41),Nil,3,1))
    runIterations ("element recursive", ITER,10) {
      shoutRecursive(List.range(1, 41),Nil,3,1)
    }

    println(shoutListProcessing(ArrayBuffer.range(1, 41),3))
    runIterations ("list reduction", ITER, 10){
      shoutListProcessing(ArrayBuffer.range(1, 41),3)
    }
  }

  def shoutListProcessing(soldiers: ArrayBuffer[Int], n: Int): Int = {
    var counter = 0
    var people = soldiers
    while (people.length > 1) {
        val newPeople = ArrayBuffer.empty[Int]
        loop(0, people.length - 1) { i =>
          if ((counter + i) % n != 0) {
            newPeople += people(i)
          }
        }
        counter = (counter + people.length) % n
        people = newPeople
    }
    people(0)
  }

  @scala.annotation.tailrec 
  final def shoutRecursive(soldiers : List[Int], survivors: List[Int], n: Int, counter: Int): Int = {
    if (soldiers.isEmpty) shoutRecursive(survivors.reverse, Nil, n, counter)
    else {
      if (soldiers.tail.isEmpty && survivors.isEmpty) soldiers.head
      else
        if (counter == 1) shoutRecursive(soldiers.tail, survivors, n, 2)
        else shoutRecursive(soldiers.tail, soldiers.head :: survivors, 
                            n, if (counter == n) 1 else counter + 1)
    }
  }
}

