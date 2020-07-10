import groovyjarjarcommonscli.MissingArgumentException
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.JSON

String host = System.properties["host"] // "tpko-dev"

if(!host) throw new MissingArgumentException("""\n
    IMPORTANT: This script requires you to use -Dhost=[host name]\n
""")

String domain = System.properties["domain"] ?: "konycloud.com" //sit2-konycloud.com
String port = System.properties["port"] ?: "443"
Boolean ignoreSSLIssues = System.properties["ignoreSSLIssues"] ?: false

String service = "hello-world"
String defaultURI = """https://${host}.${domain}:${port}/services/"""

println("""
    Host: ${host}
    Domain: ${domain}
    Port: ${port}
    Ignore SSL Issues: ${ignoreSSLIssues}
    Service: ${service}
    Base URL: ${defaultURI}

""")

def http = new HTTPBuilder( defaultURI )

//TODO: only do this if a flag for it is passed from the command line.
http.ignoreSSLIssues()

def getAndAssert = { operation, count, status, result ->
    http.request( GET, JSON ) { req ->

        uri.path = "${service}/${operation}"

        println("GET ${uri}")

        response.success = { resp, json ->
            assert resp.status == status
            assert json.size() == count
            println "Query response: " + json
            assert json.message == result
        }

        // called only for a 404 (not found) status code:
        response.'404' = { resp ->
            println 'Not found'
        }
    }
}

getAndAssert("sayHello", 4, 200, "Hello World!")
getAndAssert("sayGoodbye", 4, 200, "Goodbye World!")
getAndAssert("sayNonsense", 4, 200, "No such operation!")
getAndAssert("", 2, 200, null)

