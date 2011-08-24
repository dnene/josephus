(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(definterface IChain
  (kill [^long nth])
  (getFirst ^long [])
  (shout [^long nth]))

(deftype Chain [^java.util.ArrayList persons
                ^{:unsynchronized-mutable true :tag long} index
                ^{:unsynchronized-mutable true :tag long} size]
  IChain
  (getFirst [this]
    (.get persons 0))
  (kill [this nth]
    (set! index 0)
    (while (> size 1)
      (.shout this nth))
    (.getFirst this))
  (shout [this nth]
    (while (< index size)
      (.remove persons index)
      (set! index (+ index (dec nth)))
      (set! size (dec size)))
    (set! index (- index size))))

(defn run []
  (let [al (java.util.ArrayList.)
        n 40]
    (dotimes [x n]
      (.add al (inc x)))
    (let [chain (Chain. al 0 n)]
     (.kill chain 3))))

;(comment
;  (dotimes [_ 10]
;    (time
;     (dotimes [_ 1e6]
;       (run)))))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (run))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (run))
(run-iterations 1000000 10)
