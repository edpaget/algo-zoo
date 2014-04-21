(ns zoo-algos.graph)

(defn task-ids
  [graph]
  (map :id (graph :tasks :all)))

(defn user-ids
  [graph]
  (map :id (graph :users :all)))
 
(defn update-p
  [graph [type id] f]
  (update-in [graph type id] (fn [{:keys [p delta]
                                   {:p (f p delta)
                                    :delta delta}}])))
