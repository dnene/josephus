(defn vec-range [start end]
  (loop [start start r (transient [])]
    (if (= start end)
      (persistent! r)
      (recur (inc start) (conj! r start)))))

(defn shout [people n counter]
  (cond
   (= (count people) 1) (nth people 0)
   (zero? counter) (recur (subvec people 1) n (inc counter))
   :else (let [counter (if (= counter (dec n)) 0 (inc counter))]
           (recur (conj (subvec people 1) (nth people 0)) n counter))))

(defn josephus [people nth]
  (shout (vec-range 1 (inc people)) nth 0))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 1000000 10)
