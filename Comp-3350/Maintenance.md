# Identifying a Maintenance Opportunity 

Based on the repository structure, the current save system probably creates a new file each time or prevents overwriting an existing save slot. This is a design flaw that reduces useability

## Problem Description

Users cannot overwrite an existing saved game file when saving. Instead, they are forced to delete the old save manually or the application throws an error. The desired behavior, when a user chooses an existing save slot, the system should replace the old save with the new board state after a confirmation prompt

## Affected Classes
- `FileSudokuBoardDao.java` Will need to be changed to allow the user to overwrite a file
- `MainMenuController` contains the “Save Game” action and the logic that triggers the DAO.
- `GameController.java` Might need to be adjusted to allow for this.

## Architectural Risk

- High coupling between MainMenuController and the DAO: the controller currently decides the save flow. Changing the DAO’s write method to support overwriting could break existing controller logic that expects a new file.
- Risk of data loss if overwrite is implemented without a confirmation step. Users might accidentally replace a wanted save.
- Lack of atomic write: if the DAO simply deletes then writes, a crash between deletion and writing would corrupt the save. Need to write to a temporary file first, then replace.
- Missing tests: no automated tests for save/load behaviour, making it hard to verify that overwriting doesn’t introduce regression.

## Seam

I would implement an overloaded method `save(Board board, String filename, boolean overwrite)` in FileSudokuBoardDao. The existing save(Board, String) calls this with `overwrite=false`, preserving old behaviour. The new method provides a seam: the DAO can be replaced or mocked, and atomic write logic (temp file + move) can be tested independently.