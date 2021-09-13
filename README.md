# java-neural-network-app

This is a java based neural network app based on https://towardsdatascience.com/first-neural-network-for-beginners-explained-with-code-4cfd37e06eaf implementation which is in python.

![image](https://user-images.githubusercontent.com/20777854/133108042-de932ca7-cc3a-4a93-b061-5df57d0e968c.png)

The same Perceptron implementation is used to implement or, and and xor gate and a asic logic of checking if age is less than 18, while 60 rounds of training is sufficient to teach 'age>18 logic' and 10 rounds of training for 'or' and 'and' gates. It wasn't able to train itself even after 1000 round of training for xor gate.

For OR gate its trained with all the possible inputs of OR and with 10 rounds of training , its able to compute from given inputs properly.
The function it calculates in the end is: [f=I0 * 0.5894199282171857+ I1 * 0.7123390979973881+ 1.0 * 0.4749096864742145]

Where I0 and I1 are the two inputs taking value 0 or 1.

There's one more implementation here based on the below logic:
(age<18?0:1)

With 10 rounds of training it computes: [f=I0 * 16.558959156129262+ 1.0 * -35.067280281867326]
which is (age<3?0:1), using a higher rounds to training eventually computes the correct function and sometimes even with 10 rounds of training it does calculate the correct function i.e. [f=I0 * 1.9854330663982651+ 1.0 * -33.59209815675304]

Similarly for AND gate it computes the function as f=I0 * 1.9713703472125506+ I1 * 0.9156385055704073+ 1.0 * -1.9007178334879886

For XOR gate however it fails to predict correct values even after 1000 rounds of training. Probably its the activation function which needs to be changed here, here it is using (val<0.5?0:1).
