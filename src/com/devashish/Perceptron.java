package com.devashish;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author Devashish Priyadarshi
 * This class has all the methods for storing inputs , their corresponding outputs 
 * Running training against those inputs
 * And finally running tests on the trained inputs or on unseen inputs
 */
public class Perceptron {
	private final double learningRate;
	private final double bias;
	private final double THRESHOLD = 0.5;
	private double[] weights;
	private double biasWeight;
	private int numberOfInputs;
	private InputsOutputs ios;
	private boolean showDetailedOutput = true;
	private String labelFor1 = "";
	
	public Perceptron(double learningRate,double bias,int numberOfInputs) {
		this.learningRate = learningRate;
		this.bias = bias;
		this.numberOfInputs = numberOfInputs;
		weights = new double[this.numberOfInputs];
		SecureRandom rnd = new SecureRandom();
		for(int i=0;i<this.numberOfInputs;i++) {
			double w = rnd.nextDouble();
			weights[i] = w;
		}
		biasWeight = rnd.nextDouble();
		//System.out.println(getCalculatedFunction());
		ios = new InputsOutputs(numberOfInputs);
	}
	
	public void setLabelFor1(String labelFor1) {
		this.labelFor1 = labelFor1;
	}
	
	public void showDetailedOutput() {
		this.showDetailedOutput = true;
	}
	
	public void dontShowDetailedOutput() {
		this.showDetailedOutput = false;
	}
	
	public InputsOutputs getInputsOutputs() {
		return ios;
	}
	
	public int getNumberOfInputs() {
		return this.numberOfInputs;
	}
	
	public void train(List<Integer> trainingData,int expectedOutput) throws InvalidInputException {
		Integer[] intArr = new Integer[trainingData.size()];
		trainingData.toArray(intArr);
		train(intArr,expectedOutput);
	}
	
	public void train(Integer[] trainingData,int expectedOutput) throws InvalidInputException {
		if(trainingData.length!=this.numberOfInputs) {
			throw new InvalidInputException(this.numberOfInputs,trainingData.length);
		}
		double output = 0.0;
		for(int i=0;i<this.numberOfInputs;i++ ) {
			output += ((double)trainingData[i]) * weights[i];
		}
		//add bias
		output += this.bias * this.biasWeight;
		int intOutput = 0;
		if(output>THRESHOLD) {
			intOutput = 1; 
		}
		double error = (double)expectedOutput - intOutput;
		//correct weights based on error
		for(int i=0;i<this.numberOfInputs;i++ ) {
			weights[i] += error * (double)trainingData[i] * this.learningRate;
		}
		biasWeight += error * this.bias * this.learningRate; 
		//System.out.println("output: "+output+",eror:" +error+"=> biasWeight "+this.biasWeight);
	}
	
	public void compute(String label, List<Integer> data) throws InvalidInputException {
		Integer[] intArr = new Integer[data.size()];
		data.toArray(intArr);
		compute(label,intArr);
	}
	
	public int compute(String label,Integer[] data) throws InvalidInputException {
		if(data.length!=this.numberOfInputs) {
			throw new InvalidInputException(this.numberOfInputs,data.length);
		}
		double output = 0.0f;
		//multiply the inputs with the weights
		for(int i=0;i<this.numberOfInputs;i++ ) {
			output += ((double)data[i]) * weights[i];
		}
		//add the bias
		output += this.bias * this.biasWeight;
		int intOutput = 0;
		if(output>THRESHOLD) {
			intOutput = 1;
		}
		if(showDetailedOutput) {
			System.out.println("Result of "+Arrays.toString(data)+" is "+intOutput+" Calculated from function:"+getCalculatedFunction());	
		} else {
			System.out.println("{expected:"+label+" computed:"+getCorrectLabel(intOutput)+"}");
		}
		return intOutput;
	}
	
	public String getCalculatedFunction() {
		String f = "";
		for(int i=0;i<this.weights.length;i++) {
			if(f.equals("")) {
			    f+= "I"+i+" * "+weights[i];
			} else {
				f+= "+ I"+i+" * "+weights[i];
			}
		}
		f+= "+ "+this.bias +" * "+this.biasWeight;
		//String debugInfo = " {Weights:"+Arrays.toString(this.weights) + ",biasWeight:"+this.biasWeight+"}";
		return " [f="+f+"]";
	}
	
	public HashMap<String,Integer> getFunctionHistogram() {
		HashMap<Integer,Integer> histo = new HashMap<Integer,Integer>(); 
		for(int i=0;i<this.weights.length;i++) {
			Double w = weights[i];
			int wInt = Math.abs(w.intValue());
			if(histo.get(wInt)==null) {
				histo.put(wInt, 1);
			} else {
				histo.put(wInt, 1+histo.get(wInt));
			}
		}
		Collection<Integer> coll = histo.keySet();
		int max = coll.stream().max((n1,n2)->n1-n2).get();
		int min = coll.stream().min((n1,n2)->n1-n2).get();
		System.out.println("MAX_WEIGHT:"+max);
		System.out.println("MAX_WEIGHT_ASSIGNED_TO:"+histo.get(max)+" points.");
		System.out.println("MIN_WEIGHT:"+min);
		System.out.println("MIN_WEIGHT_ASSIGNED_TO:"+histo.get(min)+" points.");
		
		HashMap<String,Integer> histo2 = new HashMap<String,Integer>(); 
		for(int i=min;i<=max;i++) {
			for(double PERCENT=0.1;PERCENT<=1.0;PERCENT+=0.1) {
				Double from = (PERCENT-0.1)*max;
				Double to = PERCENT*max;
				if(i>=from && i<=to) {
					String key = "[Weights from "+from.intValue()+" to "+to.intValue()+" assigend to]: ";
					int frequencyOfValuei = histo.get(i)==null?0:histo.get(i);
					if(histo2.get(key)==null) {
						histo2.put(key, frequencyOfValuei);
					} else {
						histo2.put(key, frequencyOfValuei+histo2.get(key));
					}
				}	
			}
		}
		/*coll.stream().max(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});*/
		return histo2;
	}
	
	public void trainPerceptronNTimes(int n) throws InvalidInputException {
		for(int i=0;i<n;i++) {
			trainPerceptron();
		}
	}
	
	public void trainPerceptron() throws InvalidInputException {
		for(int i=0;i<ios.getInputSize();i++) {
			List<Integer> inputs = ios.getInputAtIndex(i);
			int expectedOutput = ios.getOutputAtIndex(i);
			train(inputs, expectedOutput);
		}
	}
	
	public void computeAgainstTrainedInpus() throws InvalidInputException {
		for(int i=0;i<ios.getInputSize();i++) {
			List<Integer> inputs = ios.getInputAtIndex(i);
			compute(ios.getOutputAtIndex(i)+"",inputs);
		}
	}
	
	private String getCorrectLabel(int output) {
		if(labelFor1.equals("")) {
			return output+"";
		}
		return (output==1?labelFor1:"NOT "+labelFor1);
	}
	
	public void computeAgainstTrainedInpusShowLabelFor1AsOutput() throws InvalidInputException {
		for(int i=0;i<ios.getInputSize();i++) {
			List<Integer> inputs = ios.getInputAtIndex(i);
			compute(getCorrectLabel(ios.getOutputAtIndex(i))+"",inputs);
		}
	}
	
	public void computeAgainstAnyInpusShowLabelFor1AsOutput(List<Integer> inputs,int expectedOutput) throws InvalidInputException {
		compute(getCorrectLabel(expectedOutput)+"",inputs);
	}
	
}
