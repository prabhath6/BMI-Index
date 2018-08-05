(ns lib.component.metric
  (:require [reagent.core :as r]
            [lib.state :as s]
            [lib.calculation :as cal]
            [lib.helpers.metric-helpers :as helpers]))

(defn metric-component []
  (let [height (r/atom "1.8")
        weight (r/atom "75")
        height-converted (helpers/str->float height)
        weight-converted (helpers/str->float weight)
        type-name "metric"]
    (fn []
      [:div
       [:p "height: " [helpers/text-input-helper height-converted]]
       [:p "weight: " [helpers/text-input-helper weight-converted]]
       (when @s/metric-result [:p "metric result: " @s/metric-result])
       [:p "bmi index: " @s/metric-bmi-index]
       [helpers/button-helper
        @weight-converted
        @height-converted
        type-name
        cal/bmi-in-metric
        "metric"]])))
