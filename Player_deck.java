public class Player_deck
{
	String[] ranks = {"E","S","W","N","O","G","R"};
	int[] furiten = {0,0,0,0,0,0,0};
	int[] number = {0,0,0,0,0,0,0};
	String thrown_cards = "";
	int score = 15;
	int ANTRIP=0,KAN=0,DRAG=0,WIND=0,ORDI=0,TOITU=0,GOKUSI=0;
	public Player_deck(){}
	public void reset()
	{
		for (int i=0; i<7; i++)
		{
			furiten[i]=0;
			number[i]=0;
		}
		ANTRIP=0; KAN=0; DRAG=0; WIND=0; ORDI=0; TOITU=0; GOKUSI=0;
		thrown_cards="";
	}
	public void scorereset() { score = 15; }
	public int scoreget() { return score; }
	public void scorechange(int gain) { score +=gain; }
	public void scorestore(int gain) { score =gain; }
	public void pick(String code)
	{
		for (int i=0; i<7; i++)
		{
			if (ranks[i].equals(code))
			{
				number[i]++;
				break;
			}
		}
	}
	public void called() { thrown_cards+="!"; }
	public void discard(String code) throws Invalidcards
	{
		boolean done=false;
		for (int i=0; i<7; i++)
		{
			if (ranks[i].equals(code))
			{
				done=true;
				if (number[i]<1||number[i]>4)
				{
					throw new Invalidcards(); 
				}
				else
				{
					number[i]--;
					furiten[i]++;
					thrown_cards+=ranks[i];
					break;
				}
			}
		}
		if (done==false) { throw new Invalidcards(); }
	}
	public char lastthrow() {return thrown_cards.charAt(thrown_cards.length()-1);}
	public String throwns() {return ("Player's discard list : "+thrown_cards);}
	public String getinfo(boolean tsumo, String code)
	{
		String Info="";
		int tsumonow = 0;
		if (tsumo==true)
		{		
			for (int i=0; i<7; i++)
			{
				if (ranks[i].equals(code))
				{
					tsumonow=i;
					number[i]--;
					break;
				}
			}
		}
		String Ordinary="", Pons="", Kans="";
		for (int i=0; i<7; i++)
		{
			if (number[i]>=1&&number[i]<=4)
			{
				for (int j=0; j<number[i];j++) {Ordinary+=ranks[i];}
			}
			else if (number[i]==5) {Pons+=ranks[i]+ranks[i]+"+"+ranks[i]+" ";}
			else if (number[i]==6) {Kans+=ranks[i]+ranks[i]+"+"+ranks[i]+"+"+ranks[i]+" ";}
			else if (number[i]==7) {Kans+=ranks[i]+ranks[i]+ranks[i]+ranks[i]+" ";}
			else if (number[i]==8) {Kans+=ranks[i]+ranks[i]+ranks[i]+"+"+ranks[i]+" ";}
		}
		Info += "Player's Hand : " + Ordinary;
		if (tsumo==true)
		{
			number[tsumonow]++;
			Info += " (This turn get : " + code + ")";
		}
		if (Pons!="") { Info += " / Pons : " + Pons; }
		if (Kans!="") { Info += " / Kans : " + Kans; }
		return Info;
	}
	public boolean wincheck(boolean tsumo, String code)
	{
		int pair=0, trip=0, antrip=0, inkans=0, outkans=0, winds=0, dragons=0; 
		int roncheck=0;
		ANTRIP=0; KAN=0; DRAG=0; WIND=0; ORDI=0; TOITU=0; GOKUSI=0;
		for (int i=0; i<7; i++)
		{
			if (ranks[i].equals(code))
			{
				if (tsumo==false) {number[i]++;}
				roncheck=i;
				break;
			}
		}
		for (int i=0; i<7; i++)
		{
			if (number[i]>=3)
			{
				if (i<=3) {winds+=2;}
				else {dragons+=2;}
				if (number[i]==3) {antrip++;}
				else if (number[i]==6||number[i]==8) {outkans++;}
				else if (number[i]==7) {inkans++;}
				trip++;
			}
			else if (number[i]==2)
			{
				if (i<=3) {winds++;}
				else {dragons++;}
				pair++;
			}
		}
		if (tsumo==false) {number[roncheck]--;}
		
		if (tsumo==true)
		{
			if (trip==2&&pair==1)
			{
				if (dragons<4&&winds<4&&antrip==0&&inkans==0&&(outkans+inkans)<2) {return false;}
				else 
				{
					ORDI=1;
					if (dragons==5) {DRAG=2;}
					else if (dragons==4) {DRAG=1;}
					else if (winds==5) {WIND=2;}
					else if (winds==4) {WIND=1;}
					if (antrip!=0) {ANTRIP=antrip;}
					if (inkans==2) {KAN=4;}
					else if (inkans==1&&outkans==1) {KAN=3;}
					else if (outkans==2) {KAN=2;}
					else if (inkans==1) {KAN=1;}
					return true;
				}
			}
			else if (pair==4)
			{
				TOITU=1;
				if (winds==4) WIND=1;
				else if (dragons==3) DRAG=1;
				return true;
			}
			else if (trip==0&&pair==1)
			{
				if (number[roncheck]==2)
				{
					if (thrown_cards.equals("")==true) {GOKUSI=3;}
					else {GOKUSI=2;}
				}
				else {GOKUSI=1;}
				return true;
			}
			else {return false;}
		}
		else
		{
			if (trip==2&&pair==1)
			{
				if (furiten[roncheck]==0)
				{
					if (number[roncheck]==2) {antrip--;}
					if (dragons<4&&winds<4&&antrip==0&&inkans==0&&(outkans+inkans)<2) {return false;}
					else 
					{
						ORDI=1;
						if (dragons==5) {DRAG=2;}
						else if (dragons==4) {DRAG=1;}
						else if (winds==5) {WIND=2;}
						else if (winds==4) {WIND=1;}
						if (antrip!=0) {ANTRIP=antrip;}
						if (inkans==2) {KAN=4;}
						else if (inkans==1&&outkans==1) {KAN=3;}
						else if (outkans==2) {KAN=2;}
						else if (inkans==1) {KAN=1;}
						return true;
					}
				}
				else {return false;}
			}
			else if (pair==4)
			{
				if (furiten[roncheck]==0)
				{
					TOITU=1;
					if (winds==4) WIND=1;
					else if (dragons==3) DRAG=1;
					return true;
				}
				else {return false;}
			}
			else if (trip==0&&pair==1)
			{
				if (number[roncheck]==1&&thrown_cards.equals("")==true)
				{
					GOKUSI=3;
					return true;
				}
				else if (number[roncheck]==0&&furiten[roncheck]==0)
				{
					GOKUSI=1;
					return true;
				}
				else {return false;}
			}
			else {return false;}
		}
	}
	public int scoreprint(int num, boolean tsumo, boolean flower, boolean chang)
	{
		scoreprint prter = new scoreprint();
		return prter.calculator(ANTRIP,KAN,DRAG,WIND,ORDI,TOITU,GOKUSI,num,tsumo,flower,chang);
	}
	public boolean poncheck(String code)
	{
		boolean able=false;
		for (int i=0; i<7; i++)
		{
			if (ranks[i].equals(code))
			{
				if (number[i]==2) {able=true;}
				else {able=false;}
				break;
			}
		}
		return able;
	}
	public boolean kancheck(String code)
	{
		boolean able=false;
		for (int i=0; i<7; i++)
		{
			if (ranks[i].equals(code))
			{
				if (number[i]==3) {able=true;}
				else {able=false;}
				break;
			}
		}
		return able;
	}
	public boolean sidekancheck(String code)
	{
		boolean able=false;
		for (int i=0; i<7; i++)
		{
			if (ranks[i].equals(code))
			{
				if (number[i]==6) {able=true;}
				else {able=false;}
				break;
			}
		}
		return able;
	}
	public String ankancheck()
	{
		String able="";
		for (int i=0; i<7; i++)
		{
			if (number[i]>=4) {able=ranks[i]; break;}
		}
		return able;
	}
	public void pon(String code)
	{
		for (int i=0; i<7; i++)
		{
			if (ranks[i].equals(code))
			{
				number[i]=5;
				break;
			}
		}
	}
	public void kan(String code)
	{
		for (int i=0; i<7; i++)
		{
			if (ranks[i].equals(code))
			{
				if (number[i]==3) {number[i]=8;}
				else if (number[i]==4) {number[i]=7;}
				else {number[i]=6;}
				break;
			}
		}
	}
}