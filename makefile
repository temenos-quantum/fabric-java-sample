SHELL = /usr/bin/env bash

build: clean buildj copy zip
	echo "Done!"

clean:
	find fabric -name '*.jar' -delete
	find . -name '*.zip' -delete

buildj:
	cd java/HelloWorld && mvn package

copy:
	cp java/**/target/*.jar fabric/FabricJavaSample/Apps/_JARs/

zip:
	zip --recurse-paths --display-bytes FabricJavaSample.zip fabric/FabricJavaSample --exclude \*.DS_Store \*thumbs.db \*.desktop.ini
