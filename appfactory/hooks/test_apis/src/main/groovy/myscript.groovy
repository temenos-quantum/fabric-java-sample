
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
//import static groovyx.net.http.Method.POST

import static groovyx.net.http.ContentType.JSON

String defaultURI = 'https://tpko-dev.konycloud.com:443/services/hello-world/sayHello'
//String defaultURI = 'https://storeonly82x.sit2-konycloud.com:443/services/hello-world/sayHello'
def http = new HTTPBuilder( defaultURI )

//TODO: only do this if a flag for it is passed from the command line.
http.ignoreSSLIssues()

http.request( GET, JSON ) { req ->

    response.success = { resp, json ->
        assert resp.status == 200
        assert json.size() == 4
        println "Query response: " + json
        assert json.message == 'Hello World!'
    }

    // called only for a 404 (not found) status code:
    response.'404' = { resp ->
        println 'Not found'
    }
}
