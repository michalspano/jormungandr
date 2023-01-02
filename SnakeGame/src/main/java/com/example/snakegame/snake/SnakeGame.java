/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code SnakeGame.java} - core of the game functionality
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame.snake;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.example.snakegame.InstantiateScenes;
import com.example.snakegame.SnakeGameUtils;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * The type Snake game.
 */
public class SnakeGame extends Application {
    // global constants
    public static final int ENEMY_COUNT = 5;
    public static final int BLOCK_COUNT = 5;
    public static final int BLOCK_SIZE = 3;

    // parsed from the config file
    private final int INITIAL_SNAKE_SIZE;
    private final int CELL_SIZE;
    private final int ROW_COUNT;
    private final int COLUMN_COUNT;
    private final int speed;

    // assigned in the constructor
    private boolean isPreloaded;
    private int foodX, foodY; // initial both 0
    private boolean gameOver; // initial false
    private int currentScore; // initial 0
    private final int MAX_SCORE;
    private final int UPPER_PADDING;
    private SnakeDirection direction;
    private final List<GridPiece> SNAKE;
    private final List<GridPiece> ENEMY_LIST;
    private final List<GridPiece> BLOCKS_LIST;

    /**
     * Instantiates a new Snake game.
     *
     * @param initialSnakeSize the initial snake size
     * @param cellSize         the cell size
     * @param rows             the rows
     * @param columns          the columns
     * @param speed            the speed
     * @param upperPadding     the upper padding of the canvas
     */
    public SnakeGame(int initialSnakeSize, int cellSize, int rows, int columns, int speed, int upperPadding) {
        this.INITIAL_SNAKE_SIZE = initialSnakeSize;
        this.CELL_SIZE          = cellSize;
        this.ROW_COUNT          = rows;
        this.COLUMN_COUNT       = columns;
        this.speed              = speed;

        this.MAX_SCORE          = SnakeGameUtils.getOverallMaxScore();
        this.UPPER_PADDING      = upperPadding;
        this.direction          = SnakeDirection.DOWN; // default direction - go UP
        this.SNAKE              = new ArrayList<>();
        this.ENEMY_LIST         = new ArrayList<>();
        this.BLOCKS_LIST        = new ArrayList<>();
    }

    /**
     * Instantiates a new Snake game (with the states pre-set).
     * This is used in the preloading of the game with states from the config file.
     *
     * @param initialSnakeSize the initial snake size
     * @param cellSize         the cell size
     * @param rows             the rows
     * @param columns          the columns
     * @param speed            the speed
     * @param upperPadding     the upper padding
     * @param sessionScore     the session score
     * @param direction        the direction
     * @param snake            the snake
     * @param enemyList        the enemy list
     * @param blockList       the blocks list
     */
    public SnakeGame(int initialSnakeSize, int cellSize, int rows, int columns, int speed, int upperPadding,
                     int sessionScore, SnakeDirection direction, int foodX, int foodY, List<GridPiece> snake,
                     List<GridPiece> enemyList, List<GridPiece> blockList) {

        this.INITIAL_SNAKE_SIZE = initialSnakeSize;
        this.CELL_SIZE          = cellSize;
        this.ROW_COUNT          = rows;
        this.COLUMN_COUNT       = columns;
        this.speed              = speed;
        this.isPreloaded        = true;

        this.MAX_SCORE          = sessionScore;
        this.UPPER_PADDING      = upperPadding;
        this.direction          = direction;
        this.foodX              = foodX;
        this.foodY              = foodY;
        this.SNAKE              = snake;
        this.ENEMY_LIST         = enemyList;
        this.BLOCKS_LIST        = blockList;

        // SnakeGameUtils.TODO();
    }

    /* initial setup of the game parameters */
    /**

     This method sets the initial game states for the Snake game.
     It first adds the initial snake pieces to the SNAKE list.
     It then generates a new consumable and enemy for the game.
     Finally, it generates the block terrain for the game.
     */
    private void setInitialGameStates() {
        for (int i = this.INITIAL_SNAKE_SIZE; i > 0; i--) {
            SNAKE.add(new GridPiece(0, i));
        }

        generateNewConsumable();
        generateNewEnemy();
        generateBlockTerrain();
    }

