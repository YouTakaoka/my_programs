import Perceptron as pcpt
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

df = pd.read_csv('iris.data', header=None)

y = df.iloc[0:100,4].values
y = np.where(y == 'Iris-setosa', -1, 1)

X = df.iloc[0:100, [0,2]].values

ppn = pcpt.Perceptron(eta=0.1, n_iter=10)
ppn.fit(X, y)

fig = plt.figure()

ax1 = fig.add_subplot(131)
ax2 = fig.add_subplot(132)

ax1.plot(range(1, len(ppn.errors_) + 1), ppn.errors_, marker='o')

ax1.set_xlabel('Epochs')
ax1.set_ylabel('Number of updates')

x1_min, x1_max = X[:, 0].min() - 1, X[:,0].max() + 1
x2_min, x2_max = X[:, 1].min() - 1, X[:,1].max() + 1

ax2.scatter(X[:50,0], X[:50,1], marker='o')
ax2.scatter(X[50:100,0], X[50:100,1], marker='x')

for w in ppn.ws_:
    x1 = np.linspace(x1_min, x1_max, 100)
    x2 = -(w[1]/w[2])*x1 - w[0]/w[2]
    ax2.plot(x1, x2)

plt.show()

