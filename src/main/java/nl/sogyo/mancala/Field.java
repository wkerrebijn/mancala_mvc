package nl.sogyo.mancala;

abstract public class Field {

	private int stones;
	private Field neighbor;
	private Player player;
	
	public Field(Player p) {
		player = p;
	}
	
	public int getStones() {
		return stones;
	}
	
	public void addStones(int s) {
		stones += s;
	}
	
	public void resetStones() {
		stones = 0;
	}
	
	public Field getNeighbor() {
		return neighbor;
	}
	
	public void setNeighbor(Field n) {
		neighbor = n;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	abstract public void play();
	
	public void getOnePassRest(int passStones) {
		if(passStones > 0) {
			stones++;
			if(passStones > 1) {
				neighbor.getOnePassRest(passStones-1);
			}		
		} 
	}
		
	abstract public void continueChain(int length, int maxLength, Hole firstHole);
	
	abstract public Field getOpposite();
	
	abstract public Kalaha getKalaha();
	
//	public Kalaha getKalahaByPlayer(Player player) {
//		return player.equals(getKalaha().getPlayer()) ?
//				getKalaha() :
//				getKalaha().getNeighbor().getKalaha();
//	}
	
	public void endTurn() {
		Field initialField = this;
		if(!hasMovesLeft(player.switchTurn(), initialField)) {
			endGame();
		}
	}
	
	public void endGame() {
//		getKalaha().getNeighbor().getKalaha().getNeighbor().sweep();
		getKalaha().getNeighbor().sweep();

		determineWinner();
	}
	
	public String determineWinner() {
		Kalaha kalaha1 = getKalaha();
		Kalaha kalaha2 = getKalaha().getNeighbor().getKalaha();

		if(kalaha1.getStones() == kalaha2.getStones()) {
			return "Both";
		} else {
			return kalaha1.getStones() < kalaha2.getStones() ?
					kalaha2.getPlayer().getName() :
					kalaha1.getPlayer().getName();
		}
	}
	
	abstract public void sweep();
	
	abstract public boolean hasMovesLeft(Player p, Field initialField);
		
	abstract public void print();
	
	public void printFirst() {
		print();
		System.out.print(" -> ");
		neighbor.printChain(this);
	}
	
	public void printChain(Field first) {
		if(!this.equals(first)) {
			print();
			System.out.print(" -> ");
			neighbor.printChain(first);
		} else {
			System.out.println();
		}
	}
}
