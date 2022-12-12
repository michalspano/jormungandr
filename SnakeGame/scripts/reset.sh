#!/bin/sh

# Reset the game - reset the contents of the score.json file (Unix)
PATH_TO_SCORE_FILE="../src/main/resources/score.json"

echo "{\"sessionMaxScore\":0}" > $PATH_TO_SCORE_FILE
echo "Score file reset"