    // this method ensures that no two objects from the game (with randomised coordinates) are in the same position
    private int[] generateRandomNonOverlappingGridPosition() {
        int x, y;
        do {
            x = SnakeGameUtils.random.nextInt(this.ROW_COUNT);
            y = SnakeGameUtils.random.nextInt(this.UPPER_PADDING, this.COLUMN_COUNT); // vertical upper padding

        } while (SnakeGameUtils.detectCollisionOverIterable(x, y, SNAKE)      ||
                SnakeGameUtils.detectCollisionOverIterable(x, y, ENEMY_LIST)  ||
                SnakeGameUtils.detectCollisionOverIterable(x, y, BLOCKS_LIST) ||
                (x == foodX && y == foodY));
        return new int[] { x, y };
    }

    private void generateNewConsumable() {
        int[] randomPosition = generateRandomNonOverlappingGridPosition();
        foodX = randomPosition[0];
        foodY = randomPosition[1];
    }

    private void generateNewEnemy() {
        int enemySize = SnakeGameUtils.random.nextInt(ENEMY_COUNT) + 1;
        ENEMY_LIST.clear();

        int x, y;
        for (int i = 0; i < enemySize; i++) {
            int[] position = generateRandomNonOverlappingGridPosition();
            x = position[0];
            y = position[1];

            ENEMY_LIST.add(new GridPiece(x, y));
        }
    }

    private void generateBlockTerrain() {
        int clusterCount = SnakeGameUtils.random.nextInt(BLOCK_COUNT) + 1;
        int blockCount;
        int x, y;

        final int[] POSITIONS = new int[] { -1, 0, 1 };

        for (int i = 0; i < clusterCount; i++) {
            blockCount = SnakeGameUtils.random.nextInt(BLOCK_SIZE) + 1;

            int[] position = generateRandomNonOverlappingGridPosition();
            x = position[0];
            y = position[1];

            BLOCKS_LIST.add(new GridPiece(x, y));

            for (int j = 0; j < blockCount; j++) {

                // generate offsets for both x, y
                int[] offset = new int[2];

                while (offset[0] == 0 && offset[1] == 0) {
                    offset[0] = POSITIONS[SnakeGameUtils.random.nextInt(POSITIONS.length)];
                    offset[1] = POSITIONS[SnakeGameUtils.random.nextInt(POSITIONS.length)];
                }

                // assign new x, y
                x += offset[0];
                y += offset[1];

                BLOCKS_LIST.add(new GridPiece(x, y));
            }
        }
    }

    public void start(Stage primaryStage) {
        Group root = new Group();

        Canvas canvas = new Canvas(this.ROW_COUNT * this.CELL_SIZE, this.COLUMN_COUNT * this.CELL_SIZE);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root, this.ROW_COUNT * this.CELL_SIZE, this.COLUMN_COUNT * this.CELL_SIZE);
        scene.setFill(Color.BLACK);

        // set the initial game states (for not preloaded games)
        if (!isPreloaded) {
            setInitialGameStates();
        }

