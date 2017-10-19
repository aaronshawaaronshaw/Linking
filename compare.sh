#!/bin/bash
for file in "test_files/"* ; do
	./readjcf -ed "$file" > "mine.txt"
	./readjcf_ref -ed "$file" > "theirs.txt"
	DIFF=$(diff "mine.txt" "theirs.txt")
	if [ "$DIFF" != "" ] 
	then
    		echo "$file has a difference"
	fi
	
done