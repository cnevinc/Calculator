# Calculator

An Android Calculator app using Kotlin.

## Feature
1. Addtion, subtraction, division, multiplication, equal, AC
2. The bahvior of equal button is similar to Android Calculator (click "=" button multple times won't re-calculate the euqation)
3. "+/-", "." are not supported
4. Currently on support Integer input/output.


## TODO
1. Use BigDecimal directly here.We don't want to loose the precision when we calculate the money.
2. Use LiveData and keep the state
3. Make input a command pattern so no need to check type here.


## Architecure
There's no architecture right now. I only want to try using state machine to immutate the state;

    
    
    
                                 |<-----(input[=])---- \    |<----------(back to OP STATE)-------------------|
                                 |                      \   |                                                |
                                 |                       \  |                                                |
                                 |                        \ |                                                |
                                 |                         \|                                                |
      Start ---(input[0-9])----> X ---(input[OPERATOR])---> OP ---(input[0-9])---> Y ---(input[OPERATOR])--->|
        |                        |                          |                      |
       input                   input                      input                  input
      OPERATOR                 [0-9]                      [+-*\/]                [0-9]
       stays                   stays                      stays                  stays
    
