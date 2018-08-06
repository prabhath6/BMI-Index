(ns lib.component.metric
  (:require [reagent.core :as r]
            [lib.state :as s]
            [lib.calculation :as cal]
            [lib.helpers.metric-helpers :as helpers]))

(defn metric-component
  "Computes BMI based on the metric system."
  []
  (let [height (r/atom "1.8")
        weight (r/atom "75")
        height-converted (helpers/str->float height)
        weight-converted (helpers/str->float weight)]
    (fn []
      [:div.tile.is-parent
       [:article.tile.is-child.notification.is-info
        [:p.title.has-text-centered "Metric BMI"]
        [:div.columns.is-mobile
         [:div.column.is-three-fifths.is-offset-one-fifth
          [:div.field
           [:label.label "Height"]
           [:div.control
            [helpers/text-input-helper height-converted]]]
          [:div.field
           [:label.label "Weight"]
           [:div.control
            [helpers/text-input-helper weight-converted]]]
          [helpers/button-helper
           @weight-converted
           @height-converted
           cal/bmi-in-metric
           "metric"]
          [:br]
          [:br]
          (when @s/metric-bmi-index [:a {:class (helpers/cs "button"
                                                            @s/bmi-index-color
                                                            "is-inverted")}
                                     "BMI = " @s/metric-result " Category = " @s/metric-bmi-index])]]]])))
