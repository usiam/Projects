import turtle, pandas as pd
from tkinter import messagebox

screen = turtle.Screen()
screen.title("64 Districts of BD")
screen.setup(width=1.0, height=1.0, startx=None, starty=None)
screen.screensize(705, 1003)

rootwindow = screen.getcanvas().winfo_toplevel()
rootwindow.call('wm', 'attributes', '.', '-topmost', '1')
rootwindow.call('wm', 'attributes', '.', '-topmost', '0')

image = './img/img.gif'
screen.addshape(image)
turtle.shape(image)
screen.tracer(0)


def writeGuess(guess):
    districts = data[data.District == guess]
    corr = (int(districts.x), int(districts.y))
    turtle.penup()
    turtle.goto(corr)
    turtle.write(answerDistrict)
    turtle.goto(0, 0)


data = pd.read_csv('./districts/districts.csv')
totalDistricts = len(data.District)
corrDistricts = 0
guessedDistricts = []

while len(guessedDistricts) < totalDistricts:
    screen.update()
    answerDistrict = screen.textinput(title=f"{corrDistricts}/{totalDistricts} Districts Correct",
                                      prompt="What's another districts' name?").title()
    if answerDistrict in data.District.to_list() and answerDistrict not in guessedDistricts:
        corrDistricts += 1
        guessedDistricts.append(answerDistrict)
        writeGuess(answerDistrict)
    if len(guessedDistricts) == totalDistricts:
        messagebox.showinfo(title="Congrats", message='You guessed all the districts!')
    elif answerDistrict == 'Exit':
        missingDistricts = [district for district in data.District.to_list() if district not in guessedDistricts]
        newData = pd.DataFrame(missingDistricts)
        newData.to_csv("DistrictsToLearn.csv")
        break

screen.mainloop()
