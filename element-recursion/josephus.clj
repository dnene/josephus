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

(defn countdown [iterations] 
  (dotimes [_ iterations]
  (josephus 40 3)))

(println (josephus 40 3))
(countdown 100000)
(let [start (System/currentTimeMillis) iterations 100000]
    (countdown iterations)
    (let [end (System/currentTimeMillis)]
        (println (/ (* (- end start) 1000.00) iterations) " microseconds")))
