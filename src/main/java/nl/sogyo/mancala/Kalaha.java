package nl.sogyo.mancala;

public class Kalaha extends Field {
	
	public Kalaha(int s, Player p) {
		super(p);
		addStones(s);
	}
	
	public void play() {}
	
	public void getOnePassRest(int passStones) {

		if(getPlayer().getTurn()) {
			super.getOnePassRest(passStones);
			if(passStones == 1) {
				if(!hasMovesLeft(getPlayer(), this))
					endGame();
			}
		} else {
			getNeighbor().getOnePassRest(passStones);
		}
	}
	
	public void continueChain(int length, int maxLength, Hole firstHole) {
		if(getPlayer().equals(firstHole.getPlayer())) {
			setNeighbor(new Hole(4, firstHole.getPlayer().getNemesis()));
			getNeighbor().continueChain(++length, maxLength, firstHole);
		} else {
			setNeighbor(firstHole);
		}	
	}
	
	public Field getOpposite() {
		return this;
	}
	
	public Kalaha getKalaha() {
		return this;
	}

	public void sweep() {}
	
	public boolean hasMovesLeft(Player p, Field initialField) {
		if(this.getNeighbor().equals(initialField)){
			return false;
		}
		return getNeighbor().hasMovesLeft(p, initialField);
	}
	
	public void print() {
		System.out.print("[" + getStones() + "]");
	}
}
