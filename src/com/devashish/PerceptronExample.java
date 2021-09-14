package com.devashish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PerceptronExample {

	private static int TRAIN_TIMES = 10;

	public static void main(String[] args) {
		testAndTrainORGate();
		testAndTrainANDGate();
		testAndTrainXORGate();
		testAgeLogic();// model to test if age is greater than or equals 18

		String SHAPE_TO_IDENTIFY = "triangle";
		runShapesClassification(SHAPE_TO_IDENTIFY);
	}

	private static void runShapesClassification(String SHAPE_TO_IDENTIFY) {

		List<String> trainingFiles = FileIO.listFilesPaths("imgs/train");
		System.out.println("Training files: " + "" + trainingFiles);

		HashMap<String, List<Integer>> trainingFilesData = new HashMap<String, List<Integer>>();
		trainingFiles.stream().forEach(fName -> trainingFilesData.put(fName, FileIO.readFile(fName)));
		// images are stored as monochrome so that same resolution images are of same
		// size(in terms of memory)
		System.out.println("Training FileSizes:");
		trainingFilesData.forEach((k, v) -> System.out.println(k + " size:" + v.size()));

		Perceptron shapeImagesPerceptron = new Perceptron(1.0f, 1.0f,
				trainingFilesData.get(trainingFiles.get(0)).size());
		InputsOutputs ioShapeImages = shapeImagesPerceptron.getInputsOutputs();
		trainingFilesData.forEach((k, v) -> {
			try {
				ioShapeImages.addInputs(v, (k.contains(SHAPE_TO_IDENTIFY) ? 1 : 0));
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			shapeImagesPerceptron.setLabelFor1(SHAPE_TO_IDENTIFY);
			shapeImagesPerceptron.trainPerceptronNTimes(TRAIN_TIMES * 10);
			shapeImagesPerceptron.dontShowDetailedOutput();
			System.out.println("Shapes training complete");
			System.out.println("results from trained/seen Inputs:");
			shapeImagesPerceptron.computeAgainstTrainedInpusShowLabelFor1AsOutput();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// calculating results for unseen images
		List<String> testFiles = FileIO.listFilesPaths("imgs/test");
		System.out.println("Training files: " + "" + testFiles);
		HashMap<String, List<Integer>> testFilesData = new HashMap<String, List<Integer>>();
		System.out.println("Test FileSizes:");
		testFilesData.forEach((k, v) -> System.out.println(k + " size:" + v.size()));

		testFiles.stream().forEach(fName -> testFilesData.put(fName, FileIO.readFile(fName)));

		System.out.println("Results from unseen data");
		testFilesData.forEach((k, v) -> {
			try {
				shapeImagesPerceptron.computeAgainstAnyInpusShowLabelFor1AsOutput(v,
						(k.contains(SHAPE_TO_IDENTIFY) ? 1 : 0));
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//System.out.println("The function that was calculated:");
		//System.out.println(shapeImagesPerceptron.getCalculatedFunction());
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
