 # Str8ts Solver

 ## Java
> For this project the minimum JDK version "1.8.0.152" is recommended.<br />

**Download:** *http://www.oracle.com/technetwork/java/javase/downloads/index.html*

 ## Str8ts
 >Str8ts is a logic puzzle, invented by Jeff Widderich. It is a grid, partially divided by black cellts into compartments. 
 >Each compartment, vertically or horizontally, must contain a straigt - a set of consecutive numbers, but in any order (for example 2-1-3-4).
 >The aim is to fill all white cells with the numbers from 1 to N (which N is the size of the grid). No single number can repeat in any row or column.
 >Clues in black cells that number as an option in that row and column, and are not part of any straigt.

## Solving Algorithm

 ## Usage
 ### src.application.Main.java
 > Creates and starts the Application.
 > *  Use setArguments() and startApplication() in order to make the JavaFX program run
 
 ### src.application.gui.Application.java
 > Defines basic style of the application including:
 > *  Title of Application
 > *  Size of Application
 > *  Load base Node of Application
 
 ### src.application.gui.controller.ApplicationController.java
 > Handle all events from the model and offers access to all fx:id defined in the *.fxml files, which use that controller.
 > *  Use @FXML to reffer ids or methods to the model
 
 