;; contributed by Stuart Halloway
;; This is not idiomatic Clojure, rather, a simple-minded post of the
;; Java code, in the name of an apples-apples comparison.

(import [java.util ArrayList LinkedList List ListIterator])

(set! *warn-on-reflection* true)

(defn countoff
  [^ListIterator ring ^LinkedList reset kth k]
  (loop [ring ring
         reset reset
         kth (int kth)
         k (int k)]
    (if (.hasNext ring)
      (do
        (.next ring)
        (if (not= k kth)
          (recur ring reset kth (inc k))
          (do
            (.remove ring)
            (recur ring reset kth (int 1)))))
      (if (> (.nextIndex ring) 1)
        (recur (.listIterator reset (int 0)) reset kth k)
        (.previous ring)))))
            
(defn josephus
  [n kth]
  (let [soldiers (LinkedList.)]
    (dotimes [i n]
      (.add soldiers (inc i)))
    (countoff (.listIterator soldiers) soldiers kth kth)))

(defn run-iterations [iterations times]
  (dotimes [_ times]
    (let [start (System/nanoTime)]
        (dotimes[_ iterations] 
            (josephus 40 3))
        (let [end (System/nanoTime)]
            (println (float (/ (- end start) (* 1000 iterations))))))))

(println (josephus 40 3))
(run-iterations 100000 10)

