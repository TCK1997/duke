@ECHO OFF

setlocal ENABLEDELAYEDEXPANSION
set java11="C:\Program Files\Java\jdk-11.0.4\bin\java.exe"
set fc="C:\Windows\System32\fc"

::create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

::delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

::compile the code into the bin folder
echo ********** BUILDING...   **********
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
) ELSE (
    ::no error here, errorlevel == 0
    echo ********** BUILD SUCCESS **********
)

SET /A "_num = 1"

for %%i in (input_*.txt) do (
    echo using %%i as input now
    %java11% -classpath ..\bin Duke < %%i > ACTUAL_!_num!.txt
    echo generated ACTUAL_!_num!.txt
    echo Checking against EXPECTED_!_num!.txt now ...
    %fc% ACTUAL_!_num!.txt EXPECTED_!_num!.txt
    set /a "_num+=1"
    if exist state.txt del state.txt
)


PAUSE