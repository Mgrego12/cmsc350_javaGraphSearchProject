
/* Matthew Gregorek CMSC 350
 * Project 4 Directed Graphs and Depth First Search
 * Write a program that accepts information contained in a file about the class
 * dependencies in a Java program and creates a directed graph from that 
 * information.
 * From the directed graph, it produces two different kinds of
 * displays of those dependency relationships.
 * 
 * SAMPLE INPUT
 * ==========
 * 	ClassA ClassC ClassE ClassJ
 * 	ClassB ClassD ClassG 
 * 	ClassC ClassA
 * 	ClassE ClassB ClassF ClassH
 * 	ClassJ ClassB
 * 	ClassI ClassC 
 * ======
 */
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
	static DirectedGraph graph = new DirectedGraph();

	public void readGraph() {

		JFileChooser userChoice = new JFileChooser(new File("."));
		int choice = userChoice.showOpenDialog(null);

		if (choice == JFileChooser.APPROVE_OPTION) {// check if valid
			try {
				Scanner input = new Scanner(userChoice.getSelectedFile());
				while (input.hasNextLine()) {// read each line
					String newEdge = input.nextLine(); // save input
					String[] edge = newEdge.split(" "); // split, store []edge

					if (graph.startingNode == null)
						graph.startingNode = graph.getVert(edge[0]);// if starting node, put 0 index

					for (int i = 1; i < edge.length; i++) {
						graph.addEdge(edge[0], edge[i]);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// create new Main to read graph, then start DFS on graph
		// print parenthesizedList and hierarchy
		// display all unreachable nodes
		new Main().readGraph();
		graph.depthFirstSearch();
		System.out.println(graph.parenthesizedList.toString()); // print parenthesizedList
		System.out.println(graph.hierarchy.toString());// print hierarchy

		List<Vertex> unreachableNodes = graph.findUnreachableNodes();

        if (!unreachableNodes.isEmpty()) {
            System.out.println("Unreachable nodes:");

            for (Vertex vertex : unreachableNodes) {
                System.out.println(vertex.toString());
            }

        } else {
            System.out.println("All nodes are reachable.");
        }
    }
}
