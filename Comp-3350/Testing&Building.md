# Responses to questions
---
> Are tests present in the repository?
looking through the applications diretories we can see a few different tests having been made.

Specifically we can see that there are tests for the DAO and Jdbc plug-ins. We can also see that tests for the projects models, such as fields and boards do infact contain tests.

---

> If yes, were they runnable? Where do they live and what do they test?
Tests for specifically the models live in the relative path `Model/src/test/java/models` and can infact be run. Running the tests results in no errors. The tests for the models test the functionality of the fields and boards, ensuring that they are working as expected.

---

> If not, what does that suggest about maintainability and risk?
If tests weren't able to be ran we would have to assume that the code is not being tested and therefore may be more prone to bugs and errors. This would suggest that the maintainability of the code is lower and that there is a higher risk of issues arising in the future.

---
> JUnit test implementation
For my test I decided to test the functionality of the BacktrackingSudokuSolver class, specifically the solve method. I created a test class called BacktrackingSudokuSolverTest and implemented test methods `testSolvePartiallyFilledBoard`, `testSolveUnsolvableBoard`, and `testFillBoardDirectly`. These tests check if the solve method can correctly solve a partially filled board, if it can identify an unsolvable board, and if it can fill a board directly with a valid solution. I used assertions to verify that the expected outcomes are met in each test case.

This test can be found in the relative path `Model/src/test/java/solver/BacktrackingSudokuSolverTest.java` and can be run using JUnit to ensure that the BacktrackingSudokuSolver class is functioning correctly.