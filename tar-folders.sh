#!/bin/bash

# Check if a directory path is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <directory>"
    exit 1
fi

# Change to the specified directory
cd "$1" || exit 1

# Iterate through each subdirectory
for folder in */; do
    folder_name=$(basename "$folder")
    tar -czf "${folder_name}.tar.gz" "$folder_name" && echo "Created ${folder_name}.tar.gz"
done
