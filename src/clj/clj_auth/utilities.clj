(ns clj-auth.utilities
  (:require [compojure.core :refer :all]
            [clj-auth.models :as model]
            [toucan.db :as db]
            [buddy.hashers :as hashers])
  (:gen-class))

(defn ok
  "Default ok response"
  ([] (ok nil))
  ([body]
   {:status 200
    :headers {}
    :body body}))

(defn user-selector [email]
  "Given an email returns a user object from the DB"
  (db/select-one model/User :email email))

(defn check-password [input-password db-password]
  "Checks if a password corresponds to the hashed password in the DB"
  (hashers/check input-password db-password))

(defn create-password [input-password]
  "Hashes password to store it in DB"
  (hashers/derive input-password))

(defn user-create [email password]
  "Creates an instance of user in the database"
  (def pw (create-password password))
  (db/insert! model/User {:email email
                          :password pw}))

(defn req-user [req]
  (if (get-in req [:session :identity])
    (name (get-in req [:session :identity]))))