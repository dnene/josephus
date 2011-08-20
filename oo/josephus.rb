class Person
    attr_reader :count, :prev, :next
    attr_writer :count, :prev, :next

    def initialize(count)
        #puts 'Initializing person : ' + count.to_s()
        @count = count
        @prev = nil
        @next = nil
    end

    def count()
        return @count
    end

    def shout(shout, nth)
        if shout == 1
            @prev.next = @next
            @next.prev = @prev
            return shout + 1
        else 
            if shout == nth
                return 1
            else
                return shout + 1
            end
        end
    end
end

class Chain
    attr_reader :first
    attr_writer :first

    def initialize(size)
        @first = nil
        last = nil
        for i in (1..size)
            current = Person.new(i)
            if @first == nil
                @first = current
            end
            if last != nil
                last.next = current
                current.prev = last
            end
            last = current
        end
        @first.prev = last
        last.next = @first
    end

    def kill(nth)
        current = @first
        shout = 1
        while current.next != current
            shout = current.shout(shout,nth)
            current = current.next
        end
        @first = current
        return current
    end

    def first()
        return @first
    end
end

chain = Chain.new(40)
chain.kill(3)
puts chain.first.count
TIMES=10
ITER=1000000
TIMES.times { |i|
    start = Time.now
    ITER.times { |k|
        chain = Chain.new(40)
        chain.kill(3)
    }
    ends = Time.now
    puts ((ends - start) * 1000000 / ITER).to_s()
}
