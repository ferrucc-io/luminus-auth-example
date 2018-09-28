(ns clj-auth.mailing
    (:require [postal.core :refer [send-message]])
    (:gen-class))

(defn send-pw-reset [user_email token]
      (def message_body (str "Click this linke to reset your password: localhost:3000/reset/" user_email "/" token))
      (def mail_info {:host "smtp.mailtrap.io"
                      :user "12341d55f5c1ed"
                      :pass "ADD YOUR OWN"})

      (def no-reply-email "my@u.th")

      (send-message mail_info {:from no-reply-email
                               :to user_email
                               :subject "Password reset"
                               :body message_body}))