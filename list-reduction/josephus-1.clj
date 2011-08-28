(defn shout [counter nth coll]
    (if (== (count coll) 1)
        (first coll)
        (shout
            (rem (+ counter (count coll)) nth)
            nth
            (keep-indexed #(if(not= 0 (rem (+ %1 counter) nth)) %2) coll))))

(defn josephus [people nth]
  (shout 0 nth (range 1 (inc people))))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 1000000 10)

