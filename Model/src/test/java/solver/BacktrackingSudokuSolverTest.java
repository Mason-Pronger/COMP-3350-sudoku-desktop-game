package solver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sudoku.model.exceptions.FillingBoardSudokuException;
import sudoku.model.models.SudokuBoard;
import sudoku.model.solver.BacktrackingSudokuSolver;

public class BacktrackingSudokuSolverTest {
    
    @Test
    public void testSolveEmptyBoard() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        assertDoesNotThrow(() -> solver.solve(board));
        assertTrue(isBoardFilled(board));
        assertTrue(isBoardValid(board));
    }
    
    @Test
    public void testSolvePartiallyFilledBoard() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        // Fill some cells
        board.getField(0, 0).setValue(5);
        board.getField(1, 0).setValue(3);
        assertDoesNotThrow(() -> solver.solve(board));
        assertTrue(isBoardFilled(board));
        assertTrue(isBoardValid(board));
        // Ensure pre-filled values are preserved
        assertEquals(5, board.getField(0, 0).getValue());
        assertEquals(3, board.getField(1, 0).getValue());
    }
    
    @Test
    public void testSolveUnsolvableBoard() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        // Create an unsolvable board by filling conflicting values
        board.getField(0, 0).setValue(1);
        board.getField(1, 0).setValue(1); // Conflict in row
        assertThrows(FillingBoardSudokuException.class, () -> solver.solve(board));
    }
    
    @Test
    public void testFillBoardDirectly() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        assertDoesNotThrow(() -> solver.fillBoard(board));
        assertTrue(isBoardFilled(board));
        assertTrue(isBoardValid(board));
    }
    
    private boolean isBoardFilled(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getField(col, row).getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isBoardValid(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            if (!board.getRow(i).verify() || !board.getColumn(i).verify() || !board.getBox(i).verify()) {
                return false;
            }
        }
        return true;
    }
}
