def find_last(count=40, nth=3) {
    find_last(1 .. count, [], 3, 0)
}

def find_last(people,acc,nth,count) {
    if ((acc.size() == 0) && (people.size() == 1)) {
        return people.get(0)
    }
    else if (people.size() == 0) {
        find_last(acc,[],3,count)
    }
    else {
        if (count == 0) {
            count = 1
            people = people.tail()
        } else {
            if (count == (nth - 1)) {
                count = 0
            } else {
                count = count + 1
            }
            head = people.remove(0)
            acc.add(head)
        }
        find_last(people,acc,nth,count)
    }
}

def runIterations(iterations, times) {
    for(i = 0 ; i < times ; i++) {
        def start = System.nanoTime()
        for(def j = 0 ; j < iterations ; j++)
            find_last()
        def end = System.nanoTime()
        println ((end - start)/(iterations * 1000))
    }
}

println find_last()
runIterations(1000000,10)
