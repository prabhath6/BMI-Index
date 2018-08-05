(ns lib.helpers.stuff
  (:require [lib.state :as s]
            [lib.calculation :as cal]))

(defn str->float [value]
  (if (nil? @value)
    nil
    (do
      (swap! value #(js/parseFloat %))
      value)))

(defn set-state [result metric-name]
  (fn []
    (cond
      (= metric-name "imperial") (do
                                   (reset! s/imperial-result result)
                                   (reset! s/imperial-bmi-index (cal/get-bmi-category result)))
      (= metric-name "metric") (do
                                 (reset! s/metric-bmi-index (cal/get-bmi-category result))
                                 (reset! s/metric-result result)))))

(defn button-helper [weight height type f bmi-name]
  (let [bmi-result (f weight height)]
    [:input {:type "button"
             :value type
             :on-click (set-state bmi-result bmi-name)}]))

(defn text-input-helper [metric]
  [:input {:type "text"
           :value @metric
           :on-change #(reset! metric (-> % .-target .-value))}])