        // control the snake with the keystrokes
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            switch (key.getCode()) {
                case W, UP      -> direction = SnakeDirection.UP;
                case S, DOWN    -> direction = SnakeDirection.DOWN;
                case A, LEFT    -> direction = SnakeDirection.LEFT;
                case D, RIGHT   -> direction = SnakeDirection.RIGHT;

                default -> System.out.println("Invalid key");
            }
        });

        double timeToWait = Math.pow(10, 9) / speed;
        new AnimationTimer() {
            long lastTick;

            public void handle(long currentTick) {
                if (gameOver) {
                    this.stop();

                    // delete the canvas from the root
                    root.getChildren().remove(canvas);
                    scene.setFill(Color.BLACK);

                    // check if the score is a new high score, if so, update the JSON file
                    if (currentScore > MAX_SCORE) {
                        SnakeGameUtils.updateGameSession(currentScore);
                    }

                    InstantiateScenes instantiateScenes = new InstantiateScenes();

                    try {
                        instantiateScenes.instantiateGameOverScene(primaryStage, currentScore);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return;
                }
                if (currentTick - lastTick > timeToWait) {
                    lastTick = currentTick;
                    tick(graphicsContext);
                }
            }
        }.start();

        primaryStage.setTitle("The Snake Game");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * Tick - a single iteration of the game loop
     *
     * @param gc the graphics context
     */
    public void tick(GraphicsContext gc) {
        // this for-loop ensures that the snake makes the illusion that it is moving
        // starting from the tail, each snake piece takes the position of the snake piece in front of it (an index lower)
        for (int i = SNAKE.size() - 1; i >= 1; i--) {
            SNAKE.get(i).setX(SNAKE.get(i - 1).getX());
            SNAKE.get(i).setY(SNAKE.get(i - 1).getY());
        }

        final GridPiece HEAD = SNAKE.get(0); // per each tick, there is only one head - constant
        switch (direction) {
            case UP     -> HEAD.setY(HEAD.getY() - 1);
            case DOWN   -> HEAD.setY(HEAD.getY() + 1);
            case LEFT   -> HEAD.setX(HEAD.getX() - 1);
            case RIGHT  -> HEAD.setX(HEAD.getX() + 1);
        }

        // detect if the snake is out of the screen
        // -1 in the upper bounds of the width, height is because the snake is drawn from the top left corner
        if (SNAKE.get(0).getX() < 0 || SNAKE.get(0).getX() > this.ROW_COUNT - 1 || SNAKE.get(0).getY() < 0 || SNAKE.get(0).getY() > this.COLUMN_COUNT - 1) {
            gameOver = true;
            return;
        }

        // detection if the snake eats the consumable
        if (foodX == HEAD.getX() && foodY == HEAD.getY()) {

            GridPiece tail = SNAKE.get(SNAKE.size() - 1);
            SNAKE.add(new GridPiece(tail.getX(), tail.getY()));

            // increase the speed after each food is eaten [optional]
            // speed++;

            currentScore++;
            generateNewConsumable();
            generateNewEnemy();
        }

        // iterate over snake.size(), skip [0] in an anonymous function
        SNAKE.stream().skip(1).forEach(piece -> {
            if (HEAD.getX() == piece.getX() && HEAD.getY() == piece.getY()) {
                System.out.println("You ate yourself!");
                gameOver = true;
            }
        });

        // collision with enemy
        if (SnakeGameUtils.detectCollisionOverIterable(HEAD.getX(), HEAD.getY(), ENEMY_LIST)) {
            System.out.println("You ate an enemy!");
            gameOver = true;
            return;
        }

        // collision with blocks
        if (SnakeGameUtils.detectCollisionOverIterable(HEAD.getX(), HEAD.getY(), BLOCKS_LIST)) {
            System.out.println("You hit a block!");
            gameOver = true;
            return;
        }

        SnakeGameUtils.drawImage(gc, "gameBackground", 0, 0,
                this.ROW_COUNT * this.CELL_SIZE, this.COLUMN_COUNT * this.CELL_SIZE);

        // default system font
        String fontName = Font.getFamilies().get(0);

        // TODO: make the score display more appealing

        // display the current score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(fontName, 30));
        gc.fillText("Score: " + currentScore, 20,
                                        this.CELL_SIZE);

        // display the high score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(fontName, 30));
        gc.fillText("High Score: " + MAX_SCORE, 20,
                                        this.CELL_SIZE * 2);

        // draw the snake
        String currentImageStr;
        for (int i = 0; i < SNAKE.size(); i++) {
            if (i == 0) {
                currentImageStr = "head";
            } else if (i < SNAKE.size() - currentScore) {
                currentImageStr = "body";
            } else {
                currentImageStr = "enemyBody";
            }

            // draw the parts of the snake
            SnakeGameUtils.drawImage(gc, currentImageStr,
                    SNAKE.get(i).getX() * this.CELL_SIZE,
                    SNAKE.get(i).getY() * this.CELL_SIZE,
                    this.CELL_SIZE, this.CELL_SIZE);
        }

        // draw the consumable
        SnakeGameUtils.drawImage(gc, "food",
                foodX * this.CELL_SIZE,
                foodY * this.CELL_SIZE,
                this.CELL_SIZE, this.CELL_SIZE);

        // draw the enemies
        for (GridPiece ep : ENEMY_LIST)
            SnakeGameUtils.drawImage(gc, "enemy",
                    ep.getX() * this.CELL_SIZE,
                    ep.getY() * this.CELL_SIZE,
                    this.CELL_SIZE, this.CELL_SIZE);

        // draw the blocks
        for (GridPiece bp : BLOCKS_LIST)
            SnakeGameUtils.drawImage(gc, "block",
                    bp.getX() * this.CELL_SIZE,
                    bp.getY() * this.CELL_SIZE,
                    this.CELL_SIZE, this.CELL_SIZE);
    }

    // TODO: add getters and setters

    public static void instantiate(String[] args) {
        launch(args);
    }
}