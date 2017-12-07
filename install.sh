#!/bin/bash

serverpath=/var/lib/tomcat8/webapps
basepath=$(dirname $(readlink -f $0))
basedir=$(basename $basepath)
finalpath=$serverpath/$basedir

echo "Packaging war file"
mvn clean package

cd target

echo -e "\nUsing path $finalpath"
echo "Removing $finalpath"
rm -rf $finalpath
mkdir $finalpath

echo "Copying war file to $finalpath"
rsync -zarv --include="*/" --include="*.war" --exclude="*" "$basepath" "$serverpath"
cd $finalpath
