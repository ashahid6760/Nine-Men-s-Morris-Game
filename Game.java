package assignment;
import java.util.*;
public class Game {
	public static List<char[]> generateMovesOpening(char[] boardPosition) {
		return generateAdd(boardPosition);
	}
	public static List<char[]> generateMovesOpeningBlack(char[] boardPosition){
		char[] invBoard = invertColor(boardPosition.clone());
		List<char[]>invList = generateAdd(invBoard);
		return invertCollection(invList);
	}
	public static List<char[]> generateMovesMidgameEndGame(char[] boardPosition) {
		int whiteCount = count(boardPosition,'W');
		if(whiteCount==3) {
			return generateHopping(boardPosition);
		}
		else {
			return generateMove(boardPosition);
		}
	}
	public static List<char[]> generateMovesMidgameEndGameBlack(char[] boardPosition){
		char[] invBoard = invertColor(boardPosition.clone());
		List<char[]> invList = generateMovesMidgameEndGame(invBoard);
		return invertCollection(invList);
	}
	
	public static int count(char[] boardPosition, char val) {
		int count = 0;
		for(int i=0; i< boardPosition.length; i+=1) {
			if(boardPosition[i] == val) {
				count +=1;
			}
		}
		return count;
	}
	
	public static List<char[]> generateAdd(char[] boardPosition) {
		List<char[]> positionList = new ArrayList<>();
		for(int i=0; i< boardPosition.length; i+=1) {
			if(boardPosition[i]=='x') {
				char[] b = boardPosition.clone();
				b[i] = 'W';
				if(closeMill(i,b)) {
			/**/	generateRemove(b,positionList);
				}
				else {
					positionList.add(b);
				}
			}
		}
		return positionList;
	}
	public static char[] invertColor(char[] boardPosition) {
	//	System.out.println("The board at invert color input is: " + new String(boardPosition));
		for(int i=0; i<boardPosition.length; i+=1) {
			if(boardPosition[i]=='W') {
				boardPosition[i]='B';
			}
			else if(boardPosition[i]=='B') {
				boardPosition[i]='W';
			}
		}
		//System.out.println("The board at output of invert color is: "+ new String(boardPosition));
		return boardPosition;
	}
	
	public static List<char[]> invertCollection(List<char[]> invPositionList){
		int size = invPositionList.size();
		System.out.println("Input collenction");
		for(int i=0; i< size; i+=1) {
			System.out.println(new String(invPositionList.get(i)));
			invertColor(invPositionList.get(i));
		}
		System.out.println("Output position list is....");
		for(int i=0;i< size; i+=1) {
			System.out.println(new String(invPositionList.get(i)));
		}
		return invPositionList;
	}
/*	
	public static List<char[]> generateAddBlack(char[] boardPosition){
		char[] boardPositionInverted = invertColor(boardPosition);
		List<char[]> generatedList = generateAdd(boardPositionInverted);
		return invertCollection(generatedList);
	}
	*/
	/*
	public static List<char[]> generateHoppingBlack(char[] boardPosition){
		char[] invBoardPosition = invertColor(boardPosition);
		List<char[]> invList = generateHopping(invBoardPosition);
		return invertCollection(invList);
	}
	*/
	public static List<char[]> generateHopping(char[] boardPosition){
		List<char[]> positionList = new ArrayList<>();
		for(int alpha = 0; alpha < boardPosition.length; alpha+=1) {
			if(boardPosition[alpha]=='W') {
				for(int beta=0; beta < boardPosition.length; beta+=1) {
					if(boardPosition[beta]=='x') {
						char[] b = boardPosition.clone();
						b[alpha] = 'x';
						b[beta] = 'W';
						if(closeMill(beta,b)) {
							generateRemove(b,positionList);
						}
						else {
							positionList.add(b);
						}
					}
				}
			}
		}
		return positionList;
	}
	
