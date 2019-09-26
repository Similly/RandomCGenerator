# Random C++ Generator

Link to online Java Compiler: https://onlinegdb.com/H12vYotvr

## Errors and problems with the generator

### Variable was not declared

One error why I can't run my generated program is because that some assignment statement try to assign values to variables which had never been declared before.

### Declared variable is never used

The generator generates declaration statement to declare variables. But the declared variables are never used most of the time, so the most variables are completely useless.

### Generated program is useless

The generator can't generate programs which do useful things. That is because the set of statements to generate is limited. The generator can only declare variables and assign values to it and I can't think of any case in which this can create a useful program.


## Detector algorithm for errors

### Variable was not declared

To solve the error with not declared variables I would check at every generated assignment statement if the variable was declared or not. If it was not declared I would just turn the assignment statement into a declaration statement.

### Declared variable is never used

After the generator is finished I would check if there are variables that are never used. If they are never used I would delete its declaration statement.

### Generated program is useless

I don't think there is much I could do against this problem. One option would be to an input and print statements to the generator, because then there would be a chance to generate useful programs.
