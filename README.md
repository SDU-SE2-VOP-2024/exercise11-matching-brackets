# **Solutions for** Matching Brackets

Here is a solution for Matching Brackets.  
There are many ways to solve these tasks, and here is presented one such solution.
When looking a these try to understand what has been done, and how the solution compares to
your solution or your thoughts of possible solutions.
It is great to be inspired especially if stuck, but try to get as much learning from this as possible.

Switch to `alternative-solutions` branch to view another implementation.

## Tasks

In this exercise you are tasked with creating a program that can evaluate whether there is a balance of brackets
contained in a string.
An example of this is `[{}]` where there are equally many opening brackets as there are closing.
`[{()]` is an example of no balance between the brackets, as there is a missing closing curly bracket.

### Task 1

Implement the `checkBrackets` method.
The method must be able to check if a string contains a balanced amount of open/closing brackets.
Example:
> `–{ [b ⋅ b – (4 ⋅ a ⋅ c ) ] / (2 ⋅ a }`
> This string should give a `false` value.

A `Stack<Character>` can be used to check for balanced brackets:

- When you see an opening parenthesis, push it on the stack.
- When you see a closing parenthesis, pop the stack.
    - If the opening and closing parentheses don’t match - the parentheses are unbalanced.
    - If at the end the stack is empty - the parentheses are balanced.

`Hint: Consider making it easy to change supported brackets types.`

### Task 2

> **Goal**
> Use a List structure rather than a Stack.

It is important to maintain the **LIFO** (Last In, First Out) approach, when inserting and retrieving values.
As you can imagine this will require some logic which is otherwise built into a Stack.

You are free to create a new class for this purpose, if you don't want to overwrite the existing one. In this
class you need to implement another `checkBrackets`-method.

> Hint: The `List` interface has a method to insert elements a specific index.  
> [See List documentation](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)

![stack](assets/stack.png)

### Bonus Task

For an added challenge:

- The main method should now take a file as an input, instead of input from the keyboard.
