(ns clj-auth.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [clj-auth.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[clj-auth started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[clj-auth has shut down successfully]=-"))
   :middleware wrap-dev})
