### 1)  Introduction

DynamicDiet is an app that changes your diet requirements as you progress and meet your goals. As a user, you will be able to track your daily calories and log your weight change progress. As you get closer and closer to your goals, the app will change your daily caloric intake to ensure that you reach that weight goal.
### 2) Storyboard
https://www.figma.com/file/osIMLBdzWFm4a5e7lnkKaY/DynamicDiet?node-id=2%3A27

### 3) Functional Requirements 
**Requirement 01: Track calories**

**Scenario**: As a user who wishes to lose weight, I want to be able to track the calories that I consume each day so that I can be below my caloric need

**Given** the user tracks their food correctly,

**When** they enter that they ate a chicken breast,

**Then** the result should be Chicken: 180 cals

**Requirement 02: Track weight**

**Scenario:** As a user who wishes to lose weight, I want to be able to track my weight each morning so that I know I am making progress

**Given** the user weighs themselves accurately and consistently

**When** the user inputs their weight

**Then** the application will take and store the entry so the user can see their progress


**Requirement 03: Take progress pictures**

**Scenario**: As a user that wishes to track my progress, I want to be able to have a set of pictures I can use to gauge my progress 

**Given** the phone has a camera and the user utilizes it

**When** the user takes a progress picture

**Then** the app should save it so the user can look back at it


### 4) Class Diagram
![image](https://user-images.githubusercontent.com/31493216/151727610-b3a7e80f-cda8-463c-bed4-76c3d6f3403c.png)


### 5) Class Diagram Description
**MainActivity** - The first screen that the user sees when opening the application

**DietDetail** - The page of the application that displays diet requirements for the day

**Person** - Class that represents an application user

**Diet** - Class that represents the user's diet.  Includes calorie goal for the day

**IPersonDAO** - Interface to retrieve and parse Person data in JSON

**IDietDAO** - Interface to calculate diet/calorie goals

**AppDatabase** - Used to format and store/fetch Person and Diet data from the applications database


### 6) Scrum Roles
**DevOps/Product owner/Git master:** Jimmy Tran

**Frontend developer:** Justin Tran

**Backend developer:** Justin Tracy, David Herier
### 7) Meetings in discord at 8pm Sunday


