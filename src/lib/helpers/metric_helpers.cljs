(ns lib.helpers.metric-helpers
  (:require [lib.state :as s]
            [lib.calculation :as cal]
            [clojure.string :as str]))

(defn str->float
  "Converts string to float"
  [value]
  (if (nil? @value)
    nil
    (do
      (swap! value #(js/parseFloat %))
      value)))

(defn set-state [result metric-name]
  "Computes BMI and updates the state."
  (fn []
    (reset! s/bmi-index-color (last (cal/get-bmi-category result)))
    (cond
      (= metric-name "imperial") (do
                                   (reset! s/imperial-result result)
                                   (reset! s/imperial-bmi-index (first (cal/get-bmi-category result))))
      (= metric-name "metric") (do
                                 (reset! s/metric-bmi-index (first (cal/get-bmi-category result)))
                                 (reset! s/metric-result result)))))

(defn button-helper [weight height f bmi-name]
  "Button envent handler."
  (let [bmi-result (f weight height)]
    [:a.button {:on-click (set-state bmi-result bmi-name)} "Calculate"]))

(defn text-input-helper
  "Text input helper."
  [metric]
  [:input.input.is-info {:type "text"
                         :value @metric
                         :on-change #(reset! metric (-> % .-target .-value))}])

(defn cs
  "Builds css classes dynamically."
  [& names]
  (str/join " " (filter identity names)))
