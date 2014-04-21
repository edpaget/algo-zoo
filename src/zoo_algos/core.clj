(ns zoo-algos.core)

(defn update-p
  [graph [type id] f]
  (update-in [graph type id] (fn [{:keys [p delta]
                                   {:p (f [p delta])
                                    :delta delta}}])))

(defn correct-prob
  [[task-answer user-answer task-p]]
  )

(defn log-likelihood
  [[task-p task-answer user-answer]]
  )

(defn sigma-task-p
  [answers [task users]]
  (reduce + (map (comp log-likelihood (partial answer :task task)) users)))

(defn sigma-user-p 
  [answers [user tasks]]
  (reduce + (map (comp correct-prob (partial answer :user user)) tasks)))

(defn update-task-p
  [answers graph task-id] 
  (update-p graph [:task task-id] (partial sigma-task-p answers)))

(defn update-user-p
  [answers graph user-id]
  (update-p graph [:user user-id] (partial sigma-user-p answers)))

(defn tasks-p
  [graph answers]
  (reduce graph update-task-p (tasks answers)))

(defn users-p
  [graph answers]
  (reduce graph update-task-p (tasks answers)))

(defn iterative-reduction
  [assignment-graph answers iterations]
  (loop [i 0
         g (init-user-p assignment-graph)]
    (if (= i iterations)
      (reduce-answers g)
      (recur (+ i 1) (-> (tasks-p g answers)
                         (users-p answers))))))

