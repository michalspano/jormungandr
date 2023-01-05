# Reset the game - reset the contents of the score.json file (Windows)
$PATH_TO_SCORE_FILE="..\src\main\resources\score.json"

"{`"sessionMaxScore`":0}" | Out-File $PATH_TO_SCORE_FILE
Write-Output "Score file reset"