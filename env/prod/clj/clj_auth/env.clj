(ns clj-auth.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[clj-auth started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[clj-auth has shut down successfully]=-"))
   :middleware identity})
