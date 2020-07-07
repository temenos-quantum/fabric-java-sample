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

println("""
    Host: ${host}
    Domain: ${domain}
    Port: ${port}
    Ignore SSL Issues: ${ignoreSSLIssues}

""")

String service = "hello-world"
String defaultURI = """https://${host}.${domain}:${port}/services/"""
println("defaultURI: ${defaultURI}")
def http = new HTTPBuilder( defaultURI )

//TODO: only do this if a flag for it is passed from the command line.
http.ignoreSSLIssues()

http.request( GET, JSON ) { req ->

    uri.path = "${service}/sayHello"

	println("GET ${uri}")

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
