(ns lib.component.imperial
  (:require [reagent.core :as r]
            [lib.state :as s]
            [lib.calculation :as cal]
            [lib.helpers.metric-helpers :as helpers]))

(defn imperial-component []
  (let [height (r/atom "71")
        weight (r/atom "150")
        height-converted (helpers/str->float height)
        weight-converted (helpers/str->float weight)
        type-name "imperial"]
    (fn []
      [:div
       [:p "height: " [helpers/text-input-helper height-converted]]
       [:p "weight: " [helpers/text-input-helper weight-converted]]
       (when @s/imperial-result [:p "imperial result: " @s/imperial-result])
       [:p "bmi index: " @s/imperial-bmi-index]
       [helpers/button-helper
        @weight-converted
        @height-converted
        type-name
        cal/bmi-in-imperial
        "imperial"]])))
