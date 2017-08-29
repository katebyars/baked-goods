# Study Abroad Language App: Document Your Learning Abroad

#### Java project for Epicodus, August 2017

#### By Kate Byars

## Description
This App is for the student traveling abroad. Although electronic translators and dictionaries serve a purpose in more traditional settings, the act of learning a language on the go is a different experience. Students can track the words that they have learned, alongside the locations they have visited. The Study Abroad Language App helps you associate what you learned with where you learned it, so you can always access it via your own memory reference points. For example: Can't remember the name of the dish you ate at a new Italian friend's house in Florence, and all the kitchen words her mom taught you? Go to your Study Abroad Language App, where you recorded these words under "Gina's House"; or, access them via a single word, phrase, or alphabetical list.

## Setup/Installation Requirements

* Download and open Postman
* Use the Postman file in this project to test the various paths in App

## Specifications

| Behavior | Example Input | Example Output |
| ------------- | ------------- | ------------- |
|Enter single words into the app along with a definition| Buongiorno | Hello |
|Enter a phrase into the app along with definition|"Ho fame."|"I am hungry"|
|Enter a new language|Italian|Italian|
|See all the words in the app|"All Words"|List of all words|
|See all the phrases in the app  |"All phrases"|List of all phrases|
|See all the languages in the app|"All languages"|List of all the languages|

## Postman
This project has been tested in Postman. Here are examples of some functionalities:

Create a new word:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/postnewword.png)

Create a new language:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/postnewlanguage.png)

Create a new phrase:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/postnewphrase.png)

Get all languages:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/getAllLanguages.png)

Get all words:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/getAllWords.png)

Get all phrases:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/getAllPhrases.png)

Get languages by id:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/getLanguagesById.png)

Get words by id:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/getWordsById.png)

Get phrases by id:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/getPhrasesById.png)

## Technologies Used

Java, Spark, SQL, JSON, GSON, Postman, IntelliJ

## Known Bugs

This project is under construction. It will add the following features in the future:

| Behavior | Example Input | Example Output |
| ------------- | ------------- | ------------- |
|Student can track locations visited|Location: "Gina's mom's house"|Words Learned: "Straciatella"|
|Students can look up a phrase with a single word|"cucchiaio"|"Ho messo il cucchiaio nel armadio"|
|Students can look up a list of words or phrases based on where they learned it|"Gina's House"|"Straciatella, gemelli, cucchiaio"|
|Students can upload photos to words, phrases or locations|Pasta photo| Pasta photo and "Gemelli:"Twin" shaped pasta|
|Professors can pre-load images, words and phrases into the App prior to or during the student's trip|"Studiolo di Federico da Montefeltro" |"Frederick of Mentefeltro's Study" and photo of the study|

You can easily see the present and future database relationships here:
![alt text](https://github.com/katebyars/new-project/blob/master/src/main/resources/public/images/schema.png)


### License

Licensed under GPL

Copyright &copy; 2017 Kate Byars