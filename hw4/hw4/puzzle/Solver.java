package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
* Shout out to https://github.com/LiqunZhao/cs61b-sp18/blob/master/hw4/hw4/puzzle/Solver.java
* Given me some idea about solution.
* */

public class Solver {
    private Stack<WorldState> bestFirstSearchStack;
    private Map<WorldState, Integer> eDGCache = new HashMap<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
        SearchNode currentNode = new SearchNode(initial, null);
        bestFirstSearchStack = new Stack<>();
        pq.insert(currentNode);
        while (!currentNode.world.isGoal()) {
            Iterable<WorldState> helper = currentNode.world.neighbors();
            for (WorldState state : helper) {
                if (currentNode.prev == null || !currentNode.prev.world.equals(state)){
                    pq.insert(new SearchNode(state, currentNode));
                }
            }
            currentNode = pq.delMin();
        }
        for (SearchNode Node = currentNode; Node != null; Node = Node.prev) {
            bestFirstSearchStack.push(Node.world);
        }
    }
    public int moves() {
        return bestFirstSearchStack.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return bestFirstSearchStack;
    }

    private class SearchNode{
        private WorldState world;
        private int moves;
        private SearchNode prev;
        private int priority;
        public SearchNode(WorldState world, SearchNode prev) {
            this.world = world;
            if (prev == null) {
                this.moves = 0;
            } else {
                this.moves = prev.moves + 1;
            }
            if (eDGCache.containsKey(this.world)) {
                priority = moves + eDGCache.get(this.world);
            } else {
                int edG = this.world.estimatedDistanceToGoal();
                priority = moves + edG;
                eDGCache.put(this.world, edG);
            }
            this.prev = prev;
        }

    }
    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return o1.priority - o2.priority;
        }
    }
}
