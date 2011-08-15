(defn shout [start nth coll acc]
        (if (and (== 1 (count coll)) (== 0 (count acc)))
            (first coll)
            (if (== (count coll) 0) 
                (recur start nth (reverse acc) ())
                (if (== 1 start)
                    (recur (inc start) nth (rest coll) acc)
                    (recur (if (== start nth) 1 (inc start)) nth (rest coll) (conj acc (first coll)))))))

(defn josephus [people nth]
  (shout 1 nth (range 1 (inc people)) ()))

(defn countdown [iterations] 
  (josephus 40 3)
  (if (== 0 iterations)
    0
    (recur (dec iterations))))

(println (josephus 40 3))
(countdown 100000)
(let [start (System/currentTimeMillis) iterations 100000]
    (countdown iterations)
    (let [end (System/currentTimeMillis)]
        (println (/ (* (- end start) 1000.00) iterations) " microseconds")))

