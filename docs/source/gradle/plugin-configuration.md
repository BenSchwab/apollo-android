---
title: Gradle Configuration 
---

Apollo Android comes with logical defaults that will work for the majority of use cases, below you will find additional configuration that will add Optional Support & Semantic Query Naming.

## Optional Support
By default Apollo-Android will return `null` when a graph api returns a `null` field.  Apollo allows you to configure the generated code to instead use a Guava `Optional<T>` or a shaded`Apollo Optional<T>` rather than simply returning the scalar value or null.

```groovy
apollo {
  nullableValueType = "apolloOptional"  //use one or the other
  nullableValueType = "guavaOptional"   //use one or the other
}
```

## Semantic Naming
By default Apollo-Android expects queries to be written as follows:
```Query someQuery{....}```
alternatively you can turn on Semantic Naming which will allow you to define queries without the Query suffix:
```Query some{....}```

With Semantic Naming enabled you will still see a SomeQuery.java generated same as the first query above.

```groovy
apollo {
  useSemanticNaming = false
}
```

## Java Beans Semantic Naming for Accessors
By default, the generated classes have accessor methods whose names are identical to the name of the Schema field.

```query Foo { bar }```

results in a class signature like:

```
class Foo {
    public Bar bar() { ... }
}
```

Alternatively, turning on Java Beans Semantic Naming will result in those methods being pre-pended with `get` or `is`:

```
class Foo {
    public Bar getBar() { ... }
}
```

```groovy
apollo {
  useJavaBeansSemanticNaming = true
}
```

## Explicit Schema location
By default Apollo-Android tries to lookup GraphQL schema file in `/graphql` folder, the same folder where all your GraphQL queries are stored. 
For example, if query files are located at `/src/main/graphql/com/example/api` then the schema file should be placed in the same location `/src/main/graphql/com/example/api`. Relative path of schema file to `/src/main/graphql` root folder defines the package name for generated models, in our example the package name of generated models will be `com.example.api`.

Alternatively, you can explicitly provide GraphQL schema file location and package name for generated models:

```groovy
apollo {
  sourceSet {
    schemaFile = "/path_to_schema_file/my-schema.json"
  }
  outputPackageName = "com.my-example.graphql.api"
}
```

### Exclude GraphQL files
Apollo Gradle plugin supports GraphQL operations defined in `*.graphql|*.gql` files. You can provide additional configuration to exclude certain GraphQL files by providing file filters. 

```groovy
apollo {
  sourceSet {
    exclude = "**/*.gql"
  }
  outputPackageName = "com.my-example.graphql.api"
}
```

If there is more than one filter:

```groovy
apollo {
  sourceSet {
    exclude = ["**/Query1.graphql", "**/Query2.graphql"]
  }
  outputPackageName = "com.my-example.graphql.api"
}
```

## Visitor generation for polymorphic datatypes
Apollo Gradle plugin also supports generating visitors for compile-time safe handling of polymorphic datatypes. By default the feature is turned off since it requires source/target compatibility with Java 1.8. To opt into visitor generation:
```groovy
apollo {
  generateVisitorForPolymorphicDatatypes = true
}
```

## Kotlin model generation
By default Apollo Gradle plugin generates Java models but you can configure it to generate Kotlin models instead:
```groovy
apollo {
  generateKotlinModels = true
}
```

## Query operation output
When Apollo-Android generates your queries, it generates transforms your query by providing type hint as part of the information sent to the server. It uses this transformed query to generate an id for this operation. If you want to access this information, Apollo Gradle plugin can save them in a build directory in Json format. This can be useful if you need to upload a query's exact content to a server that doesn't support automatic persisted queries.

```groovy
apollo {
  generateOperationOutput = true
}
```

## Custom ID for Persisted Queries

By default, Apollo uses `Sha256` hashing algorithm to generate an ID for the query. To provide a custom ID generation logic, use the option - `operationIdGenerator` which accepts an `instance` that implements the `OperationIdGenerator` interface (`com.apollographql.apollo.compiler.OperationIdGenerator`) as the input. This option can be used to either specify a different Hashing Algorithm or to fetch the persisted query id from a different place - e.g. a service or a CLI.

Example Md5 hash generator:

```groovy
import com.apollographql.apollo.compiler.OperationIdGenerator

apollo {
  operationIdGenerator = new OperationIdGenerator() {
    String apply(String operationDocument, String operationFilepath) {
      return operationDocument.md5()
    }

    /**
     * Use this version override to indicate an update to the implementation.
     * This invalidates the current cache.
     */
    String version = "v1"
  }
}
```

### Versioning Id Generator

The result of the ID generator is cached. The cache is not updated when the implementation of the ID Generator changes. To indicate an update to the implementation of the ID Generator, change the `version` override as shown in the above example.
