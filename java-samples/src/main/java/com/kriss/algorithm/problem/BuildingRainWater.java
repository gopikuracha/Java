package com.kriss.algorithm.problem;

public class BuildingRainWater {

	public static void main(String[] args) {
		
		int[] b = {6, 2, 4, 1, 9, 5, 3, 5, 1, 6};
		System.out.println("Units of Water Stored : " + getRainWater(b));
	}
	
	public static int getRainWater(int[] b) {
		int size = b.length;
		int leftPeakNode = -1, rightPeakNode = -1, totalArea = 0;
		for (int i=0; i<size; i++) {
			if (i == 0) {
				if (b[i] >= b[i+1]) leftPeakNode = i;
				continue;
			}
			if (i == (size-1)) {
				if (b[i] > b[i-1]) {
					if (leftPeakNode == -1) return totalArea;
					else if (b[i] >= b[leftPeakNode]) {
						totalArea += calAreaBtwnPeaks(leftPeakNode, i, b);
						return totalArea;
					}
				}
				break;
			}
			if (b[i] >= b[i-1] && b[i] >= b[i+1]) {
				if (leftPeakNode == -1) leftPeakNode = i;
				else {
					if (b[i] >= b[leftPeakNode]) {
						totalArea += calAreaBtwnPeaks(leftPeakNode, i, b);
						leftPeakNode = i;
					}
				}
			}
		}
		
		for (int i=(size-1); i>=leftPeakNode; i--) {
			if (i == (size-1)) {
				if (b[i] > b[i-1]) rightPeakNode = i;
				continue;
			}
			if (i == leftPeakNode) {
				if (b[i] > b[i+1]) {
					if (rightPeakNode == -1) return totalArea;
					totalArea += calAreaBtwnPeaks(i, rightPeakNode, b);
				}
				break;
			}
			if (b[i] > b[i-1] && b[i] > b[i+1]) {
				if (rightPeakNode == -1) rightPeakNode = i;
				else {
					if (b[i] >= b[rightPeakNode]) {
						totalArea += calAreaBtwnPeaks(i, rightPeakNode, b);
						rightPeakNode = i;
					}
				}
			}
		}
		return totalArea;
	}
	
	public static int calAreaBtwnPeaks(int leftNode, int rightNode, int[] b) {
		int sum = 0;
		for (int i=leftNode+1; i<rightNode; i++) sum+=b[i];
		int width = (rightNode-leftNode)-1;
		int height = (b[rightNode] > b[leftNode]) ? b[leftNode] : b[rightNode];
		int area = (height*width)-sum;
		System.out.println(leftNode + " " + rightNode + " " + sum + " " + width + " : " + area);
		return area;
	}
	
}
