(ns lib.calculation
  (:require [goog.string :as gstring]
            [goog.string.format]))

(def pos?? (complement pos?))

(defn bmi-in-metric
  "Computes BMI in metric"
  [weight-kg height-meters]
  (cond
    (zero? weight-kg) "Weight cannot be zero."
    (zero? height-meters) "Height cannot be zero."
    (or (pos?? weight-kg) (pos?? height-meters)) "Values cannot be negative."
    :esle (goog.string.format "%.2f" (float (/ weight-kg (* height-meters height-meters))))))

(defn bmi-in-imperial
  "Computes BMI in imperial"
  [weight-pounds height-inches]
  (cond
    (zero? weight-pounds) "Weight cannot be zero."
    (zero? height-inches) "Height cannot be zero."
    (or (pos?? weight-pounds) (pos?? height-inches)) "Values cannot be negative."
    :esle (.toFixed (float (* (/ weight-pounds (* height-inches height-inches)) 703)) 2)))

(defn get-bmi-category
  "Returns BMI category and css class for the category."
  [bmi]
  (if (nil? bmi)
    nil
    (do
      (cond
        (< bmi 15.0) ["Very severely underweight" "is-warning"]
        (and (>= bmi 15) (< bmi 16)) ["Severely underweight" "is-warning"]
        (and (>= bmi 16) (< bmi 18.5)) ["Under weight" "is-warning"]
        (and (>= bmi 18.5) (< bmi 25)) ["Normal" "is-success"]
        (and (>= bmi 25) (< bmi 30)) ["Over weight" "is-danger"]
        (and (>= bmi 30) (< bmi 35)) ["Moderately obese" "is-danger"]
        (and (>= bmi 35) (< bmi 40)) ["Severely obese" "is-danger"]
        (>= bmi 40) ["Very severely obese" "is-danger"]))))
