(ns zoo-algos.core
  (:require [incanter.core :refer [log]]
            [zoo-algos.graph :refer :all]))

(defn correct-p
  [user-answer {:keys [task-answer task-p]}]
  (if (= task-answer user-answer)
    (log task-p)
    (- (log task-p) 1)))

(defn answer-p
  [{:keys [user-p user-answer]}]
  (* user-p user-answer))

(defn sigma-task-p
  [task task-delta]
  (reduce + (map answer-p task-delta)))

(defn sigma-user-p 
  [user user-delta]
  (reduce + (map (partial correct-p (:p user)) user-delta)))

(defn update-task-p
  [graph task-id] 
  (update-p graph [:task task-id] sigma-task-p))

(defn update-user-p
  [graph user-id]
  (update-p graph [:user user-id] sigma-user-p))

(defn tasks-p
  [graph answers]
  (reduce graph update-task-p (task-ids graph)))

(defn users-p
  [graph answers]
  (reduce graph update-task-p (user-ids graph)))

(defn reduce-answers
  [graph]
  (map (tasks graph)))

(defn iterative-reduction
  [assignment-graph answers iterations]
  (loop [i 0
         g (init-user-p assignment-graph)]
    (if (= i iterations)
      (reduce-answers g)
      (recur (+ i 1) (-> (tasks-p g answers)
                         (users-p answers))))))

