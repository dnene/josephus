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

;(defn countdown [iterations]
; (dotimes [_ iterations]
;   (josephus 40 3)))
(defn countdown [iterations] 
  (josephus 40 3)
  (if (zero? iterations)
    0
    (recur (dec iterations))))

; verify
(println (josephus 40 3))

; warmup
(countdown 100000)

; measure
(let [start (System/currentTimeMillis) iterations 100000]
    (countdown iterations)
    (let [end (System/currentTimeMillis)]
        (println (/ (* (- end start) 1000.00) iterations) " microseconds")))

