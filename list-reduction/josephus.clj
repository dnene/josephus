(defn shout [^long counter ^long nth [cnt coll]]
  (if (= 1 cnt)
    (first coll)
    (shout (rem (+ counter cnt) nth)
           nth
           (loop [index 0
                  new-count 0
                  remaining coll
                  result (transient [])]
             (if (seq remaining)
               (if-not (zero? (rem (+ index counter) nth))
                 (recur (inc index)
                        (inc new-count)
                        (rest remaining)
                        (conj! result (first remaining)))
                 (recur (inc index)
                        new-count
                        (rest remaining)
                        result))
               [new-count (persistent! result)])))))

(defn josephus [people nth]
  (shout 0 nth [people (into [] (range 1 (inc people)))]))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 1000000 10)

