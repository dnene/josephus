(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; N.B. referentially transparent and threadsafe even with internal
;; use of mutation.
(defn josephus
  [people nth]
  (let [shout
        (fn [^java.util.LinkedList people ^long n ^long counter]
          (cond
           (= (.size people) 1) (.get people 0)
           (zero? counter) (do
                             (.remove people 0)
                             (recur people n (inc counter)))
                 :else (let [counter (if (= counter (dec n))
                                       0 (inc counter))
                             f (.get people 0)]
                         (.remove people 0)
                         (.add people f)
                         (recur people n counter))))
        al (java.util.LinkedList.)]
    (dotimes [x people]
      (.add al (inc x)))
   (shout al nth 0)))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 1000000 10)
