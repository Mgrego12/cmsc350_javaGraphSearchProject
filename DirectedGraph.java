import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DirectedGraph extends Graph<Vertex> {
	// addEdge pass 2 strings, create new array if null.
	// otherwise add vertex, update list.

	private Set<Vertex> visitedNodes = new HashSet<>();

	public void addEdge(String x, String y) {
		ArrayList<Vertex> vList = adjacencyList.get(getVert(x));
		if (vList == null) {
			vList = new ArrayList<>();
		}
		vList.add(getVert(y));
		adjacencyList.put(getVert(x), vList);
	}

	// getVertex pass string to map to correct vertex
	public Vertex getVert(String v) {
		if (!vertices.containsKey(v)) {
			vertices.put(v, new Vertex(v));
		}
		return vertices.get(v);
	}

	   public void depthFirstSearch() {
        // Clear the set of visited nodes before each DFS
        visitedNodes.clear();
        cycle = false;
        depthFS(startingNode);
    }

    // Check for unreachable nodes after DFS is complete
    public List<Vertex> findUnreachableNodes() {
        List<Vertex> unreachableNodes = new ArrayList<>();
        for (Vertex vertex : vertices.values()) {
            if (!visitedNodes.contains(vertex)) {
                unreachableNodes.add(vertex);
            }
        }
        return unreachableNodes;
    }
}
