# java-neural-network-app

This is a java based neural network app based on the Perceptron concept from https://towardsdatascience.com/first-neural-network-for-beginners-explained-with-code-4cfd37e06eaf implementation which is in python.

![image](https://user-images.githubusercontent.com/20777854/133108042-de932ca7-cc3a-4a93-b061-5df57d0e968c.png)

The app starts with training some logic gates against all possible inputs and then testing them again against those inputs

When OR Gate training completes for example the output is :

---Training model for OR Gate---
Result of [0, 0] is 0 Calculated from function: [f=I0 * 1.5006730136829658+ I1 * 1.6650976926846672+ 1.0 * -0.2737571815368699]
Result of [0, 1] is 1 Calculated from function: [f=I0 * 1.5006730136829658+ I1 * 1.6650976926846672+ 1.0 * -0.2737571815368699]
Result of [1, 0] is 1 Calculated from function: [f=I0 * 1.5006730136829658+ I1 * 1.6650976926846672+ 1.0 * -0.2737571815368699]
Result of [1, 1] is 1 Calculated from function: [f=I0 * 1.5006730136829658+ I1 * 1.6650976926846672+ 1.0 * -0.2737571815368699]
-------------------

The model is predicting the values correctly and it has computed f=I0 * 1.5006730136829658+ I1 * 1.6650976926846672+ 1.0 * -0.2737571815368699 as the function 
 Where 1.5006730136829658 and 1.6650976926846672 are the weights on each inputs 
 and 1.0 is the bias and -0.2737571815368699 is the bias weight

Similarly AND and XOR gate is done , however the model is not able to evaluate XOR for obvious reasons of its behavior

The Application that extends into training a model for (age<18:0:1) logic , which again it succeeds to do.

Next the app is extended again to identify shapes present in imgs directory,
model is created by training against imgs/train and then tested against imgs/test

Similarly app is extended again to identify complex objects in this case Bollywood actor Hritik.
The images are present in the "img_complex/train" and "img_complex_test" directories
 