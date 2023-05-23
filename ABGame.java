package assignment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class ABGame {
	public static int staticEstimationMidgameEndGame(char[] boardPosition) {
		int numBlackPieces = Game.count(boardPosition, 'B');
		int numWhitePieces = Game.count(boardPosition,'W');
		int numBlackMoves = Game.generateMovesMidgameEndGameBlack(boardPosition).size();
		
		if(numBlackPieces <= 2) {
			return 10000;
		}
		else if(numWhitePieces <=2) {
			return -10000;
		}
		else if(numBlackMoves==0) {
			return 10000;
		}
		else {
			return (1000*(numWhitePieces - numBlackPieces) - numBlackMoves);
		}
	}
	
	public static Node alphaBetaPruning(char[] boardPosition, int height, boolean isMax,int alpha, int beta ) {
		if(height==0) {
			return new Node(boardPosition,staticEstimationMidgameEndGame(boardPosition),1);
		}
		Node currNode = new Node();
		List<char[]> positionList;
		if(isMax) {
			positionList= Game.generateMovesMidgameEndGame(boardPosition);
			for(char[] position: positionList) {
				Node childNode = alphaBetaPruning(position,height-1,!isMax,alpha, beta);
				if(childNode.getStaticEstimate() > alpha) {
					alpha = childNode.getStaticEstimate();
					currNode.setBoardPositions(position.clone());
				}
				currNode.setEstimate(currNode.getEstimate() + childNode.getEstimate());
				if(alpha>=beta) {
					break;
				}
			}
			currNode.setStaticEstimate(alpha);
		}
		else {
			positionList = Game.generateMovesMidgameEndGameBlack(boardPosition);
			for(char[] position: positionList) {
				Node childNode = alphaBetaPruning(position, height-1,!isMax, alpha, beta);
				if(childNode.getStaticEstimate() < beta) {
					beta = childNode.getStaticEstimate();
					currNode.setBoardPositions(position.clone());
				}
				currNode.setEstimate(currNode.getEstimate()+childNode.getEstimate());
				if(alpha >=beta) {
					break;
				}
			}
			currNode.setStaticEstimate(beta);
		}
		return currNode;
	}
	public static char[] getPosition(File inputFile) {
		System.out.println("Let us see if the file exists" + inputFile.exists());
		String currLine=null;
		char[] boardPosition = null;
		System.out.println("reading inputFile----->" + inputFile);
		try {
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			currLine = bufferedReader.readLine();
			System.out.println(currLine);
			bufferedReader.close();
		}
		catch(IOException e) {
			System.out.println("Reading error....");
			System.err.println(e.getStackTrace());
		}
		
		if(currLine!=null) {
			boardPosition =  currLine.toCharArray();
		}
		else {
			System.out.println("File is empty....");
		}
		return boardPosition;
	}
	
	public static int writeToFile(String outputFile, char[] boardPosition) {
		int returnStatus = -1;
		try {
			FileWriter fileWriter = new FileWriter(outputFile);
			fileWriter.write(boardPosition);
			fileWriter.close();
			returnStatus = 1;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return returnStatus;
	}
	public static void main(String[] args) {
		if(args.length!=3) {
			System.out.println(args.length);
			System.out.println("Please Enter the input and the output file name...");
			System.exit(0);
		}
		File inputFile = new File(args[0]);
		String outputFile = args[1];
		char[] boardPosition = getPosition(inputFile);
		if(boardPosition==null) {
			System.out.println("input error... program is terminating");
			System.exit(0);
		}
		int height = Integer.parseInt(args[2]);
		Node outputNode = alphaBetaPruning(boardPosition,height, true,Integer.MIN_VALUE, Integer.MAX_VALUE);
		char[] outputBoard = outputNode.getBoardPositions();
		int status = writeToFile(outputFile,outputBoard);
		System.out.println("write status is" + status);
		System.out.printf("Input board is: %s%n", new String(boardPosition));
		System.out.printf("Output board is: %s%n",new String(outputNode.getBoardPositions()));
		System.out.printf("Static estimation: %d%n",outputNode.getStaticEstimate());
		System.out.printf("Positions Evaluated By ABPrunning : %d%n",outputNode.getEstimate());
		if(status==-1) {
			System.exit(status);
		}
	}	
}
