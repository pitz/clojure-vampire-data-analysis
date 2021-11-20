(ns vampireanalysis.core)

(def filename "suspects.csv")

(def vampire-keys 
    [:name 
    :power-index])

(defn str->int
    [str]
    (Integer. str))

(def conversions {:name identity 
                  :power-index str->int})

(defn convert
      [vampire-key value]
      ((get conversions vampire-key) value))

(defn parse
    [string]
    (map #(clojure.string/split % #",")
          (clojure.string/split string #"\n")))

;; (println (parse (slurp filename)))

(defn mapify
    [rows]
    (map (fn [unmapped-row]
        (reduce (fn [row-map [vampire-key value]]
                    (assoc row-map vampire-key (convert vampire-key value)))
                {}
                (map vector vampire-keys unmapped-row)))
        rows))

;; Código comentado é feio mesmo mas irei manter isso por aqui.
;; (println "Mapear todos: ")
;; (println (mapify (parse (slurp filename))))
;; (println "Take 1: ")
;; (println (take 1 (mapify (parse (slurp filename)))))
;; (println "Ordenar pelo :name: ")
;; (println (sort-by :name (mapify (parse (slurp filename)))))

(defn power-index-filter
    [minimum-power-index records]
    (filter #(>= (:power-index %) minimum-power-index) records))

(println "VAMPIROS DETECTADOS: ")
(println (power-index-filter 3 (mapify (parse (slurp filename)))))