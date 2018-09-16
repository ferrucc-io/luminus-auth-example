(ns clj-auth.routes.auth
  (:require [clj-auth.layout :as layout]
            [compojure.core :refer :all]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn login-handler [req]
  (println req)
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body {:success "True"
          :content "No Content"}})

(defn register-handler [req]
  (println req)
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body {:success "True"
          :content "No Content"}})

(defroutes auth-routes
  (POST "/auth" req (login-handler req))
  (POST "/register" req (register-handler req)))

