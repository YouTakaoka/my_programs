import Perceptron as pcpt
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

def plot_decision_boundary(X, y, classifier, label1='setosa', label2='versicolor', xlabel='sepal length [cm]', ylabel='petal length [cm]'):
    fig = plt.figure()    
    
    ax1 = fig.add_subplot(131)
    ax2 = fig.add_subplot(133)

    ax1.plot(range(1, len(classifier.errors_) + 1), classifier.errors_, marker='o')

    ax1.set_xlabel('Epochs')
    ax1.set_ylabel('Number of updates')

    x1_min, x1_max = X[:, 0].min() - 1, X[:,0].max() + 1
    x2_min, x2_max = X[:, 1].min() - 1, X[:,1].max() + 1

    ax2.scatter(X[:50,0], X[:50,1], marker='o', label=label1)
    ax2.scatter(X[50:100,0], X[50:100,1], marker='x', label=label2)

    cnt=0
    for w in ppn.ws_:
        print(w)
        x1 = np.linspace(x1_min, x1_max, 100)
        x2 = -(w[1]/w[2])*x1 - w[0]/w[2]

        col = [cnt/len(ppn.ws_), 0, 1-cnt/len(ppn.ws_)]
        ax2.plot(x1, x2, color=col)
        cnt += 1

    ax2.legend()

    return fig

    
if __name__ == "__main__":
    df = pd.read_csv('iris.data', header=None)

    y = df.iloc[0:100,4].values
    y = np.where(y == 'Iris-setosa', -1, 1)

    X = df.iloc[0:100, [0,2]].values

    ppn = pcpt.Perceptron(eta=0.1, n_iter=10, random_state=None)
    ppn.fit(X, y)

    fig = plot_decision_boundary(X,y,ppn)
    plt.show(fig)
