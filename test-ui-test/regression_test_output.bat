@ECHO OFF

setlocal ENABLEDELAYEDEXPANSION
SET /A "_num = 1"

for %%i in (input_*.txt) do (
    if not exist EXPECTED_!_num!.txt (
        move ACTUAL_!_num!.txt EXPECTED_!_num!.txt
        echo EXPECTED_!_num!.txt generated
    ) else (
        echo EXPECTED_!_num!.txt already generated
    )
    set /a "_num+=1"
)

PAUSE