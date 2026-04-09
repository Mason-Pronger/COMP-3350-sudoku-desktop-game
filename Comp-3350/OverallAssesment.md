# Overall Maintainability Assessment

## Does the system appear actively maintained?

This system does not seem to be actively maintained.

1. Lack of recent commits
2. Technical debt accumulation
3. Incomplete test coverage
4. No evidence of issue tracking or pull requests

## Is technical debt visible?
Yes, technical debt is clearly visible:

- High coupling in the board structure: `SudokuBoard` tightly depends on `SudokuRow`, `SudokuColumn`, and `SudokuBox`. Changing the grid size would require coordinated changes across five or more classes – a classic rigidity smell.

- Controller bloat: `MainMenuController` handles UI events, save/load logic, language switching, and difficulty selection. This violates the Single Responsibility Principle and makes the class a maintenance bottleneck.

- Missing abstractions: The save system lacks an overwrite capability, forcing users to manually delete files. This is a usability debt that also indicates incomplete design.

- Inconsistent test coverage: While `Board` and `Field` have tests, the solver and DAO components have minimal or no automated verification before the student’s intervention.

## Are solid principles repected or violated?

### Respected
- Interface Segreagation: The DAO interface `SudokuBoardDao` is small and only touches what it needs
- Dependancy Inversion: Controllers depend on the `SudokuBoardDao` interface, not concrete file or database classes.

### Violated
- Single responsibility: `MainMenuController` handles UI, persistence, game state, and language setting.
- Open/Closed: adding a new persistence format would require modifying `MainMenuController` because the DAO is instansiated inside the contrller rather injected.
- Interface Segregation: The `SudokuBoard` class exposes many public methods that are only used by the solver and view, increasing the risk of unintended dependencies.

## How difficult would it be to extend this system long-term

This system would be difficult to extend. With the coupling and centralised controll, many files would have to be carefully changed.
Specific challenges could include:

- Adding a new difficulty level
- Changing the board size
- adding a new persistence backend
- Adding a new feature like hints or undo


## Would you recommend incremental improvemnt or a major refactor?

I recommend a major refactor because the current tight coupling and SOLID violations make incremental changes dangerous and testing nearly impossible. However, I did not refactor blindly. First, I would write integration tests. Then, I would refactor in small, verifiable steps starting with dependency injection, then extracting services, and finally simplifying the board model. After each step, I would run the integration tests. This approach balances the need for a strong foundation with the practical realities of legacy system maintenance.