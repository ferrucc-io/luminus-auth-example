(ns user
  (:require [clj-auth.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [clj-auth.core :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'clj-auth.core/repl-server))

(defn stop []
  (mount/stop-except #'clj-auth.core/repl-server))

(defn restart []
  (stop)
  (start))


