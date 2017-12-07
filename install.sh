#!/bin/bash

serverpath=/var/lib/tomcat8/webapps
basepath=$(dirname $(readlink -f $0))
basedir=$(basename $basepath)

echo ">> Packaging war file"
mvn clean package
cd target
echo -e "\n>> Using path $serverpath"
echo -e ">> Stopping tomcat8"
service tomcat8 restart
echo ">> Copying war file to $serverpath"
rsync -zarv --include="*.war" --exclude="*" "." "$serverpath"
chown tomcat8:tomcat8 $serverpath/*.war
echo -e ">> Starting tomcat8"
service tomcat8 start
