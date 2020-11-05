import java.util.*;

public class Graph {

    private ArrayList<Item> nodes;
    private Hashtable<Item, ArrayList<Item>> neighbors;
    private Hashtable<Edge, Integer> weights;

    Graph(
            ArrayList<Item> nodes,
            Hashtable<Item, ArrayList<Item>> neighbors,
            Hashtable<Edge, Integer> weights) {
        this.nodes = nodes;
        this.neighbors = neighbors;
        this.weights = weights;
    }

    // -----

    ArrayList<Item> getNodes() {
        return nodes;
    }

    Hashtable<Item, ArrayList<Item>> getNeighbors() {
        return neighbors;
    }

    Hashtable<Edge, Integer> getWeights() {
        return weights;
    }

    // -----
    // Computes all shortest paths from a given node
    // Nodes are marked with the shortest path to the source

    void allShortestPaths(Item source) {
        // TODO

        WeakHeap one = new WeakHeap();

        for (Item three : this.nodes) {
            three.reset();
            three.setValue(Integer.MAX_VALUE);
            three.setVisited(false);
            one.insert(three);
        }
        one.updateKey(source.getPosition(), 0);

        Item currentP = source;
        ArrayList<Item> neighToCP = new ArrayList<>();

        while (!one.isEmpty()) {
            currentP = one.extractMin();

            if(!currentP.isVisited()) {
                currentP.setVisited(true);
                neighToCP = this.neighbors.get(currentP);

                for (Item neigh: neighToCP) {
                    int CD;
                    if (currentP.getValue() == Integer.MAX_VALUE) {
                        CD = this.weights.get(new Edge(currentP, neigh));
                    } else {
                        CD = currentP.getValue() + this.weights.get(new Edge(currentP, neigh));
                    }
                    if (CD < neigh.getValue()) {
                        neigh.setPrevious(currentP);
                        one.updateKey(neigh.getPosition(), CD); //error about assertion error
                    }
                }
            }
        }
    }

    // -----
    // Point-to-point shortest path; stops as soon as it reaches destination

    ArrayList<Edge> shortestPath(Item source, Item dest) {
        // TODO
        WeakHeap one = new WeakHeap();

        for (Item nodes : this.nodes) {
            nodes. reset();
            nodes.setValue(Integer.MAX_VALUE);
            nodes.setVisited(false);
            one.insert(nodes);
        }
        one.updateKey(source.getPosition(), 0);

        Item currentP = source;
        ArrayList<Item> neighToCP = new ArrayList<>();

        while(!one.isEmpty()) {
            currentP = one.extractMin();
            if (currentP.equals(dest)) {
                break;
            } else {
                    currentP.setVisited(true);
                    neighToCP = neighbors.get(currentP);

                    for (Item nodes : neighToCP) {
                        if (!nodes.isVisited()) {
                            int CD;
                            if (currentP.getValue() == Integer.MAX_VALUE) {
                                CD = this.weights.get(new Edge(currentP, nodes));
                            } else {
                                CD = currentP.getValue() + this.weights.get(new Edge(currentP, nodes));
                            }
                            if (CD < nodes.getValue()) {
                                nodes.setPrevious(currentP);
                                one.updateKey(nodes.getPosition(), CD); // assertion error
                            }
                        }

                    }

            }
        }
        return Item.pathToSource(dest);
    }

    // -----

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Nodes:%n%s%n", nodes));
        res.append(String.format("Neighbors:%n%s%n", neighbors));
        res.append(String.format("Weights:%n%s%n", weights));
        return new String(res);
    }
}
