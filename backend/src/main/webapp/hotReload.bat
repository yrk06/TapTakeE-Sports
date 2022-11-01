@echo off

DEL /S /Q "build/"
echo %CD%
npm "run" "build"
mv "build" "%CD%\..\..\..\target\classes\static"