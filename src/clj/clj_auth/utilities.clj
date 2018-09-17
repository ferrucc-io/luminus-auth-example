(ns clj-auth.utilities
  (:require [compojure.core :refer :all]
            [clj-auth.models :as model]
            [toucan.db :as db]
            [buddy.hashers :as hashers]))

(defn user-selector [email]
  (db/select-one model/User :email email))

(defn check-password [input-password db-password]
  (hashers/check input-password db-password))

(defn create-password [input-password]
  (hashers/derive input-password))

(defn user-create [email password]
  (db/insert! model/User {:email email
                          :password (create-password password)}))