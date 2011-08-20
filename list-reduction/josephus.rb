class Josephus
    def initialize(count,nth)
        @count = count
        @nth = nth
    end
    def shout()
        counter = 0
        people = (1..@count).to_a
        while people.size > 1
            new_people = Array.new
            for index in 0 ... people.size
                if (counter + index) % @nth != 0 then
                    new_people.push people[index]
                end
            end
            counter = (counter + people.size) % @nth
            new_people.size
            people = new_people
        end
        return people[0]
    end
end

j = Josephus.new(40,3)
puts j.shout()
TIMES=10
ITER=1000000
TIMES.times { |i|
    start = Time.now
    ITER.times { |k|
        j.shout()
    }
    ends = Time.now
    puts ((ends - start) * 1000000 / ITER).to_s()
}
