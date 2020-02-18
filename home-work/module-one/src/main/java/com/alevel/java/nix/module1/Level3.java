package com.alevel.java.nix.module1;

public class Level3 {
    public int[][] nextLifeStep(final int[][] lifeBoard) {
        int[][] nextGeneration = new int[lifeBoard.length][lifeBoard[0].length];
        for (int i = 0; i < lifeBoard.length; i++) {
            for (int j = 0; j < lifeBoard[0].length; j++) {
                nextGeneration[i][j] = processObject(lifeBoard, i, j);
            }
        }
        return nextGeneration;
    }

    private int processObject(final int[][] lifeBoard, final int x, final int y) {
        if (x == 0 || x == (lifeBoard.length - 1) || y == 0 || y == (lifeBoard[0].length - 1)) {
            return processBorderObject(lifeBoard, x, y);
        }
        int cell = lifeBoard[x][y];
        int aliveNeighbours = 0;
        if (lifeBoard[x - 1][y] == 1) {
            aliveNeighbours++;
        }
        if (lifeBoard[x + 1][y] == 1) {
            aliveNeighbours++;
        }
        if (lifeBoard[x][y - 1] == 1) {
            aliveNeighbours++;
        }
        if (lifeBoard[x][y + 1] == 1) {
            aliveNeighbours++;
        }
        if (lifeBoard[x - 1][y - 1] == 1) {
            aliveNeighbours++;
        }
        if (lifeBoard[x - 1][y + 1] == 1) {
            aliveNeighbours++;
        }
        if (lifeBoard[x + 1][y - 1] == 1) {
            aliveNeighbours++;
        }
        if (lifeBoard[x + 1][y + 1] == 1) {
            aliveNeighbours++;
        }
        return processLifeCycle(cell, aliveNeighbours);
    }

    private int processBorderObject(final int[][] lifeBoard, final int x, final int y) {
        int aliveNeighbours = 0;
        if (x == 0 && y == 0) {
            if (lifeBoard[x][y + 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y + 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y] == 1) {
                aliveNeighbours++;
            }
            return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
        } else if (x == (lifeBoard.length - 1) && y == (lifeBoard[0].length - 1)) {
            if (lifeBoard[x - 1][y] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x - 1][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x][y - 1] == 1) {
                aliveNeighbours++;
            }
            return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
        } else if (x == 0 && y == (lifeBoard[0].length - 1)) {
            if (lifeBoard[x][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y] == 1) {
                aliveNeighbours++;
            }
            return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
        } else if (x == (lifeBoard.length - 1) && y == 0) {
            if (lifeBoard[x - 1][y] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x - 1][y + 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x][y + 1] == 1) {
                aliveNeighbours++;
            }
            return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
        }

        if (x == 0) {
            if (lifeBoard[x][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y + 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x][y + 1] == 1) {
                aliveNeighbours++;
            }
            return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
        } else if (x == (lifeBoard.length - 1)) {
            if (lifeBoard[x][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x - 1][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x - 1][y] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x - 1][y + 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x][y + 1] == 1) {
                aliveNeighbours++;
            }
            return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
        } else if (y == 0) {
            if (lifeBoard[x - 1][y] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x - 1][y + 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x][y + 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y + 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y] == 1) {
                aliveNeighbours++;
            }
            return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
        } else if (y == (lifeBoard[0].length - 1)) {
            if (lifeBoard[x - 1][y] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x - 1][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y - 1] == 1) {
                aliveNeighbours++;
            }
            if (lifeBoard[x + 1][y] == 1) {
                aliveNeighbours++;
            }
            return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
        }
        return processLifeCycle(lifeBoard[x][y], aliveNeighbours);
    }

    private int processLifeCycle(final int currentState, final int aliveNeighboursCount) {
        if (currentState == 1 && aliveNeighboursCount > 3) {
            return 0;
        } else if (currentState == 1 && aliveNeighboursCount < 2) {
            return 0;
        } else if (currentState == 0 && aliveNeighboursCount != 3) {
            return 0;
        }
        return 1;
    }

    public void print2dArray(int[][] array) {
        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}