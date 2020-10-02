call runcrud

if "%ERRORLEVEL%" == "0" goto startChrome
echo.
echo runcrud has errors - breaking work
goto fail

:startChrome
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot start Chrome
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.