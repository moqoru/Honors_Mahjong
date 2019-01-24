public class scoreprint
{
	public scoreprint() {}
	public int calculator(int ANTRIP,int KAN,int DRAG,int WIND,int ORDI,int TOITU,int GOKUSI,int NUM,boolean TSUMO,boolean FLOWER,boolean CHANG)
	{
		int score=0;
		if (ORDI==1)
		{
			if (ANTRIP==1) { System.out.println("- One closed triplet : 1 point"); score++; }
			else if (ANTRIP==2) { System.out.println("- Two closed triplets : 3 points"); score+=3; }
			if (KAN==1||KAN==3) {System.out.println("- One closed kan : 2 points"); score+=2; }
			if (KAN==2||KAN==3) { System.out.println("- Two kans : 3 points"); score+=3; }
			if (KAN==4) {System.out.println("- Two closed kans : 5 points"); score+=5; }
			if (DRAG==2) { System.out.println("- Three dragons : 4 points"); score+=4; }
			else if (DRAG==1) { System.out.println("- Two dragons : 3 points"); score+=3; }
			else if (WIND==2) { System.out.println("- Three winds : 3 points"); score+=3; }
			else if (WIND==1) { System.out.println("- Two winds : 2 points"); score+=2; }
		}
		else if (TOITU==1)
		{
			if (DRAG==1) { System.out.println("- Dragon pairs : 3 points"); score+=3; }
			else if (WIND==1) { System.out.println("- Wind pairs : 3 points"); score+=3; }
			else { System.out.println("- Four pairs : 1 point"); score++; }
		}
		else
		{
			if (GOKUSI==3) { System.out.println("- Seven orphans 7 wait : 3 points"); score+=3; }
			else if (GOKUSI==2) { System.out.println("- Seven orphans 7 wait(furiten tsumo) : 2 points"); score+=2; }
			else { System.out.println("- Seven orphans : 1 point"); score++; }
		}
		if (NUM==13)
		{
			if (TSUMO==true) { System.out.println("- Heavenly hand : 4 points"); score+=4; }
			else { System.out.println("- Hand of man : 2 points"); score+=2; }
		}
		else if (NUM==12&&TSUMO==true) { System.out.println("- Earthly hand : 2 points"); score+=2; }
		else if (NUM==0)
		{
			if (TSUMO==true) { System.out.println("- Last draw : 1 point"); score++; }
			else { System.out.println("- Last discard : 1 point"); score++; }
		}
		if (FLOWER==true) {	System.out.println("- Dead wall draw : 1 point"); score++; }
		if (CHANG==true) { System.out.println("- Robbing a quad : 1 point"); score++; }
		return score;
	}
}