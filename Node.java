package assignment;
public class Node {
	private char[] boardPositions = new char[22];
	private int staticEstimate;
	private int estimate;
	public Node() {
		
	}
	public Node(char[] boardPositions, int staticEstimate, int estimate) {
		this.boardPositions = boardPositions;
		this.staticEstimate = staticEstimate;
		this.estimate = estimate;
	}
	
	public char[] getBoardPositions() {
		return this.boardPositions;
	}
	
	public int getStaticEstimate() {
		return this.staticEstimate;
	}
	
	public int getEstimate() {
		return this.estimate;
	}
	
	public void setBoardPositions(char[] boardPositions) {
		this.boardPositions = boardPositions;
	}
	
	public void setStaticEstimate(int staticEstimate) {
		this.staticEstimate = staticEstimate;
	}
	
	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}
}
