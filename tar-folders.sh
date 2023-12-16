#!/bin/bash

# Check if a directory path is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <directory>"
    exit 1
fi

# Iterate through each folder in the specified directory
for folder in "$1"/*/; do
    folder_name=$(basename "$folder")
    tar czf "${folder_name}.tar.gz" "$folder_name" && echo "Created ${folder_name}.tar.gz"
done