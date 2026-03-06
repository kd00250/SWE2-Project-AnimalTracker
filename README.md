# Project Title

## Description
This system will support wildlife research by allowing scientists, contributors, guests, and administrators to collaborate on tracking and analyzing migration data.

## Installation instructions
how to setup a development environment
1. Unzip the file
2. Go into the nested folder for the code unitl reaching the pom.xml file
3. Click on the pom.xml file and open it in intellij
4. (OPTIONAL: if you wanna run our checkstyle/inspection)
   4A. Download the inspection.xml and the checkstyle.xml from sprint 1 folder
   4B. (For Checkstyle:) Go to settings, then hit the editor dropdown and then hit code style
   4C. Hit the little gear icon next to scheme, then hit import scheme and then choose intellij idea code style XML
   4D. Choose the codestyle that you just downloaded and hit ok, then hit apply to apply it to the project
   4E. (For Inspections:) Go to settings, then hit the editor dropdown and then hit inspections (under code style)
   4F. Hit the gear icon next to profile and select import profile
   4G. Select the downloaded inspection.xml and hit open (it may want you to overwrite the current if so then hit overwrite)
   4H. Hit apply to apply it to the project
   
   
   

## Run instructions
Admin login:
	Username: Billy
	Password: 6767

Scientist login:
	Username: Bob
	Password: 1234

1. In intellij before running the project for the first time we recommend running clean and compile from the maven menu that can be found on the right hand side once your project is open in intellij
2. Go to the main file and run the project

##Test instructions
explain how to run the tests for all portions of the system
1. To run the tests for the project you will want to hover over either java package or the edu.westga.cs3211.animaltracker packages under the initial test package
2. Right click the hovered package and hover over more run/debug to show side options and click "run tests with coverage"
3. You will see the code coverage tool popup to the right of your screen and the test pass/fail will show up in the console area at the bottom of the screen 
