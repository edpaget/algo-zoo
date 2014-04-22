(ns zoo-algos.core
  (:refer-clojure :exclude [get])
  (:require [zoo-algos.graph :refer :all]))

(defn answer-p
  [{:keys [p answer]}]
  (* p answer))

(defn sigma-task-p
  [task task-delta]
  (reduce + (pmap answer-p task-delta)))

(defn sigma-user-p 
  [user user-delta]
  (reduce + (pmap answer-p user-delta)))

(defn update-task-p
  [graph task-id] 
  (update-p graph [:task task-id] sigma-task-p))

(defn update-user-p
  [graph user-id]
  (update-p graph [:user user-id] sigma-user-p))

(defn tasks-p
  [graph]
  (reduce update-task-p graph (task-ids graph)))

(defn users-p
  [graph]
  (reduce update-task-p graph (user-ids graph)))

(defn reduce-answers
  [graph]
  (pmap (fn [id]
          (let [{:keys [id p]} (task graph id)] 
            (if (> 0 p) 
              {:id id :answer 1} 
              {:id id :answer -1})))
        (task-ids (tasks-p graph))))

(defn init-user-p
  [graph]
  (reduce #(update-p %1 [:user %2] (fn [& args] (rand))) graph (user-ids graph)))

(defn iterative-reduction
  [assignment-graph iterations]
  (loop [i 0
         g (init-user-p assignment-graph)]
    (println "Iteration: " (+ 1 i) " of " iterations)
    (if (= i iterations)
      (reduce-answers g)
      (recur (+ i 1) (-> (tasks-p g)
                         users-p)))))

