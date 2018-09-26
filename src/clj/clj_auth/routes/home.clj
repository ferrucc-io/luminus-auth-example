(ns clj-auth.routes.home
  (:require [clj-auth.layout :as layout]
            [clj-auth.utilities :refer :all]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [buddy.auth :refer [authenticated? throw-unauthorized]]
            [clojure.java.io :as io]))

(defn home-page [req]
  (if (req-user req)
    (layout/render
      "home.html" {:user (req-user req)})
    (layout/render
      "home.html" {:user "anonymous"})))

(defn profile-page [req]
  (if-not (req-user req)
    (layout/render "profile.html" {:user "anon"})
    (let [user_email (req-user req)]
      (layout/render "profile.html" {:user user_email}))))

(defroutes home-routes
  (GET "/" req (home-page req))
  (GET "/u/profile" req (profile-page req)))

