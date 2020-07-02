SHELL = /usr/bin/env bash

#############################################################################################
# IMPORTANT: Run 'make conf-git-filters' before adding your user and password to this file. #
#############################################################################################

fabric_dir=fabric
fabric_proj=HelloWorld

java_dir=java
java_proj=HelloWorld

# Compile the Java assets, copy them into the Fabric app and zip it up.
build: clean javac copy zip
	printf "\nDone!\nNow import $(fabric_proj).zip into Fabric.\n\n"

# Run Maven clean and remove all .jar and .zip files from this directory.
clean:
	find $(fabric_dir) -name '*.jar' -delete
	cd $(java_dir)/$(java_proj) && mvn clean
	find . -name '*.zip' -delete

# Compile the Java assets.
javac:
	cd $(java_dir)/$(java_proj) && mvn package

# Copy the JAR files built into the Fabric app's directory.
copy:
	cp $(java_dir)/$(java_proj)/target/*.jar $(fabric_dir)/$(fabric_proj)/Apps/_JARs/

# Zip up the Fabric app for import/upload.
zip:
	cd $(fabric_dir)/$(fabric_proj) && zip --recurse-paths --display-bytes ../../$(fabric_proj).zip * --exclude \*.DS_Store \*thumbs.db \*.desktop.ini

# Avoid pushing your Fabric password to source control.
conf-git-filters:
	git config --global filter.fabric_makefile.clean 'sed -e "s/\(.*fabric_acct.*=\).*/\1/g; s/\(.*fabric_user.*=\).*/\1/g; s/\(.*fabric_pssw.*=

# Note: Set these three as environment variables so that switching branches won't blank them.
# The 9 digit Fabric account ID —e.g.: 100000001
fabric_acct=
# The developer's user for the Fabric Console —e.g.: jimi.hendrix@purple.com
fabric_user=
# The developer's password for the Fabric Console.
fabric_pssw=

show:
	printf "acct: %s\nuser: %s\npssw: %s\n" $(fabric_acct) $(fabric_user) $(fabric_pssw)

# Export/download the Fabric app from the Fabric Console and decompress it to the local file system.
export:
	rm -f $(fabric_proj).zip
	fabric export --account $(fabric_acct) --user $(fabric_user) --password $(fabric_pssw) --app $(fabric_proj) --file $(fabric_proj).zip
	rm -rf fabric/$(fabric_proj)
	unzip $(fabric_proj).zip -d fabric/$(fabric_proj)
	rm $(fabric_proj).zip

# Import/upload the already compressed Fabric app into the Fabric Console.
import:
	fabric import --account $(fabric_acct) --user $(fabric_user) --password $(fabric_pssw) --file $(fabric_proj).zip

# Package the custom hooks to be executed by App Factory.
hooks:
	cd appfactory/hooks && find . -name '*.zip' -delete
	cd appfactory/hooks/pre_build   && zip --recurse-paths --display-bytes ../pre_build.zip   * --exclude \*.DS_Store \*thumbs.db \*.desktop.ini
	cd appfactory/hooks/post_build  && zip --recurse-paths --display-bytes ../post_build.zip  * --exclude \*.DS_Store \*thumbs.db \*.desktop.ini
	cd appfactory/hooks/post_deploy && zip --recurse-paths --display-bytes ../post_deploy.zip * --exclude \*.DS_Store \*thumbs.db \*.desktop.ini
	cd appfactory/hooks && find . -name '*.zip'
