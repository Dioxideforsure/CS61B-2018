package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> pq;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        pq = new PriorityQueue<>((o1, o2) -> {
            int h = h(o1) - h(o2);
            if (h != 0) {
                return h;
            }
            return distTo[o1] - distTo[o2];
        });
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs((v - 1) / maze.N() - (t - 1) / maze.N()) + Math.abs((v - 1) % maze.N() - (t - 1) % maze.N());
    }


    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        announce();
        pq.add(s);
        while (!pq.isEmpty()) {
            int v = pq.poll();
            if (v == t) {
                targetFound = true;
                announce();
                return;
            }

            if (!marked[v]){
                for (int w : maze.adj(v)) {
                    if (!marked[w]){
                        announce();
                        marked[v] = true;
                        distTo[w] = distTo[v] + 1;
                        edgeTo[w] = v;
                        pq.add(w);
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

