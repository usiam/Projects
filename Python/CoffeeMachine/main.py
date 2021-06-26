from menu import Menu
from coffee_maker import CoffeeMaker
from money_machine import MoneyMachine

coffMaker = CoffeeMaker()
menu = Menu()
money = MoneyMachine()

turnOff = False
while not turnOff:
  options = menu.get_items()
  coffeeChoice = input(f"What would you like? {options}: ")
  print("")
  if coffeeChoice == 'off':      
    print("\nMachine turning off now!")
    turnOff = True

  elif coffeeChoice == 'report':
    print("")
    coffMaker.report()
    money.report()
    print("")
  else: 
      drink = menu.find_drink(coffeeChoice)
      cost = drink.cost
      if drink == None:
        print("Try again.\n")     
      else:
        suffResources = coffMaker.is_resource_sufficient(drink)
        paySuccessful = money.make_payment(cost)
        if suffResources and paySuccessful:
            print("")
            coffMaker.make_coffee(drink)
            print("")
        

