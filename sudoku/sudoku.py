# sudoku solver
import time
import sys

def check(grid, n, row, col):
    for i in range(0, 9):
        if grid[row][i] == n:
            return False
    for i in range(0, 9):
        if grid[i][col] == n:
            return False
    subRow = (row // 3) * 3
    subCol = (col // 3) * 3
    for i in range(0, 3):
        for j in range(0, 3):
            if grid[subRow+i][subCol+j] == n:
                return False
    return True

def print_grid(grid):
    for row in range(0, 9):
        if row % 3 == 0:
            print("+-------"*3+"+")
        for col in range(0, 9):
            if col % 3 == 0:
                if col == 0:
                    print("|", end='')
                else:
                    print(" |", end='')
            print(" " + str(grid[row][col]), end='')
        print(" |")
    print("+-------"*3+"+")

def solve(grid):
    for row in range(0, 9):
        for col in range(0, 9):
            if grid[row][col] == 0:
                for n in range(1, 10):
                    if check(grid, n, row, col):
                        grid[row][col] = n
                        solve(grid)
                        grid[row][col] = 0
                return
    print_grid(grid)

def main():
    grid = []
    if len(sys.argv) != 2:
        print("No file.")
        exit(1)
    file = open(sys.argv[1])
    for row in range(0, 9):
        grid.append([])
        for col in range(0, 9):
            grid[row].append(int(file.read(2)[0]))
    #print_grid(grid)
    start = time.time()
    solve(grid)
    print("Solved in", "%.3f" % (time.time()-start), "seconds")

if __name__ == '__main__':
    main()
