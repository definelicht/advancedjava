#!/bin/bash
FILES=./
for f in assignment3/lib/*.jar
do
  FILES=$FILES:$f
done
java -ea -cp $FILES assignment3/EmployeeDBTest
