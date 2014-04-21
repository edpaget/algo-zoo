(ns zoo-algos.graph)

(defn task-ids
  [graph]
  (graph :task :all))

(defn user-ids
  [graph]
  (graph :user :all))

(defn task
  [graph id]
  (graph :task id))

(defn user
  [graph id]
  (graph :user id))

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
