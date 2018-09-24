(ns clj-auth.routes.home
  (:require [clj-auth.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page [req]
  (println (:identity req))
  (layout/render
    "home.html"))

(defn about-page []
  (layout/render "about.html"))

(defn profile-page []
  (layout/render "profile.html" {:user "ferruccio"}))

(defroutes home-routes
  (GET "/" req (home-page req))
  (GET "/about" [] (about-page))
  (GET "/u/profile" [] (profile-page)))

