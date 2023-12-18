import java.util.*;


public class Graph<X> {
	// Starting node of the graph

	public X startingNode = null;
	Map<String, X> vertices = new HashMap<>();
	Map<X, ArrayList<X>> adjacencyList = new HashMap<>();

	// Check if node/vert is visited with HashSet
	Set<X> visited = new HashSet<>();
	ParenthesizedList hierarchy = new ParenthesizedList();
	Hierarchy parenthesizedList = new Hierarchy();

	// check for cycle
	boolean cycle;
	Set<X> discovered = new HashSet<>();

	public void depthFirstSearch() {
		// Marking cycle flag as false
		cycle = false;
		depthFS(startingNode);
	}

	protected void depthFS(X node) {
		if (discovered.contains(node)) {
			cycle = true;
			hierarchy.cycleDetected();
			parenthesizedList.cycleDetected();
			return;
		}

		// process nodes , add to discovered/visited
		hierarchy.processVertex((Vertex) node);
		parenthesizedList.processVertex((Vertex) node);
		hierarchy.descendVertex((Vertex) node);
		parenthesizedList.descendVertex((Vertex) node);
		discovered.add(node);
		visited.add(node);
		ArrayList<X> list = adjacencyList.get(node);
		if (list != null) {
			for (X y : list)
				depthFS(y);
			}
		hierarchy.ascendVertex((Vertex) node);
		parenthesizedList.ascendVertex((Vertex) node);
		discovered.remove(node);
	}

	public void displayUnreachable(){
		// for entry in array list map entry
		for (Map.Entry<X, ArrayList<X>> entry : adjacencyList.entrySet()) {

			// check each node to see if unreachable
			if(entry.getValue().size()>0){

				// if found mark it to avoid doubling
				if(!visited.contains(entry.getKey())){
					System.out.println(entry.getKey() + " is unreachable");
					visited.add(entry.getKey());
					}
				// loop to check adjacent nodes
				for (X vertex : entry.getValue()){

					if(!visited.contains(vertex)){

						System.out.println(vertex + " is unreachable");
						visited.add(vertex);
					}
				}
			}
		}
	}
}
