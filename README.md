# ring-data.json

[![Travis CI Build Status](https://travis-ci.org/chbrown/ring-data.json.svg)](https://travis-ci.org/chbrown/ring-data.json)
[![Coveralls Coverage Status](https://coveralls.io/repos/chbrown/ring-data.json/badge.svg)](https://coveralls.io/github/chbrown/ring-data.json)
[![Clojars Project](https://img.shields.io/clojars/v/chbrown/ring-data.json.svg)](https://clojars.org/chbrown/ring-data.json)

This module replicates [`ring-clojure/ring-json`](https://github.com/ring-clojure/ring-json) using [`org.clojure/data.json`](https://github.com/clojure/data.json) instead of [Cheshire](https://github.com/dakrone/cheshire), with some simplifications.

    (ns ...
      (:require [ring.middleware.data.json :refer [wrap-json-request
                                                   wrap-json-response]]))

    (def handler
      (-> router
          (wrap-json-request :key-fn keyword)
          (wrap-json-response :escape-unicode false)))


## License

Copyright © 2017 Christopher Brown. [Eclipse Public License](https://www.eclipse.org/legal/epl-v10.html).
