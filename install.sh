#!/bin/bash

serverpath=/var/lib/tomcat8/webapps
basepath=$(dirname $(readlink -f $0))
basedir=$(basename $basepath)
finalpath=$serverpath/$basedir

echo -e "\nUsing path $finalpath"
echo "Removing $finalpath"
rm -rf $finalpath
mkdir $finalpath

echo "Copying files to $finalpath"
rsync -av --progress $basepath $serverpath --exclude '.git' --exclude '.gitignore' --exclude 'install.sh'
cd $finalpath
echo "Installing"
npm install
echo "Cleaning up garbage in $finalpath"
rm package*.json
