# foreCastAlexSkillBackend
This is a custom backend logic for Alexa skill that forecasts the retail demand

## Get User Invoice
http://localhost:8080/retailMart/minions@email.com/invoice returns the json with Invoice

## Personalization

* Go to build permissions on alexa developer console and check Customer Name -> Full Name, Customer Email Address
* Provide these permissions to the App by going Web Alexa or Mobile App -> Your Skills -> Open our Skill -> Manage Settings

## Setup Invoice UI
* Start React App with https://github.com/sowmika148/invoice-generation
* https://locahost:3000 prompts to enter your email id linked to Amazon
* Displays Invoice upon valid user email entry when already ordered items