	public static List<char[]> generateMove(char[] boardPosition) {
		List<char[]> positionList = new ArrayList<>();
		for(int i=0; i < boardPosition.length; i+=1) {
			if(boardPosition[i]=='W') {
				int[] neighbors = getNeighborsLocation(boardPosition,i);
				for(int j : neighbors) {
					if(boardPosition[j]=='x') {
						char[] b = boardPosition.clone();
						b[i] = 'x';
						b[j] = 'W';
						if(closeMill(j,b)) {
							generateRemove(b,positionList);
						}
						else {
							positionList.add(b);
						}
					}
				}
			}
		}
		return positionList;
	}
	/*
	public static List<char[]> generateMoveBlack(char[] boardPosition){
		char[] invBoardPosition = invertColor(boardPosition);
		List<char[]> invList = generateMove(invBoardPosition);
		return invertCollection(invList);
	}
	*/
	
	private static void generateRemove(char[] boardPosition, List<char[]> positionList) {
		// TODO Auto-generated method stub
		for(int i=0; i < boardPosition.length; i+=1) {
			if(boardPosition[i]=='B') {
				if(!closeMill(i,boardPosition)) {
					char[] b = boardPosition.clone();
					b[i] = 'x';
					positionList.add(b);
				}
			}
		}
	}
	/*
	public static List<char[]> generateRemoveBlack(char[] boardPosition){
		char[] invBoardPosition = invertColor(boardPosition);
		List<char[]> invList = generateRemoveBlack(invBoardPosition);
		return invertCollection(invList);
	}
	*/

	public static boolean closeMill(int i, char[] b) {
		// TODO Auto-generated method stub
		char c = b[i];
		switch(i) {
			case 0:
				if(b[1]==c && b[2]==c) {
					return true;
				}
				else if(b[3]==c && b[6]==c) {
					return true;
				}
				else {
					return false;
				}
			case 1:
				if(b[0]==c && b[2]==c) {
					return true;
				}
				else {
					return false;
				}
			case 2:
				if(b[1]==c && b[0]==c) {
					return true;
				}
				else if(b[5]==c && b[7]==c) {
					return true;
				}
				else if(b[12]==c && b[21]==c) {
					return true;
				}
				else {
					return false;
				}
			case 3:
				if(b[0]==c && b[6]==c) {
					return true;
				}
				else if(b[4]==c && b[5]==c) {
					return true;
				}
				else if(b[8]==c && b[16]==c) {
					return true;
				}
				else {
					return false;
				}
			case 4:
				if(b[3]==c && b[5]==c) {
					return true;
				}
				else {
					return false;
				}
			case 5:
				if(b[4]==c && b[3]==c) {
					return true;
				}
				else if(b[7]==c && b[2]==c) {
					return true;
				}
				else if(b[11]==c && b[18]==c) {
					return true;
				}
				else {
					return false;
				}
			case 6:
				if(b[3]==c && b[0]==c) {
					return true;
				}
				else if(b[9]==c && b[13]==c) {
					return true;
				}
				else {
					return false;
				}
			case 7:
				if(b[5]==c && b[2]==c) {
					return true;
				}
				else if(b[10]==c && b[15]==c) {
					return true;
				}
				else {
					return false;
				}
			case 8:
				if(b[16]==c && b[3]==c){
					return true;
				}
				else {
					return false;
				}
			case 9:
				if(b[13]==c && b[6]==c) {
					return true;
				}
				else {
					return false;
				}
			case 10:
				if(b[15]==c && b[7]==c) {
					return true;
				}
				else if(b[11]==c && b[12]==c) {
					return true;
				}
				else {
					return false;
				}
			case 11:
				if(b[10]==c && b[12]==c) {
					return true;
				}
				else if(b[18]==c && b[5]==c) {
					return true;
				}
				else {
					return false;
				}
			case 12:
				if(b[11]==c && b[10]==c) {
					return true;
				}
				else if(b[21]==c && b[2]==c) {
					return true;
				}
				else {
					return false;
				}
			case 13:
				if(b[9]==c && b[6]==c) {
					return true;
				}
				else if(b[14]==c && b[15]==c) {
					return true;
				}
				else if(b[16]==c && b[19]==c) {
					return true;
				}
				else {
					return false;
				}
			case 14:
				if(b[13]==c && b[15]==c) {
					return true;
				}
				else if(b[17]==c && b[20]==c) {
					return true;
				}
				else {
					return false;
				}
			case 15:
				if(b[10]==c && b[7]==c) {
					return true;
				}
				else if(b[14]==c && b[13]==c) {
					return true;
				}
				else if(b[18]==c && b[21]==c) {
					return true;
				}
				else {
					return false;
				}
			case 16: 
				if(b[8]==c && b[3]==c) {
					return true;
				}
				else if(b[17]==c && b[18]==c) {
					return true;
				}
				else if(b[19]==c && b[13]==c) {
					return true;
				}
				else {
					return false;
				}
			case 17:
				if(b[20]==c && b[14]==c) {
					return true;
				}
				else if(b[16]==c && b[18]==c) {
					return true;
				}
				else {
					return false;
				}
			case 18:
				if(b[11]==c && b[5]==c) {
					return true;
				}
				else if(b[17]==c && b[16]==c) {
					return true;
				}
				else if(b[15]==c && b[21]==c) {
					return true;
				}
				else {
					return false;
				}
			case 19:
				if(b[20]==c && b[21]==c) {
					return true;
				}
				else if(b[16]==c && b[13]==c) {
					return true;
				}
				else {
					return false;
				}
			case 20:
				if(b[19]==c && b[21]==c) {
					return true;
				}
				else if(b[17]==c && b[14]==c) {
					return true;
				}
				else {
					return false;
				}
			case 21:
				if(b[20]==c && b[19]==c) {
					return true;
				}
				else if(b[12]==c && b[2]==c) {
					return true;
				}
				else if(b[15]==c && b[18]==c) {
					return true;
				}
				else {
					return false;
				}
		}
				
		return false;
	}
	
