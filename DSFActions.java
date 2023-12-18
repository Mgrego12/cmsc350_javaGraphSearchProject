public interface DFSActions<T> {
	public void processVertex(T vertex);
	public void descendVertex(T vertex);
	public void ascendVertex(T vertex);
	public void cycleDetected();
}
