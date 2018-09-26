(ns clj-auth.routes.auth
  (:require [clj-auth.layout :as layout]
            [clj-auth.models :as db-model]
            [clj-auth.utilities :refer :all]
            [compojure.core :refer :all]
            [ring.util.response :as res]
            [toucan.db :as db])
  (:gen-class))


(defn register-handler [req]
  (if (= (get-in req [:form-params "password"]) (get-in req [:form-params "confirm-password"]))
    (let [email (get-in req [:form-params "email"])
          password (get-in req [:form-params "password"])]
      (if (db/exists? db-model/User :email email)
          (layout/error-page {:status 200
                              :title "User already exists"
                              :message "Did you forget your password?"})
          (do
            (user-create email password)
            (res/redirect "/"))))
    (layout/error-page {:status 200
                        :title "Passwords don't match"
                        :message "The passwords you wrote aren't the same"})))

(defn login-handler [req]
  (let [email (get-in req [:form-params "email"])
        password (get-in req [:form-params "password"])
        session (:session req)]
    (if (db/exists? db-model/User :email email)
      (do
        (if (check-password password (:password (user-selector email)))
          (do (let [ updated-session (assoc session :identity (keyword email))]
                (-> (res/redirect "/")
                    (assoc :session updated-session))))
          (layout/error-page {:status 200
                              :title "Wrong password"
                              :message "The password you typed is wrong"})))
      (layout/error-page {:status 200
                          :title "User doesn't exist"
                          :message "This user doesn't exist"}))))

(defn logout [req]
  (-> (res/redirect "/")
      (assoc :session {})))


(defroutes auth-routes
  (POST "/auth" req (login-handler req))
  (POST "/register" req (register-handler req))
  (GET "/logout" req (logout req)))

