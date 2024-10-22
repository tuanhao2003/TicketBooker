:start
chcp 65001
color a
title Quản Lý Git
set dir=%~dp0
cd %dir%
echo off
cls

:run
echo #####################################################################
echo #                                                                   #
echo #  Chọn chế độ:                                                     #
echo #          - init nhấn 0                                            #
echo #          - pull nhấn 1                                            #
echo #          - push nhấn 2                                            #
echo #          - reset branch nhấn 3                                    #
echo #          - clone nhấn 4                                           #
echo #          - fix setup stream error nhấn 5                          #
echo #          - clear reponsitory (còn lịch sử) nhấn 6                 #
echo #          - push lên nhánh mới nhấn 7                              #
echo #          - Tự gõ lệnh nhấn t                                      #
echo #                                                                   #
echo #####################################################################
choice /c t01234567 > nul
set "mode=%errorlevel%"
if "%mode%"=="1" goto handtype
if "%mode%"=="2" goto init
if "%mode%"=="3" goto pull
if "%mode%"=="4" goto push
if "%mode%"=="5" goto reset
if "%mode%"=="6" goto clone
if "%mode%"=="7" goto setup
if "%mode%"=="8" goto delall
if "%mode%"=="9" goto newbranch


:handtype
set /p "commandTyped=Lệnh: "
%commandTyped%
goto continue

:init
cls
echo #############
echo # init mode #
echo #############
git.exe init
git branch -M main
set /p originUrl=Nhập git url:
git.exe remote add origin "%originUrl%"
git.exe add .
git.exe commit -m "first push"
git.exe push --set-upstream origin main
echo Đã kết nối với github %originUrl%
goto continue


:pull
cls
echo #############
echo # pull mode #
echo #############
call :backup
set pullLog=
for /f "delims=" %%i in ('git.exe pull 2^>^&1') do (set pullLog=%%i)
if "x%pullLog:Already=%"=="x%pullLog%" if "x%pullLog:changed=%"=="x%pullLog%" goto pullErrHandle
echo Đã cập nhật về thiết bị
goto continue

:clone
cls
echo ##############
echo # clone mode #
echo ##############
set /p "linkClone=Nhập url github cần clone:"
set startGet=
set folderName=
set cloneLog=
for /f "delims=" %%i in ('git.exe clone %linkClone% 2^>^&1') do (set cloneLog==%%i)
for /f "tokens=1 delims='" %%s in ("%cloneLog%") do (set startGet=%%s)
for /f "tokens=2 delims='" %%e in ("%cloneLog:~%startGet%,-1%") do (set folderName=%%e)
echo Đã lấy code về thiết bị
call :copyManageFile "gitManage.bat" "%folderName%\gitManage.bat" 
goto continue

:push
cls
echo #############
echo # push mode #
echo #############
call :backup
set /p msgpush=Nhập commit:
if not defined msgpush goto push
git.exe add .
git.exe commit -m "%msgpush%"
set pushLog=
for /f "delims=" %%i in ('git.exe push 2^>^&1') do (set pushLog=%%i)
if "x%pushLog:->=%"=="x%pushLog%" if "x%pushLog:up-to-date=%"=="x%pushLog%" goto pushErrHandle
echo Đã cập nhật lên Github
goto continue


:reset
cls
echo ##############
echo # reset mode #
echo ##############
set /p commitId=Nhập commit id:
if not defined commitId goto reset
set /p branch=Nhập branch name:
git.exe reset --hard %commitId%
git.exe push -f origin %branch%
echo Đã reset về commit %id%, nhớ nhắc mọi người clone code mới
goto continue

:setup
cls
echo ############
echo # fix mode #
echo ############
echo Hãy nhập tên nhánh mặc định(thông thường là main):
set /p branchDefault=Tên nhánh: 
git.exe branch --set-upstream-to=origin/main "%branchDefault%"
goto continue

:err
echo #######################
echo # Lỗi, thử cách khác? #
echo #######################
echo (Y/N):
choice /c yn > nul
set "mode=%errorlevel%"
if "%mode%"=="1" goto start
if "%mode%"=="2" goto stop
echo Nhập đúng định dạng
goto err

:pullErrHandle
echo ##############################
echo # Lỗi! Hãy commit code trước #
echo ##############################
set /p msgpull=Nhập commit:
if not defined msgpull goto pullErrHandle
git.exe add .
git.exe commit -m "%msgpull%"
git.exe pull
set pullLog=
for /f "delims=" %%i in ('git.exe pull 2^>^&1') do (set pullLog=%%i)
if "x%pullLog:Already=%"=="x%pullLog%" if "x%pullLog:changed=%"=="x%pullLog%" goto err
echo Đã cập nhật về thiết bị
goto continue

:pushErrHandle
echo ############
echo # Lỗi push #
echo ############
echo Hãy nhập tên nhánh cần push(thông thường là main):
set /p branchName=Tên nhánh:
if not defined branchName goto pushErrHandle
git.exe pull
git.exe push origin %branchName%
set pushLog=
for /f "delims=" %%i in ('git.exe push 2^>^&1') do (set pushLog=%%i)
if "x%pushLog:->=%"=="x%pushLog%" if "x%pushLog:up-to-date=%"=="x%pushLog%" goto err
echo Đã cập nhật lên Github
goto continue

:delall
setlocal enabledelayedexpansion
cls
echo ##########################
echo # Delete Repository Mode #
echo ##########################
set /p "resetCommit=Nhập commit:"
if not defined resetCommit goto delall
set /p "needResetBracnh=Nhập tên nhánh:"
git.exe checkout --orphan new-branch
git.exe add -A
git.exe commit -m "%resetCommit%"
git.exe branch -D %needResetBracnh%
git.exe branch -m %needResetBracnh%
git.exe push -f origin %needResetBracnh%
endlocal
goto continue

:newbranch
setlocal enabledelayedexpansion
cls
echo ########################
echo # New Branch Push Mode #
echo ########################
set /p "newBranchName=Nhập tên nhánh mới:"
if not defined newBranchName goto newbranch
set /p "newBranchCommit=Nhập commit:"
if not defined newBranchCommit goto newbranch
git.exe checkout -b %newBranchName%
git.exe add .
git commit -m "%newBranchCommit%"
git push -u origin %newBranchName%
endlocal
goto continue

:copyManageFile
setlocal enabledelayedexpansion
copy /Y %1 %2
endlocal
goto :eof

:backup
setlocal enabledelayedexpansion
set root=%cd%
cd ..
set "bu=%root%BackUp"
echo %root%^\^* %bu%^\
xcopy %root%^\^* %bu%^\ /E /Y /I /H
cd %pwd%
endlocal
goto :eof

:continue
echo #############
echo # Tiếp tục? #
echo #############
echo (Y/N):
choice /c yn > nul
set "mode=%errorlevel%"
if "%mode%"=="1" goto start
if "%mode%"=="2" goto stop
echo Nhập đúng định dạng
goto continue

:stop
exit