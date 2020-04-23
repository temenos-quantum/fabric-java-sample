# A Sample Fabric Application with Java Custom Code

A dummy Fabric application showcasing a sample Java service, pre-processor and post-processor.

## Repository Structure

This repo uses a [Monorepo](https://en.wikipedia.org/wiki/Monorepo) approach in order to version
both the Fabric application and the source code for its Java dependencies in the same location.

```
.
├── fabric
│   └── FabricJavaSample
└── java
    └── HelloWorld
```

## The Fabric App

The `fabric/FabricJavaSample` subdirectory stores the Fabric application as it would result from exporting and
extracting the Fabric application into a directory by the same name.

## The Java Dependencies

The `java/HelloWorld` subdirectory stores the source code for a Java project which implements a custom service 
`HelloWorldService`, a pre-processor `TimestampPreProcessor` and a post-processor `TimestampPostProcessor` which
are in turn used by the Fabric application stored under `fabric/FabricJavaSample`. It includes:

* HelloWorldService: A service with two operations `sayHello` and `sayGoodbye`, and sample code on how to test them.
* TimestampPreProcessor: A pre-processor which adds a timestamp to the service operation's inputs and headers.
* TimestampPostProcessor: A post-processor which adds a timestamp to the service operation's outputs.
