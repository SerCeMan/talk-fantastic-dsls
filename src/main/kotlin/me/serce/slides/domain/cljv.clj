; create
(def ts {:payment {:currency "AUD"
                   :amount   15}
         :parts   {:from {:id   0
                          :name "alex"}
                   :to   {:id   1
                          :name "ben"}}})


; update
(def ts2 (assoc-in ts [:parts :from :name] "john"))
