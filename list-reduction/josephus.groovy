def find_last(count=40, nth=3) {
    def people = 1 .. count
    def counter = 0
    while (people.size() > 1) {
        def new_people = []
        for(i in 0 .. people.size() - 1) {
            if ((i + counter) % nth != 0) {
                new_people.add(people.get(i))
            }
        }
        counter = (counter + people.size()) % nth
        people = new_people
    }
    return people.get(0)
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
