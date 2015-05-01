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

(defn roll
  "Rolls a die"
  ([]
   (roll 6))
  ([sides]
   (lazy-seq (cons (inc (rand-int sides))
                   (roll sides)))))

(defn word-key
  "Creates a word key from die rolls"
  [rolls]
  (apply str rolls))

(defn gen-password
  "Generates a password of length n based on the specified word bank."
  [n bank]
  (->> (roll)
       (partition 5)
       (take n)
       (map word-key)
       (map #(get bank %))
       (clojure.string/join " ")))

(comment
  (gen-password 10 word-bank)
  )

(defn -main [& args]
  (println (gen-password (Integer/parseInt (first args)) word-bank)))
