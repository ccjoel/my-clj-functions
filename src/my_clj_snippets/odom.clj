(ns my-clj-snippets.odom)
;; odometer. untz untz untz untz...

(defn cap-width->max-number [cap width]
  (apply str (take width (repeat cap))))

(defn- to-decimal [s r]
  (Integer/parseInt s r))

(defn- to-radix [int r]
  (Integer/toString int r))

(defn pad->str [width number]
  (if (>= (count number) width)
    number
    (let [digits-to-add (- width (count number))]
      (str (apply str (take digits-to-add (repeat "0"))) number))))

(defn odo-print
  "
  example:
  - cap = 3 and width = 4:
  will print numbers (padded to width) between 0000 and 3333

  - cap = 6 and width = 4:
  will print numbers 0000 and 6666
  "
  [cap width]
  (let [radix (inc cap)
        max-number (to-decimal (cap-width->max-number cap width) radix)]
    (loop [x 0]
      (when (<= x max-number)
        (println (pad->str width (to-radix x radix)))
        (recur (inc x))))))

(defn- gen-next-number-fn [cap max]
  (fn [num]
    (if (>= (Long/parseLong num) (Long/parseLong (cap-width->max-number cap max)))
      nil
      (let [radix (inc cap)]
        (pad->str max (to-radix (inc (to-decimal num radix)) radix))))))

(defn next-number [num cap max]
  ((gen-next-number-fn cap max) num))

(defn odo-lazy [cap width]
  [cap width]
  (let [next-num-fn (gen-next-number-fn cap width)
        initial-number (apply str (take width (repeat "0")))]
    (iterate next-num-fn initial-number)))


;; ---------------


;; brainstorming

(defn binary [i]
   (to-radix i 2))

(defn hex [i]
  (to-radix i 16))

;; (format "%o" 19)
;; => "23"
