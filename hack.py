from faker import Faker
import pickle
import random
import csv
import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import SVC
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import numpy as np
import pyrebase
import time

# with open('dataH.csv', 'w') as f:
#     f.write('tmp,1,2,3,Out\n')
#
# x = []
#
# with open('dataH.csv', 'a') as f:
#     f.write(str(random.randint(25,40)))
#     f.write(',')
#     for i in range(4):
#         a = str(random.randint(800,1500))
#         x.append(a)
#         f.write(a)
#         f.write(',')
#     f.write('\n')
#
# with open('dataH.csv', 'a') as f:
#     for k in range(299):
#         f.write(str(random.randint(25, 40)))
#         f.write(',')
#         for j in x[-3:]:
#             f.write(str(j))
#             f.write(',')
#         b = str(random.randint(800, 1500))
#         x = x[-3:]
#         x.append(b)
#         f.write(b)
#         f.write('\n')

df = pd.read_csv('dataH.csv', index_col=False)

X = df.drop(columns=['Out'])
Y = df[['Out']]
# print(X)
# print(Y)
x = np.array(X)
y = np.array(Y)

X_train, X_test, y_train, y_test = train_test_split(x, y, test_size=0.33)
# print(X_train.shape)
# rf = RandomForestClassifier(n_estimators=70)

# rf.fit(X_train, y_train.ravel())
# for (a,b) in zip(y_test.ravel(),rf.predict(X_test)):
#     print(a,b)
# print(disp = view.findViewById(R.id.eai_tv);)
# print(accuracy_score(Y, sv.predict(X)) * 100)
# print(sv.predict(X_train))
# print(sv.score(X_test,y_test.ravel()))

config = {
  "apiKey": "AIzaSyDOU6Oy5nzeDNAIUQbYEEohw27xrygZMTY",
  "authDomain": "hack-9a49e.firebaseapp.com",
  "databaseURL": "https://hack-9a49e.firebaseio.com",
  "storageBucket": "hack-9a49e.appspot.com"
}

firebase = pyrebase.initialize_app(config)
db = firebase.database()

while True:
    print('running...')
    if(db.child('eAI').child('Activate').get().val() == 1):

        sv = SVC(kernel='rbf', C=1e3, gamma=0.1)
        sv.fit(X_train, y_train.ravel())

        print('updating Energy Values')
        v1 = db.child('eAI').child('Month1').get().val()
        v2 = db.child('eAI').child('Month2').get().val()
        v3 = db.child('eAI').child('Month3').get().val()
        v4 = db.child('eAI').child('Temp').get().val()

        _X = np.array([v4, v1, v2, v3]).reshape(1,4)
        print(_X)
        _Y = sv.predict(_X).tolist()
        print(_Y)
        db.child('eAIUpdates').set(str(_Y[0]))
        print('EB Updated')
        db.child('eAI').child('Activate').set(0)
        print('eAI Disabled')
        _Y = 0