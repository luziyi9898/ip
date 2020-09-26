# User Guide

This is a quick personal task manager that operates based on text inputs.

##Changes from release v0.1
What's different from release 0.1:
* Implemented find function which allows user to locate a task using its description
* Implemented recognition for date and time.
  * If input for time is in the format `yyyy/mm/dd`, Duke automatically converts it into date format.

## Starting the program

**Using command line**
1. Open command prompt
1. cd into the file directory where the ip.jar file is.
1. Type `java -jar ip.jar`, then Enter to execute
1. Interact with Duke using CLI

##Features

##Listing all tasks `list`
Display the whole list of tasks, as well as their details and indexes.

Format: `list`

##Adding a todo task `todo`
Adds a todo task to the list.

Format: `todo NAME`

Examples: 

* `todo read The King in Yellow`
##Adding a deadline task `deadline`
Adds a deadline task to the list with a date that it should be completed by.

Format: `deadline NAME /by TIME`

Examples:

* `deadline complete proposal /by 2020/11/12`
* `deadline submit proposal /by this friday`
##Adding an event task `event`
Adds an event task to the list with a date that it happens on.

Format: `event NAME /at TIME`

Examples:

* `event cs2101 presentation /at 2020/09/14`
* `event openhouse /at monday to friday`
##Completing a task `done`
Mark a task as done.

Format: `done INDEX` 

Completes the task at the specified `INDEX`. The index is with reference to the index number displaced in the most recent listing.

Examples:

* `done 3`

  Marks the third task as done.
##Deleting a task `delete`
Deletes a task from the list.

Format: `delete INDEX`

Deletes the task at the specified `INDEX`. The index is with reference to the index number displaced in the most recent listing.

Examples:

* `delete 3` 

  Deletes the third task from the list.
##Finding a task `find`
Finds a task from the list.

Format: `find NAME`

The search is case-sensitive, and tasks with names that has matching keyword will be returned.

Examples:

* `find King`
  returns the todo task with the description: read The King in Yellow.

##Terminating the program `bye`
Terminates the program.
Format: `bye`

##Saving the data
Duke automatically saves data into a local storage called `Duke.txt` located in the safe folder as `ip.jar`. 
There is no need to save manually.
If Duke cannot find a save file, it will automatically create a new one.