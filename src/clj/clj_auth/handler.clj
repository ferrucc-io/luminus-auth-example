(ns clj-auth.handler
  (:require [clj-auth.middleware :as middleware]
            [clj-auth.layout :refer [error-page]]
            [clj-auth.routes.home :refer [home-routes]]
            [clj-auth.routes.auth :refer [auth-routes]]
            [compojure.core :refer [routes wrap-routes]]
            [ring.util.http-response :as response]
            [compojure.route :as route]
            [clj-auth.env :refer [defaults]]
            [mount.core :as mount]))

(mount/defstate init-app
  :start ((or (:init defaults) identity))
  :stop  ((or (:stop defaults) identity)))

(mount/defstate app
  :start
  (middleware/wrap-base
    (routes
      (-> #'auth-routes
          (wrap-routes middleware/wrap-formats))
      (-> #'home-routes
          (wrap-routes middleware/wrap-csrf)
          (wrap-routes middleware/wrap-formats))
          (route/not-found
             (:body
               (error-page {:status 404
                            :title "page not found"}))))))

