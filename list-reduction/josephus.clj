(defn shout
  ([nth coll] (let [to-kill (dec nth)] (shout to-kill to-kill coll)))
  ([counter to-kill coll]
      (if (empty? (rest coll))
        (first coll)
        (let [[next-counter next-coll]
              (loop [current-idx counter
                     to-process coll
                     res []]
                (if (empty? to-process)
                  [current-idx res]
                  (if (== current-idx to-kill)
                    (recur 0 (rest to-process) res)
                    (recur (unchecked-inc current-idx)
                           (rest to-process)
                           (conj res (first to-process))))))]
          (recur next-counter to-kill next-coll)))))

(defn josephus [people nth]
  (shout nth (range 1 (inc people))))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 1000000 10)

