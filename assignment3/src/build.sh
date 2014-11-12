#!/bin/bash
rm assignment3/*.class
FILES=./
for f in assignment3/lib/*.jar
do
  FILES=$FILES:$f
done
javac assignment3/*.java -cp $FILES
