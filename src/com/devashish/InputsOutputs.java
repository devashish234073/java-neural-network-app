package com.devashish;
import java.util.ArrayList;
import java.util.List;

public class InputsOutputs {
	private ArrayList<List<Integer>> inputsArr = new ArrayList<List<Integer>>();
	private ArrayList<Integer> outputs = new ArrayList<Integer>();
	private int numInputs;
	
	public InputsOutputs(int numInputs) {
		this.numInputs = numInputs;
	}
	
	public void addInputs(List<Integer> inputs,int expectedOutput) throws InvalidInputException {
		if(inputs.size()!=this.numInputs) {
			throw new InvalidInputException(this.numInputs,inputs.size());
		}
		inputsArr.add(inputs);
		outputs.add(expectedOutput);
	}
	
	public List<Integer> getInputAtIndex(int index) {
		return inputsArr.get(index);
	}
	
	public int getInputSize() {
		return outputs.size();
	}
	
	public int getOutputAtIndex(int index) {
		return outputs.get(index);
	}
}
