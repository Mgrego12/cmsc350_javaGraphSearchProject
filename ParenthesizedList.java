import java.util.LinkedList;
import java.util.Queue;

public class ParenthesizedList implements DFSActions<Vertex> {
	Queue<String> que = new LinkedList<>();

	// methods to add parenthesis , stars, to correspond with DFSActions
	@Override
	public void processVertex(Vertex vertex) {
		que.add(vertex.toString());
	}

	@Override
	public void descendVertex(Vertex vertex) {
		que.add("(");
	}

	@Override
	public void ascendVertex(Vertex vertex) {
		que.add(")");
	}

	@Override
	public void cycleDetected() {
		que.add("*");
	}

	@Override
	public String toString() {
		String ans = " ";
		ans += "( ";
		while (que.size() > 0) {
			String c = que.peek();
			que.remove();
			if (c == "(") {
				if (que.peek() == ")") {
					que.remove();
					continue;
				} else if (que.peek() == "*") {
					ans += que.peek() + " ";
					que.remove();
					que.remove();
					continue;
				}
			}
			ans += c + " ";
		}
		ans += " )\n";
		return ans;
	}
}
