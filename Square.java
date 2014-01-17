public class Square {
	public static void main(String[] args){
	/*	Square[][] a = new Square[6][6];
		//System.out.println(a.length);
		Square cat = new Square();
		cat.setBomb();
		a[3][3] = cat;
		System.out.println(a[3][3].getBomb());
		*/
		
	}
	
	private int num; 
	private boolean bomb;
	private boolean flag;
	private boolean revealState;
	
	public Square(){
		num = 0;
		bomb = false;
		flag = false;
		revealState = false;
	}
	
	/*public Square(int numeral, boolean maybe){
		setNum(numeral);
		setBomb(maybe);
	}*/
	
	public void setNum(int a) {
		num = a;
	}
	public int getNum() {
		return num;
	}
	public void setBomb() {
		bomb = true;
	}
	
	public void setFlag(boolean a) {
		flag = a;
	}
	
	public boolean getBomb() {
		//System.out.println("IT CAME!");
		return bomb;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void setReveal(boolean h){
		revealState = h;
	}
	
	public boolean getReveal(){
		return revealState;
	}
}