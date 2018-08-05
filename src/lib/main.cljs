(ns lib.main
  (:require [reagent.core :as r]
            [lib.component.imperial :as imperial]
            [lib.component.metric :as metric]))

(defn app-component []
  [:div
   [:div
    [metric/metric-component]
    [:br]
    [:div
     [imperial/imperial-component]]]])

;; Render functions
(defn ^:private render []
  (r/render
    [app-component]
    (.getElementById js/document "app")))

(defn reload!
  "Used for hot code reloading."
  []
  (render))

(defn ^:export main
  "Renders the whole application."
  []
  (render))
