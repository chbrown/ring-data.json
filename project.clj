(defproject chbrown/ring-data.json "1.0.0-SNAPSHOT"
  :description "Ring middleware for handling JSON with data.json"
  :url "https://github.com/chbrown/ring-data.json"
  :license {:name "Eclipse Public License"
            :url "https://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories [["releases" :clojars]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.json "0.2.6"]]
  :profiles {:dev {:plugins [[lein-codox "0.10.3"]]
                   :codox {:source-paths ["src"]
                           :source-uri "https://github.com/chbrown/ring-data.json/blob/{version}/{filepath}#L{line}"}}
             :test {:plugins [[lein-cloverage "1.0.10"]]}})
