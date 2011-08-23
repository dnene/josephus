(defn shout ^long [^long counter ^long nth ^long cnt coll]
  (if (= 1 cnt)
    (first coll)
    (let [[new-count result]
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
              [new-count (persistent! result)]))]
      (recur (rem (+ counter cnt) nth)
             nth
             (long new-count)
             result))))

(defn josephus ^long [^long people ^long nth]
  (shout 0 nth people (into [] (range 1 (inc people)))))

(defn run-iterations [^long iterations ^long times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 1000000 10)

