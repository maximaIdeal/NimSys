
class NimPlayer {
	
	private String userName;
	private String giveName;
	private String familyName;
	private int nGamePlayed;
	private int nGameWon;
	private int ratio;
	
	
	NimPlayer(String userName, String familyNmae, String giveName){
		this.setUserName(userName);
		this.setGiveName(giveName);
		this.setFamilyName(familyNmae);
		this.setnGamePlayed(0);
		this.setnGamePlayed(0);
		this.setRatio(0);
		
	}
	
	public void display(){
		System.out.println(this.userName+','+this.giveName+','+this.familyName+','+this.nGamePlayed+" games,"+this.nGameWon+" wins");
		
	}
	
	public String displayFullName(){
		String fullName;
		fullName = this.getGiveName() + " " + this.getFamilyName();
		return fullName;
	}
	
	public void oneGameFin(boolean win){
		this.nGamePlayed += 1;
		if(win == true)
			this.nGameWon += 1;
		this.ratio = this.countRatio();
	}
	
	public int countRatio(){
		double retRatio;
		
		retRatio = Math.round( ((double)this.getnGameWon() / (this.getnGamePlayed()) * 100) );
		
		return (int)retRatio;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGiveName() {
		return giveName;
	}
	public void setGiveName(String giveName) {
		this.giveName = giveName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public int getnGamePlayed() {
		return nGamePlayed;
	}
	public void setnGamePlayed(int nGamePlayed) {
		this.nGamePlayed = nGamePlayed;
	}
	public int getnGameWon() {
		return nGameWon;
	}
	public void setnGameWon(int nGameWon) {
		this.nGameWon = nGameWon;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	
}
