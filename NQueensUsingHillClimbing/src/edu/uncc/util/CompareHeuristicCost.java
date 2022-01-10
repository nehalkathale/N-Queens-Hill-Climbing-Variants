package edu.uncc.util;

import java.util.Comparator;

import edu.uncc.hillClimbing.ProcessNode;

public class CompareHeuristicCost implements Comparator<ProcessNode> {

	@Override
	public int compare(ProcessNode o1, ProcessNode o2) {
		
		if (o1.getHeuristicCost() < o2.getHeuristicCost()) {
			return -1;
		}
			
		if (o1.getHeuristicCost() > o2.getHeuristicCost()) {
			return 1;
		}
			
		return 0;
	}
	
}