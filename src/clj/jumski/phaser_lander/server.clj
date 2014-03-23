(ns jumski.phaser_lander.server
  (:require [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [net.cgrand.enlive-html :as enlive]
            [compojure.route :refer (resources)]
            [compojure.core :refer (GET defroutes)]
            ring.adapter.jetty
            [ring.middleware.file :refer (wrap-file)]
            [clojure.java.io :as io]))

(enlive/deftemplate page
  (io/resource "index.html")
  []
  [:body] (enlive/append
            (enlive/html [:script (browser-connected-repl-js)])))

(defroutes site
  (resources "/")
  (GET "/*" req (page)))

(def wrapped-site (wrap-file site "resources/public"))

(defn run
  []
  (defonce ^:private server
    (ring.adapter.jetty/run-jetty #'wrapped-site {:port 8080 :join? false}))
  server)
