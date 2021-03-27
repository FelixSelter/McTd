@echo off
set /p msg="Commit message? "
git add --all
git status
git commit -m "%msg%"
git push
pause