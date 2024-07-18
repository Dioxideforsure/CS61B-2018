package lab11.graphs;


import java.util.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private Stack<Integer> cycleStack;

    public MazeCycles(Maze m) {
        super(m);
        cycleStack = new Stack<>();
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        cycle();
    }

    private void cycle() {
        int initial = 0;
        edgeTo[initial] = initial;
        distTo[initial] = initial;
        marked[initial] = true;
        cycleStack.push(initial);
        while (!cycleStack.isEmpty()) {
            int v = cycleStack.pop();
            for (int w : maze.adj(v)) {
                if (!marked[w]){
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    announce();
                    cycleStack.push(w);
                } else if (w != edgeTo[v]) {
                    announce();
                    return;
                }
            }
        }
    }

    // Helper methods go here
}

