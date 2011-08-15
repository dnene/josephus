(defn shout [counter nth coll]
    (if (== (count coll) 1)
        (first coll)
        (shout
            (rem (+ counter (count coll)) nth)
            nth
            (keep-indexed #(if(not= 0 (rem (+ %1 counter) nth)) %2) coll))))

(defn josephus [people nth]
  (shout 0 nth (range 1 (inc people))))

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

