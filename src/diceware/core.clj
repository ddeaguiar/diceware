(ns diceware.core)

(defn load-file
  [path]
  (with-open [r (clojure.java.io/reader path)]
    (->> (line-seq r)
       (map #(let [[k v] (clojure.string/split % #"\s")]
               (hash-map k v)))
       (into {}))))

(def word-bank (load-file "resources/wordlist.asc"))
(def die (vec (range 1 7)))

(defn roll-die
  []
  (let [r (rand-int 6)]
    (get die r)))

(defn gen-word
  [bank]
  (let [rolls (for [_ (range 5)]
                (roll-die))
        key (apply str rolls)]
    (get bank key)))

(defn gen-password
  [n bank]
  (clojure.string/join " " (take n (repeatedly #(gen-word bank)))))

(gen-password 5 word-bank)
