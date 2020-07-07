# Test API's App Factory Post-Deploy Hook

Once a Fabric application is built and published, this hook can be used to invoke
the published services.

## Run

Just invoke:

    mvn groovy:execute --batch-mode -Ddomain=konycloud.com -Dhost=[host name] -DignoreSSLIssues=true

## Implementation Notes

This project uses [GMaven's Execute goal](https://groovy.github.io/gmaven/groovy-maven-plugin/execute.html#) in order to execute Groovy scripts using the `mvn` command.
