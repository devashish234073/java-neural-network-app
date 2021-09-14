package com.devashish;

import java.util.Arrays;

public class PerceptronExample {

	private static int TRAIN_TIMES = 10;

	public static void main(String[] args) {
		testAndTrainORGate();
		testAndTrainANDGate();
		testAndTrainXORGate();
		testAgeLogic();
	}

	private static void testAgeLogic() {
		// returns 1 if age > 18
		Perceptron agePerceptron = new Perceptron(1.0f, 1.0f, 1);
		InputsOutputs ioAge = agePerceptron.getInputsOutputs();
		// adding inputs and outputs
		try {
			for (int i = 1; i < 100; i++) {
				Integer[] input = new Integer[] { i };
				;
				int output = 1;
				if (i < 18) {
					output = 0;
				}
				ioAge.addInputs(Arrays.asList(input), output);
			}
			// training
			agePerceptron.trainPerceptronNTimes(TRAIN_TIMES + 30);
			// testing
			agePerceptron.computeAgainstTrainedInpus();
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void testAndTrainXORGate() {
		Perceptron xorGatePerceptron = new Perceptron(1.0f, 1.0f, 2);
		InputsOutputs ioXOR = xorGatePerceptron.getInputsOutputs();
		// adding inputs and outputs
		try {
			ioXOR.addInputs(Arrays.asList(new Integer[] { 0, 0 }), 0);
			ioXOR.addInputs(Arrays.asList(new Integer[] { 0, 1 }), 1);
			ioXOR.addInputs(Arrays.asList(new Integer[] { 1, 0 }), 1);
			ioXOR.addInputs(Arrays.asList(new Integer[] { 1, 1 }), 0);
			// training
			xorGatePerceptron.trainPerceptronNTimes(TRAIN_TIMES + 1000);
			// testing
			xorGatePerceptron.computeAgainstTrainedInpus();
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void testAndTrainANDGate() {
		Perceptron andGatePerceptron = new Perceptron(1.0f, 1.0f, 2);
		InputsOutputs ioAND = andGatePerceptron.getInputsOutputs();
		// adding inputs and outputs
		try {
			ioAND.addInputs(Arrays.asList(new Integer[] { 0, 0 }), 0);
			ioAND.addInputs(Arrays.asList(new Integer[] { 0, 1 }), 0);
			ioAND.addInputs(Arrays.asList(new Integer[] { 1, 0 }), 0);
			ioAND.addInputs(Arrays.asList(new Integer[] { 1, 1 }), 1);
			// training
			andGatePerceptron.trainPerceptronNTimes(TRAIN_TIMES);
			// testing
			andGatePerceptron.computeAgainstTrainedInpus();
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static void testAndTrainORGate() {
		Perceptron orGatePerceptron = new Perceptron(1.0f, 1.0f, 2);
		InputsOutputs ioOR = orGatePerceptron.getInputsOutputs();
		// adding inputs and outputs
		try {
			ioOR.addInputs(Arrays.asList(new Integer[] { 0, 0 }), 0);
			ioOR.addInputs(Arrays.asList(new Integer[] { 0, 1 }), 1);
			ioOR.addInputs(Arrays.asList(new Integer[] { 1, 0 }), 1);
			ioOR.addInputs(Arrays.asList(new Integer[] { 1, 1 }), 1);
			// training
			orGatePerceptron.trainPerceptronNTimes(TRAIN_TIMES);
			// testing
			orGatePerceptron.computeAgainstTrainedInpus();
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
