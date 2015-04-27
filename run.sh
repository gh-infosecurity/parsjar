#!/bin/bash
find /usr/lib/jvm/ -name "jdk1.7*" |while read line; 
do
     STR="export JAVA_HOME=$line"
     echo $STR > source.sh
done
source source.sh
rm -f source.sh
echo $JAVA_HOME
$JAVA_HOME/bin/java -jar target/artifactid-1.0-SNAPSHOT.jar $1
firefox /tmp/index.html
