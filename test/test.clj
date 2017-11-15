(ns test
  (:require [clojure.test :refer [deftest is testing]]
            [ring.middleware.data.json :refer [wrap-json-request wrap-json-response]]))

(defn string->stream
  [string]
  (java.io.ByteArrayInputStream. (.getBytes string "UTF-8")))

(defn greeting-handler
  [request]
  (let [{:keys [name salutation] :or {salutation "Hello"}} (:body request)
        message (str salutation ", " name "!")]
    {:body {:message message}}))

(def handler
  (-> greeting-handler
      (wrap-json-request :key-fn keyword)
      (wrap-json-response)))

(deftest end-to-end
  (testing "greeting handler"
    (let [response (handler {:body (string->stream "{\"name\":\"Chris\"}")
                             :headers {"content-type" "application/json"}})
          body (get-in response [:body])
          content-type (get-in response [:headers "Content-Type"])]
      (is (= "{\"message\":\"Hello, Chris!\"}" body))
      (is (= "application/json;charset=utf-8" content-type)))))
