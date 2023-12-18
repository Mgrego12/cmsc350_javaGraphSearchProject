import java.util.LinkedList;
import java.util.Queue;

public class Hierarchy implements DFSActions<Vertex> {

	Queue<String> hier = new LinkedList<>();

	@Override
	public void processVertex(Vertex vertex) {
		hier.add(vertex.toString());
	}

	@Override
	public void descendVertex(Vertex vertex) {
		hier.add("(");
	}

	@Override
	public void ascendVertex(Vertex vertex) {
		hier.add(")");
	}

	@Override
	public void cycleDetected() {
		hier.add("*");
	}

	@Override
	public String toString() {
		String ans = "";
		int size = 0;

		while (hier.size() > 0) {
			String c = hier.peek();
			hier.remove();

			if (c == "(") {
				if (hier.peek() == ")") {
					hier.remove();
					continue;
				} else if (hier.peek() == "*") {
					ans += hier.peek() + " ";
					hier.remove();
					hier.remove();
					continue;
				}
			}
			if (c == "(")
				size++;
			else if (c == ")")
				--size;
			if (c == "(" || c == ")")
				continue;
			if (c != "*")
				ans += "\n";
			for (int i = 0; i < size; i++) {
				ans += "\t";
			}
			ans += c + " ";
		}
		ans += "\n";
		return ans;
	}

	public boolean isPar(String s) {
		return s != "(" && s != ")";
	}
}
