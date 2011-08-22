(defn shout ^long [counter nth coll]
  (let [cnt (count coll)]
    (if (= 1 cnt)
      (first coll)
      (shout (rem (+ counter cnt) nth)
             nth
             (keep-indexed (fn [index item]
                             (when-not (zero? (rem (+ index counter)
                                                   nth))
                               item))
                           coll)))))

(defn josephus [people nth]
  (shout 0 nth (into [] (range 1 (inc people)))))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 1000000 10)

