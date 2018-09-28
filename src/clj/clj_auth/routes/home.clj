(ns clj-auth.routes.home
  (:require [clj-auth.layout :as layout]
            [clj-auth.utilities :refer :all]
            [compojure.core :refer [defroutes GET]]
            [ring.util.response :as res]
            [clj-auth.models :as db-model]
            [toucan.db :as db]
            [buddy.auth :refer [authenticated? throw-unauthorized]]
            [clojure.java.io :as io]))

(defn home-page [req]
  (if (req-user req)
    (layout/render
      "home.html" {:user (req-user req)})
    (layout/render
      "home.html" {:user "anonymous"})))

(defn forgot-password [req]
      (if (req-user req)
        (res/redirect "/")
        (layout/render
          "forgot-password.html")))

(defn reset-password-page [user_email token]
      (if (db/exists? db-model/User :email user_email)
        (do (if (= token (get (user-selector user_email) :token))
                (layout/render "reset-password.html" {:title "Reset Password"
                                                      :email user_email
                                                      :token token})
                (layout/error-page {:status 200
                                    :title "Page not found"
                                    :message "This token is not valid"})))
        (layout/error-page {:status 200
                            :title "User doesn't exist"
                            :message "This user doesn't exist"})))
(defn profile-page [req]
  (if-not (req-user req)
    (layout/render "profile.html" {:user "anon"})
    (let [user_email (req-user req)]
      (layout/render "profile.html" {:user user_email}))))

(defroutes home-routes
  (GET "/" req (home-page req))
  (GET "/forgot-password" req (forgot-password req))
  (GET "/reset/:user_email/:token" [user_email token] (reset-password-page user_email token))
  (GET "/u/profile" req (profile-page req)))

