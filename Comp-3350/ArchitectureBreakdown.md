# Architecture Exploration and Reflection

> Describe the architectural style (if any) that appears to be present (e.g., layered, MVC).

The architectural style that is present in this project appears to be a layered architecture has evidence that it is a model view controller (MVC) architecture. The project has three main design patterns that are present in the project, which are the models; sudoku board, fields, and solvers, the views; which are the JavaFX GUI components, and the controllers; which are the classes that handle user input and interact with the models to update the views. This separation of concerns allows for a clear division of responsibilities and promotes maintainability.

---

> How are responsibilities divided across packages/classes?

Responsibilities are divided across packages and classes in a way that promotes separation of concerns. `models` package contains classes that represent the core data structures and logic of the Sudoku game. The `view` package contains classes that handle the user interface, presentation logic, and the controllers handling the user interactions. The `Dao` package contains classes that handle data access and persistence. 

---

> Is there separation between UI and logic? Provide examples.

Yes, there is a separation between UI and logic in this project. The `view` package contains classes that are responsible for the user interface, such as `App.java`, which is the main entry point for the JavaFX application. The `models` package contains classes that represent the core logic of the Sudoku game, such as `Board.java`, `Field.java`, and `BacktrackingSudokuSolver.java`. This separation allows for easier maintenance and scalability, as changes to the UI do not affect the core logic and vice versa. For example, if we wanted to change the way the Sudoku board is displayed, we could modify the SudokuGame.xml file and the corresponding controller without needing to change the underlying logic in the `models` package.

---

> Where is coupling high? Provide at least one specific example (class names + what depends on what).

Coupling is high in the SudokuBoard class, which has tight coupling with the following classes:
- `SudokuRow`
- `SudokuColumn`
- `SudokuBox`
changes that would effect the grid would require changes to each of these classes listed above. This is because the SudokuBoard class relies on these classes to represent the structure of the Sudoku grid, and any changes to the grid would require changes to these classes as well.

---

> Where is cohesion strong or weak? Provide at least one specific example.
Cohesion is strong with for the models and logic of the game, as the classes in the `models` package are focused on representing the core data structures and logic of the Sudoku game. However, cohesion is weak in the `view` package, as it contains a mix of classes that handle both the user interface and the controllers. For example, the `App.java` class is responsible for both initializing the JavaFX application and handling user interactions, which can lead to a lack of cohesion and make it harder to maintain and scale the application.

---

> Reflect on whether the architecture makes maintenance easier or harder. Support your claims with specific evidence from the codebase (file names, class names, call chains, screenshots, diagrams, etc.).

The architecture makes maintenance easier due to modular separation, but harder due to coupling issues:

Easier aspects:

- Clear module boundaries allow independent development
- Dependency injection via Maven modules prevents circular dependencies
- MVC separation allows testing business logic without UI 

Harder aspects:

- The tightly coupled board structure makes refactoring difficult - changing from 9x9 to variable-sized boards would require coordinated changes across SudokuBoard, SudokuRow, SudokuColumn, SudokuBox, and the solver
- Controller bloat in MainMenuController creates a maintenance bottleneck - UI changes, persistence changes, and internationalization all touch the same file