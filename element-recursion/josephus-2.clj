(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(defn shout [^java.util.ArrayList people ^long n ^long counter]
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

(defn josephus [people nth]
  (let [al (java.util.ArrayList.)]
    (dotimes [x people]
      (.add al (inc x)))
   (shout al nth 0)))

;(defn run-el-recur-al []
  ;(println (josephus 40 3))
  ;(time
   ;(dotimes [_ 1000000]
     ;(josephus 40 3))))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 1000000 10)
