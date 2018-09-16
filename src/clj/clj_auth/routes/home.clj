(ns clj-auth.routes.home
  (:require [clj-auth.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html"))

(defn about-page []
  (layout/render "about.html"))

(defn profile-page []
  (layout/render "profile.html" {:user "ferruccio"}))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/u/profile" [] (profile-page)))

