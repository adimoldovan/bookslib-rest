#!/bin/bash

serverpath=/var/lib/tomcat8/webapps
basepath=$(dirname $(readlink -f $0))
basedir=$(basename $basepath)

echo "Packaging war file"
mvn clean package
cd target
echo -e "\nUsing path $serverpath"
echo "Copying war file to $serverpath"
rsync -zarv --include="*.war" --exclude="*" "." "$serverpath"
