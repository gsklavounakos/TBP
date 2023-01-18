# ToEater
ToEater is a social media that allows its users to share their experience of the restaurants they visited with other users by posting reviews.
## Program execution instructions
Download file from Github repository(If file is in zip form, download and unzip the file).
Open command prompt (cmd).
Type the path where the application is located on your computer in path_of_file.
```bash
cd path_of_file
```
Example: If the app is in Desktop and the file name is TBP-Main, type the following in the command prompt(cmd).
```bash
cd Desktop\TBP-Main
```
Open the app.
```bash
javac *.java
java Main
```
Make sure that you have an sql server on your computer!
## Usage
1. User enters the application by typing in the command line the program execution instructions.
2. User enters the very first page of the application and types one of the following option.
 - By typing "1", User creates a new account by selecting his username, password and type of account (personal or professional usage). 
 - If User chooses "2", User logs in with his passwords, with witch he has registered before.
 - By typing "3", User exits from the app.
3. After register, the app closes and the User has to reopen the app and log in. 
4. After log in, User enters the home page/menu.
 - By typing "1", User can see the different posts of other users. 
 - By typing "2", User can create a new post reviewing a restaurant and rating his expirience.
 - If User chooses "3", User exits from the app.
5. By selecting option 1 in the menu, the user is further prompted to select which feature of the application he want to use.
 - By typing "1", User has the option to like on the post that interests them.
 - By typing "2", User is shown the comments on this post.
 - If User chooses "3", User can leave a comment under this post.
 - By typing "4", User can view the next post.
 - By typing "5", User can return to the home page/menu.
## Presentation of the structure of the repository contents
The repository has 6 branches owned by almost every team member and contains pieces of the program.
Εach branch contains the classes the team has been working on, which now appear in their final form on the main branch.
One of the branches also contains the 3 presentations that were made and elements necessary for them, such as the uml diagram. 
The main branch contains the final version of the application code, the licence and the sql files used for our database.
## UML diagram about code design
![UML diagram](https://github.com/gsklavounakos/TBP/blob/a6a2d88175b2160486bd3f3f77321546d34eb0bb/uml%20diagram.png)

## Overview of data structures and algorithms used
The sql code used to create database:[Create](https://github.com/gsklavounakos/TBP/blob/903c19e611e77b42294f5f4f737fcacb9cfc0489/ergasia%20prog.sql).
The data inserted into the database:[Insert](https://github.com/gsklavounakos/TBP/blob/98af2a3efbf2dff37056fc51cbe67f46ddd1432e/Insert.sql).
