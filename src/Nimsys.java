import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;


class Nimsys {

	Nimsys(){
		this.setPlayersSet( new HashMap<String, NimPlayer>() );
		globalIn = new Scanner( System.in );
	
	}
	
	
	private HashMap<String, NimPlayer> playersSet;

	private Scanner globalIn;
	
	public void run() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		String inString, functionName, arguments;
	
		
		System.out.println("Welcom to Nis");
		//int cc = 0;
		while(true){
			System.out.print("\n$");
			//cc += 1;
			inString = globalIn.nextLine();
			
			
			if(inString.length() > 0){
				
				String[] parseString = inString.split(" ");
				
				functionName = parseString[0];
				if(functionName.equals("exit"))
					break;
				
				arguments = null;
				try {		
					Method runMethod = this.getClass().getMethod(functionName, String.class);
					
					if(parseString.length == 2)
						arguments = parseString[1];
					runMethod.invoke(this, arguments);
					
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			
		}
		
		
	}
	

	public void addplayer(String arguments){
	
		String[] nameInput = arguments.split(",");
		if(nameInput.length != 3)
			return;
		String userName = nameInput[0];
		String familyName = nameInput[1];
		String giveName = nameInput[2];
		
		this.addPlayer(userName, familyName, giveName);
	}
	
	private boolean addPlayer(String userName, String familyName, String giveName){
		

		
		NimPlayer newPlayer = null; 
		
		if( this.playersSet.get(userName) == null ){
			newPlayer = new NimPlayer(userName, familyName, giveName);
			this.playersSet.put(userName, newPlayer);

			return true;
		}
		else
			System.out.println("The player already exists.");
		return false;
	}
	
	public void removeplayer(String arguments){
		String userName = null;
		String inString;
		if(arguments == null){
			System.out.println("Are you sure you want to remove all players? (y/n)");
			
			inString = globalIn.nextLine();

			if(inString.equals("y")){
				this.removeAllPlayer();
			}
			else if(inString.equals("n"))
				return;
		}
		else{
			userName = arguments;
			this.removePlayer(userName);
		}
	}
	
	private boolean removePlayer(String userName){
		if(this.playersSet.get(userName) != null){
			this.playersSet.remove(userName);
			return true;
		}
		System.out.println("The player does not exist.");
		
		return false;
	}
	
	private boolean removeAllPlayer(){
		this.playersSet.clear();
		return true;
	}
	
	public void editplayer(String arguments){
		String[] nameInput = arguments.split(",");
		if(nameInput.length != 3)
			return;
		String userName = nameInput[0];
		String familyName = nameInput[1];
		String giveName = nameInput[2];
		
		this.editPlayer(userName, familyName, giveName);
		
	}
	
	private boolean editPlayer(String userName, String familyName, String giveName){
		NimPlayer tmpPlayer = null;
		tmpPlayer = this.playersSet.get(userName);
		if( tmpPlayer != null ){
			tmpPlayer.setFamilyName(familyName);
			tmpPlayer.setGiveName(giveName);
			
			return true;
		}
		
			
		System.out.println("The player does not exist.");
		return false;
	}
	
	public void resetstats(String arguments){
		String userName = null;
		String inString;
		if(arguments == null){
			System.out.println("Are you sure you want to reset all players? (y/n)");
			
			inString = globalIn.nextLine();

			if(inString.equals("y")){
				this.resetAllPlayer();
			}
			else if(inString.equals("n"))
				return;
		}
		else{
			userName = arguments;
			this.resetPlayer(userName);
		}
	}
	
	private boolean resetPlayer(String userName){
		NimPlayer tmpPlayer = null;
		tmpPlayer = this.playersSet.get(userName);
		if( tmpPlayer != null ){
			tmpPlayer.setnGamePlayed(0);
			tmpPlayer.setnGameWon(0);
			
			return true;
		}
		
		System.out.println("The player does not exist.");
		return false;
		
	}
	
	private boolean resetAllPlayer(){
		Set<String> set = playersSet.keySet();   
		NimPlayer tmpPlayer = null;
		for (String s:set) {
			tmpPlayer = this.playersSet.get(s);
			tmpPlayer.setnGamePlayed(0);
			tmpPlayer.setnGameWon(0);
		} 
		return true;
		
	}
	
	public void displayplayer(String arguments){
		String userName = null;

		if(arguments == null){
			this.displayAllPlayer();
		}
		else{
			userName = arguments;
			this.displayPlayer(userName);
		}
	}
	
	private boolean displayPlayer(String userName){
		NimPlayer tmpPlayer = null;
		tmpPlayer = this.playersSet.get(userName);
		if( tmpPlayer != null ){
			tmpPlayer.display();
			
			return true;
		}
		
		System.out.println("The player does not exist.");
		return false;
	}
	
	private boolean displayAllPlayer(){
	
	    Object[] key_arr = this.playersSet.keySet().toArray();     
	    Arrays.sort(key_arr);     
	    for  (Object key : key_arr) {     
	        NimPlayer value = playersSet.get(key);
	        value.display();
	    }   
		return true;
	}
	
	
	public void rankings(String arguments){
		String order = arguments;
		if( order != null && order.equals("asc")){
			System.out.println("asc");
			this.rankings(false);
		}
		else{
			System.out.println("desc");
			this.rankings(true);
		}
	}
	
	private void rankings( boolean desc){
		ArrayList<NimPlayer> sortLst = new ArrayList<NimPlayer>(this.playersSet.values());
		
		if( desc){
			Collections.sort(sortLst, new Comparator<NimPlayer>(){
		    		  @Override
		              public int compare(NimPlayer p1, NimPlayer p2){
		          
		              int p1Radio = p1.getRatio();
		              int p2Radio = p2.getRatio();
		              // desc
		              if( p1Radio < p2Radio)
		            	  return 1;
		              else if( p1Radio == p2Radio){
		            	  return p1.getUserName().compareTo(p2.getUserName());
		              }
		              else
		            	  return -1;
		    		}}
		        );
		
		}
		else{
			Collections.sort(sortLst, new Comparator<NimPlayer>(){
	    		  @Override
	              public int compare(NimPlayer p1, NimPlayer p2){
	          
	              int p1Radio = p1.getRatio();
	              int p2Radio = p2.getRatio();
	              // desc
	              if( p1Radio > p2Radio)
	            	  return 1;
	              else if( p1Radio == p2Radio){
	            	  return p1.getUserName().compareTo(p2.getUserName());
	              }
	              else
	            	  return -1;
	    		}}
	        );
			
			
		}
		
		for( int i = 0; i < sortLst.size() && i < 10; i++){
			NimPlayer tmpP = sortLst.get(i);
			String stringRatio = String.valueOf(tmpP.getRatio()) + "%";
			System.out.printf("%-5s| %02d games | %s\n",stringRatio ,tmpP.getnGamePlayed(), tmpP.displayFullName());
		}
		
		
	}
	
	
	public void startgame(String arguments){
		
		String[] nameInput = arguments.split(",");
		
		int nStone = Integer.valueOf( nameInput[0]).intValue();
		int upperBound = Integer.valueOf( nameInput[1]).intValue();
		
		NimPlayer p1 = this.getPlayersSet().get( nameInput[2]);
		NimPlayer p2 = this.getPlayersSet().get( nameInput[3]);
		
		if(p1 == null || p2 == null){
			System.out.println("One of the players does not exist.");
			return;
		}
		else
			this.startGame(nStone, upperBound, p1, p2, this.globalIn);
		
		
	}
	
	public boolean startGame(int nStone, int upperBound, NimPlayer p1, NimPlayer p2, Scanner in){
		NimGame gameCur;
		
		
		System.out.println("Initial stone count: " + nStone);
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + p1.displayFullName());
		System.out.println("Player 2: " + p2.displayFullName());	
		

		
		
		gameCur = NimGame.getInstance();
		gameCur.iniNimGame(nStone, upperBound, p1, p2, in);
		
		gameCur.gameStart();
		
		return true;
		
	}
	
	
	
	
	
	public HashMap<String, NimPlayer> getPlayersSet() {
		return this.playersSet;
	}
	public void setPlayersSet(HashMap<String, NimPlayer> hashMap) {
		this.playersSet = hashMap;
	}

	
	
	
}
