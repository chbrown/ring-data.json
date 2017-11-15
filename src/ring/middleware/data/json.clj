(ns ring.middleware.data.json
  (:require [clojure.data.json :as json]))

(defn- json-request
  "Sync/async helper used by wrap-json-request"
  [request options]
  (if (some->> (get-in request [:headers "content-type"]) (re-find #"^application/json"))
    (update-in request [:body] #(apply json/read-str (slurp %) options))
    request))

(defn wrap-json-request
  "Middleware that parses the body of a JSON request if the content-type header indicates JSON;
  options are passed to clojure.data.json/read-str"
  [handler & options]
  (fn
    ([request] ; sync
     (handler (json-request request options)))
    ([request respond raise] ; async
     (handler (json-request request options) respond raise))))

(defn- json-response
  "Sync/async helper used by wrap-json-response"
  [response options]
  (if (coll? (:body response))
    (-> response
        (update-in [:body] #(apply json/write-str % options))
        (assoc-in [:headers "Content-Type"] "application/json;charset=utf-8"))
    response))

(defn wrap-json-response
  "Middleware that converts the coll? body of a response to JSON;
  options are passed to clojure.data.json/write-str"
  [handler & options]
  (fn
    ([request] ; sync
     (json-response (handler request) options))
    ([request respond raise] ; async
     (handler request (fn [response] (respond (json-response response options))) raise))))
