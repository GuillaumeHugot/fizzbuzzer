# FizzBuzzer
An application that :
[first screen] Have a form that accepts five parameters : three integers int1, int2 and limit, and two strings str1 and str2.
[second screen] Display a scrollable screen with a list of strings with numbers from 1 to limit, where: all multiples of int1 are replaced by
str1, all multiples of int2 are replaced by str2, all multiples of int1 and int2 are replaced by str1str2.

## Table of Contents
1. [Choices I made](#choices-i-made)
2. [If I had more time](#if-i-had-more-time)




### Choices I made
* I displayed the result in a recyclerview where each cell process what it should display according to it's number. It allow us to be unaffected by the limit chosen by the user.
* I didn't built any architecture or worked on several git branches as it seemed irrevelant on a project with this size, data flow & future perspectives.
* I took the liberty to interpret some cases, for example if I enter buzz, 5 before fizz, 3, should we display buzzfizz or fizzbuzz ? In a work environment I would have asked what was more relevant to the use case or at least acknowledged the choice I made but here I just added a comment where it seemed justified.
* I commented the key functions only but we may be extend the code coverage if needed, if we want to build an sdk for example.
* I tested the data processing part only. It seemed logical as it is the heart of this application logic.
* This readme is in english even if the target audience is likely to be french natives. It seemed logical as 1. this is a readme and 2. the subject was in english too.


### If I had more time
* we may add some help with the form filling which is a bit annoying for the user.
We could remember what he entered last time and/or suggested some word and period above the keyboard.
Example:

![example of filling suggestion](/readmeimages/example_suggestion.jpg =x200)

* We could build an interface that allows you to add N fizz / buzz words.
Every time you fill a cell with a word and a period, another one pops up bellow with empty fields (or you have a "+" button)

* The help of a designer would be a big plus, the UX isn't that great and on the UI part we're a bit too close to the raw and outdated material design