(ns lib.state
  (:require [reagent.core :as r]))

(def metric-result (r/atom ""))
(def imperial-result (r/atom ""))
(def metric-bmi-index (r/atom nil))
(def imperial-bmi-index (r/atom nil))
(def bmi-index-color (r/atom ""))
