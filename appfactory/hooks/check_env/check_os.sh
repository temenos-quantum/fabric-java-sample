#!/bin/bash
printf "\n\nChecking OS...\n"

#Linux
lsb_release -a
cat /etc/redhat-release || cat /etc/*-release || cat /etc/issue
uname -a

#MacOS
sw_vers
