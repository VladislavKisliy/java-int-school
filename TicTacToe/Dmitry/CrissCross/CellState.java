package CrissCross;

public enum CellState {
	TIC("X"),
	TAC("O"),
	TOE(" ");
	
	private String label;
	
	private CellState(String label){
		this.label = label;
	}
	
    public String toString() {
        return label;
    }
    
    
}
