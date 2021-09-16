package com.devashish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Devashish Priyadarshi
 * This is the main class that has all different functions for different models
 * For examlpe "testAndTrainORGate()" function creates and trains a model based on the inputs and output sets of OR Gate
 * Similarly the AND and XOR one, the XOR one fails however no matter how many rounds of training
 * testAgeLogic() is the function training the model forthe logic (age<18?0:1)
 * runShapesClassification() the imgs directory has images of shapes inside train and test directory a model is trained on the images in train and tested against the images in test directory
 * runHeroClassification() the img_complex has images of actors inside train and test directory a model is trained on the images in train and tested against the images in test directory
 */
public class PerceptronExample {

	private static int TRAIN_TIMES = 10;

	public static void main(String[] args) {
		testAndTrainORGate();
		testAndTrainANDGate();
		testAndTrainXORGate();
		testAgeLogic();// model to test if age is greater than or equals 18

		String SHAPE_TO_IDENTIFY = "triangle";
		runShapesClassification(SHAPE_TO_IDENTIFY);
		String HERO_TO_IDENTIFY = "hritik";
		runHeroClassification(HERO_TO_IDENTIFY);
	}
	
	/**
	 * @param HERO_TO_IDENTIFY
	 * HERO_TO_IDENTIFY is the String for the name of actor that needs to be identified 
	 * and the putput is  value of HERO_TO_IDENTIFY or "NOT"+ vlaue of HERO_TO_IDENTIFY
	 * the img_complex has images of actors inside train and test directory a model is trained on the images in train and tested against the images in test directory
	 */
	private static void runHeroClassification(String HERO_TO_IDENTIFY) {

		List<String> trainingFiles = FileIO.listFilesPaths("img_complex/train");
		System.out.println("Training files: " + "" + trainingFiles);

		HashMap<String, List<Integer>> trainingFilesData = new HashMap<String, List<Integer>>();
		trainingFiles.stream().forEach(fName -> trainingFilesData.put(fName, FileIO.readFile(fName)));
		// images are stored as monochrome so that same resolution images are of same
		// size(in terms of memory)
		System.out.println("Training FileSizes:");
		trainingFilesData.forEach((k, v) -> System.out.println(k + " size:" + v.size()));

		Perceptron heroImagesPerceptron = new Perceptron(1.0f, 1.0f,
				trainingFilesData.get(trainingFiles.get(0)).size());
		InputsOutputs ioShapeImages = heroImagesPerceptron.getInputsOutputs();
		trainingFilesData.forEach((k, v) -> {
			try {
				ioShapeImages.addInputs(v, (k.contains(HERO_TO_IDENTIFY) ? 1 : 0));
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		try {
			heroImagesPerceptron.setLabelFor1(HERO_TO_IDENTIFY);
			heroImagesPerceptron.trainPerceptronNTimes(TRAIN_TIMES * 100);
			heroImagesPerceptron.dontShowDetailedOutput();
			System.out.println("Heroes training complete");
			System.out.println("results from trained/seen Inputs:");
			heroImagesPerceptron.computeAgainstTrainedInpusShowLabelFor1AsOutput();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		heroImagesPerceptron.getFunctionHistogram().forEach((k,v)->System.out.println(k+" "+v));
		// calculating results for unseen images
		List<String> testFiles = FileIO.listFilesPaths("img_complex/test");
		System.out.println("Test files: " + "" + testFiles);
		HashMap<String, List<Integer>> testFilesData = new HashMap<String, List<Integer>>();
		System.out.println("Test FileSizes:");
		testFilesData.forEach((k, v) -> System.out.println(k + " size:" + v.size()));

		testFiles.stream().forEach(fName -> testFilesData.put(fName, FileIO.readFile(fName)));

		System.out.println("Results from unseen data");
		testFilesData.forEach((k, v) -> {
			try {
				heroImagesPerceptron.computeAgainstAnyInpusShowLabelFor1AsOutput(v,
						(k.contains(HERO_TO_IDENTIFY) ? 1 : 0));
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//System.out.println("The function that was calculated:");
		//System.out.println(shapeImagesPerceptron.getCalculatedFunction());
	}

	/**
	 * @param SHAPE_TO_IDENTIFY
	 * SHAPE_TO_IDENTIFY holds the value of the shape to identify and outputs returns if the shape provided is valueof(SHAPE_TO_IDENTIFY) or "NOT" + valueof(SHAPE_TO_IDENTIFY)
	 * the imgs directory has images of shapes inside train and test directory a model is trained on the images in train and tested against the images in test directory
	 * 
	 */
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
		System.out.println("Test files: " + "" + testFiles);
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

	/**
	 * creates and trains a model for the logic (age<18?0:1)
	 */
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

	/**
	 * creates and trains a model for all the inputs of XOR gate
	 */
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

	/**
	 * creates and trains a model for all the inputs of AND gate
	 */
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

	/**
	 * creates and trains a model for all the inputs of OR gate
	 */
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
