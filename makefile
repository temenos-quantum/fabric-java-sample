SHELL = /usr/bin/env bash

fabric_dir=fabric
fabric_proj=HelloWorld

java_dir=java
java_proj=HelloWorld

build: clean javac copy zip
	printf "\nDone!\nNow import $(fabric_proj).zip into Fabric.\n\n"

clean:
	find $(fabric_dir) -name '*.jar' -delete
	cd $(java_dir)/$(java_proj) && mvn clean
	find . -name '*.zip' -delete

javac:
	cd $(java_dir)/$(java_proj) && mvn package

copy:
	cp $(java_dir)/$(java_proj)/target/*.jar $(fabric_dir)/$(fabric_proj)/Apps/_JARs/

zip:
	zip --recurse-paths --display-bytes $(fabric_proj).zip $(fabric_dir)/$(fabric_proj) --exclude \*.DS_Store \*thumbs.db \*.desktop.ini