	public static int[] getNeighborsLocation(char[] b, int idx) {
		// TODO Auto-generated method stub
		int[] nIdx;
		switch(idx) {
		case 0:
			nIdx = new int[]{1,3,19};
			return nIdx;
		case 1:
			nIdx = new int[]{0,2,4};
			return nIdx;
		case 2:
			nIdx = new int[]{1,5,12};
			return nIdx;
		case 3:
			nIdx = new int[]{0,4,6,8};
			return nIdx;
		case 4:
			nIdx = new int[]{1,3,5};
			return nIdx;
		case 5:
			nIdx = new int[]{2,4,7,11};
			return nIdx;
		case 6:
			nIdx = new int[]{3,7,9};
			return nIdx;
		case 7:
			nIdx = new int[]{5,6,10};
			return nIdx;
		case 8:
			nIdx = new int[]{3,9,16};
			return nIdx;
		case 9:
			nIdx = new int[]{6,8,13};
			return nIdx;
		case 10:
			nIdx = new int[]{7,11,15};
			return nIdx;
		case 11:
			nIdx = new int[]{5,10,12,18};
			return nIdx;
		case 12:
			nIdx = new int[]{2,11,21};
			return nIdx;
		case 13:
			nIdx = new int[]{9,14,16};
			return nIdx;
		case 14:
			nIdx = new int[]{13,15,17};
			return nIdx;
		case 15:
			nIdx = new int[]{10,14,18};
			return nIdx;
		case 16:
			nIdx = new int[]{8,13,17,19};
			return nIdx;
		case 17:
			nIdx = new int[]{14,16,18,20};
			return nIdx;
		case 18:
			nIdx = new int[]{11,15,17,21};
			return nIdx;
		case 19:
			nIdx = new int[]{0,16,20};
			return nIdx;
		case 20:
			nIdx = new int[]{17,19,21};
			return nIdx;
		case 21:
			nIdx = new int[]{12,18,20};
			return nIdx;
		}
		
		return null;
	}
		
}