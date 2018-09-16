(ns clj-auth.models
    (:require [toucan [db :as db]
              [models :as models]]
              [clojure.java.jdbc :as jdb])
    (:gen-class))

(defn create-table [jdb]
  (jdb/execute!
    jdb
    ["CREATE EXTENSION IF NOT EXISTS \"uuid-ossp\""])
  (jdb/execute!
    jdb
    ["CREATE TABLE IF NOT EXISTS users_table
         (id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
          email TEXT NOT NULL UNIQUE,
          password TEXT NOT NULL,
          date_created TIMESTAMPTZ NOT NULL DEFAULT now())"]))

(models/defmodel User :users_table
                 models/IModel
                 (properties [_]
                             {:timestamped true})
                 (default_fields [_]
                                 [:id :email :password]))