(ns diceware.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(defn load-bank
  [resource]
  (with-open [r (io/reader resource)]
    (->> (line-seq r)
       (map #(let [[k v] (clojure.string/split % #"\s")]
               (hash-map k v)))
       (into {}))))

(def word-bank (load-bank (io/resource "wordlist.asc")))
(def die (vec (range 1 7)))

(defn roll-die
  []
  (let [r (rand-int 6)]
    (get die r)))

(defn gen-word
  [bank]
  (let [rolls (take 5 (repeatedly roll-die))
        key (apply str rolls)]
    (get bank key)))

(defn gen-password
  [n bank]
  (clojure.string/join " " (take n (repeatedly #(gen-word bank)))))

(comment
  (gen-password 5 word-bank))

(defn -main [& args]
  (println (gen-password (Integer/parseInt (first args)) word-bank)))
