import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;



public class NimGame{

	
	private int curStoneCount;
	private int upperBoundStoneRemoval;
	private List<NimPlayer> players;
	private Scanner gameIn;
	
	private NimGame(){}
	
	
	public void iniNimGame(int nStone, int upperBound, NimPlayer p1, NimPlayer p2, Scanner in){
		this.setCurStoneCount(nStone);
		this.setUpperBoundStoneRemoval(upperBound);
		this.setPlayers(new ArrayList<NimPlayer>() );

		this.getPlayers().add(p1);
		this.getPlayers().add(p2);

		this.gameIn = in;
	}
	
	public void gameStart(){
		int playerIdx = 0;
		while(true){
			this.displayStoneLeft();
			if( this.playerTurn(playerIdx) ){
				playerIdx = (playerIdx + 1)%2;
				
				if( this.getCurStoneCount() == 0){
					this.finishGame(playerIdx);
					break;
				}
					
			}
		}

		
	}
	
	private void finishGame(int winIdx){
		NimPlayer winner = this.getPlayers().get(winIdx);
		
		// we should print the finish result
		
		System.out.println("Game Over");
		System.out.println(winner.displayFullName() + " wins!");
		
		winner.oneGameFin(true);
		
		int lostIdx = (winIdx + 1)%2;
		
		this.players.get(lostIdx).oneGameFin(false);
		
	}
	
	
	private void displayStoneLeft(){
		int n = this.getCurStoneCount();
		System.out.print("\n" + n + " stone left:");
		for( int i = 0; i < n; i++ )
			System.out.print(" *");
		System.out.print("\n");
	}
	
	
	private boolean playerTurn(int playerIdx){
		String displayName = this.players.get(playerIdx).getFamilyName();
		
		System.out.println(displayName + "'s turn - remove how many?");
		
		int nremov;
		nremov = this.gameIn.nextInt();
		this.gameIn.nextLine();
		return this.removeStone(nremov);
	}
	
	
	private boolean removeStone(int nRemov){
		int limit = Math.min( this.getUpperBoundStoneRemoval(), this.getCurStoneCount() );
		if( nRemov >= 1 && nRemov <= limit ){
			this.curStoneCount -= nRemov;
			return true;
		}
		System.out.println("Invalid move. You must remove between 1 and "+ limit +" stones.");
		return false;
	}
	
	
	
	
	
	
	private static class NimGameSingetonHoler{
		static NimGame instance = new NimGame();
	}

	public static NimGame getInstance(){
		return NimGameSingetonHoler.instance;
	}
	
	
	
	public int getCurStoneCount() {
		return curStoneCount;
	}
	public void setCurStoneCount(int curStoneCount) {
		this.curStoneCount = curStoneCount;
	}
	public int getUpperBoundStoneRemoval() {
		return upperBoundStoneRemoval;
	}
	public void setUpperBoundStoneRemoval(int upperBoundStoneRemoval) {
		this.upperBoundStoneRemoval = upperBoundStoneRemoval;
	}
	public List<NimPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<NimPlayer> players) {
		this.players = players;
	}
	
	
	
}
