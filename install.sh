#!/bin/bash

serverpath=/var/lib/tomcat8/webapps
basepath=$(dirname $(readlink -f $0))
basedir=$(basename $basepath)
appname=bookslib-rest

echo ">> Packaging war file"
mvn clean package -DfinalName=$appname
cd target
echo -e "\n>> Using path $serverpath"
echo -e ">> Stopping tomcat8"
service tomcat8 stop
echo ">> Removing existing app"
rm -rf $serverpath/$appname
rm -rf $serverpath/$appname.war
echo ">> Copying war file to $serverpath"
rsync -zarv --include="*$appname.war" --exclude="*" "." "$serverpath"
chown tomcat8:tomcat8 -R $serverpath
#chown tomcat8:tomcat8 $serverpath/*.war
echo -e ">> Starting tomcat8"
service tomcat8 start
