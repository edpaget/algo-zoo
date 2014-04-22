(ns zoo-algos.graph
  (:refer-clojure :exclude [get]))

(defprotocol TaskGraph
  (get [this type id])
  (update-p [this [type id] p]))

(defrecord MemGraph [task-map user-map]
  TaskGraph
  (get [this type id] 
    (cond 
      (and (= :task type) (= :all id)) (keys task-map)
      (and (= :user type) (= :all id)) (keys user-map)
      (= :task type) (task-map id)
      (= :user type) (user-map id)))
  (update-p [this [type id] f]
    (update-in this [type id] (fn [{:keys [p delta]}]
                                {:p (f p delta)
                                 :delta delta}))))

(defn task-ids
  [graph]
  (get graph :task :all))

(defn user-ids
  [graph]
  (get graph :user :all))

(defn task
  [graph id]
  (get graph :task id))

(defn user
  [graph id]
  (get graph :user id))
 
