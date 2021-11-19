(ns vampireanalysis.core)
(def filename "suspects.csv")

(def vampire-keys [:name :power-index])

(defn str->int
    [str]
    (Integer. str))

(def conversions {:name identity 
                  :power-index str->int})

(defn convert
      [vamp-key value]
      ((get conversions vamp-key) value))

(defn parse
    [string]
    (map #(clojure.string/split % #",")
          (clojure.string/split string #"\n")))

(println (parse (slurp filename)))