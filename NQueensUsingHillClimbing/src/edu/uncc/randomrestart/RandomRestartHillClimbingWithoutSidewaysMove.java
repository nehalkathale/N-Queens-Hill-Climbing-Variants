package edu.uncc.randomrestart;
import java.util.ArrayList;
import java.util.List;

import edu.uncc.hillClimbing.ProcessNode;
import edu.uncc.util.HillClimbingUtility;

public class RandomRestartHillClimbingWithoutSidewaysMove {

	public int numberOfQueens = 0;
	public int steps = 0;
	public int noOfCorrectSteps = 0;
	public int noOfIncorrectSteps = 0;
	public int numberOfExecutionSuccess = 0;
	public int numberOfExecutionFailure = 0;
	
	public int restartCounter = 0;
	public int noOfAttemptedRestarts = 0;
	
	public boolean restartFlag = false;
	public boolean executionSuccessFlag = false;
	public boolean executionFailedFlag = false;
	
	
	public int[] executionStatistics = new int[6];
	
	ProcessNode nextCorrectStep = new ProcessNode();
	
	List<ProcessNode> nextSteps = new ArrayList<ProcessNode>();
	
	public HillClimbingUtility hillClimbingUtility = new HillClimbingUtility();
	
	/**
	 * Random Restart Without Sideways Move
	 * 
	 * @param numberOfQueens
	 * @param displayResult
	 * @return
	 */
	public int[] randomRestartHCSearchWithoutSidewayMove(int numberOfQueens) {
	
		this.numberOfQueens = numberOfQueens;

		ProcessNode processNode = hillClimbingUtility.formArbitraryArrangement(numberOfQueens);

		hillClimbingUtility.determineHeuristicCostValue(processNode);

		
			System.out.println("### BEGIN STATE ###");
			hillClimbingUtility.displayResult(processNode);
		
		
		while (!executionSuccessFlag) {

			//	determine possible next steps
			nextSteps = hillClimbingUtility.discoverNextSteps(processNode);

			//	find heuristic for next steps
			for (int p = 0; p < nextSteps.size(); p++) {
				hillClimbingUtility.determineHeuristicCostValue(nextSteps.get(p));
			}

			//	find the next correct step based on heuristics
			nextCorrectStep = hillClimbingUtility.discoverNextBestStep(nextSteps);

			if (processNode.getHeuristicCost() == 0) {

				noOfCorrectSteps = +steps;
				
				numberOfExecutionSuccess++;
				
				executionSuccessFlag = true;
				
				
					System.out.println("### FINAL STATE ###");
					hillClimbingUtility.displayResult(processNode);
			
				
			} else if (nextCorrectStep.getHeuristicCost() < processNode.getHeuristicCost()) {
				
				processNode = nextCorrectStep;
				
				steps++;
			} else {
				
				processNode = hillClimbingUtility.formArbitraryArrangement(numberOfQueens);
				
				hillClimbingUtility.determineHeuristicCostValue(processNode);
				
				noOfAttemptedRestarts++;
				
				restartFlag = true;
			}
		}
		
		if (restartFlag) {
			restartCounter++;
		}

		//	collect execution statistics
		executionStatistics[0] = noOfCorrectSteps;
		
		executionStatistics[1] = numberOfExecutionSuccess;
		
		executionStatistics[2] = noOfIncorrectSteps;
		
		executionStatistics[3] = numberOfExecutionFailure;
		
		executionStatistics[4] = noOfAttemptedRestarts;
		
		executionStatistics[5] = restartCounter;

		return executionStatistics;
	}
}