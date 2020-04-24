[![Build Status](https://travis-ci.org/Acosix/alfresco-rest-client.svg?branch=master)](https://travis-ci.org/Acosix/alfresco-rest-client)

# About

This library aims to provide some common interfaces / classes that could be used to interact with the Alfresco v1 ReST API. The initial, primary goal of this project is to support simpler integration test code for Alfresco extension projects, by allowing the use of the interfaces / classes in combination with generic ReST client libraries that can map a properly annotated API interface to the underlying service endpoints, e.g. similar to the following code snippet using the JBoss RESTEasy JAX-RS Client and Jackson libraries:

```java
ResteasyJackson2Provider resteasyJacksonProvider = new ResteasyJackson2Provider();
ObjectMapper mapper = new ObjectMapper();
mapper.setSerializationInclusion(Include.NON_EMPTY);
resteasyJacksonProvider.setMapper(mapper);

LocalResteasyProviderFactory resteasyProviderFactory = new LocalResteasyProviderFactory(new ResteasyProviderFactory());
resteasyProviderFactory.register(resteasyJacksonProvider);
RegisterBuiltin.register(resteasyProviderFactory);

String alfrescoBaseUrl = "https://example.com/alfresco";
ResteasyClient client = new ResteasyClientBuilder().providerFactory(resteasyProviderFactory).build();
ResteasyWebTarget target = client.target(UriBuilder.fromPath(alfrescoBaseUrl));

AuthenticationV1 authAPI = target.proxy(AuthenticationV1.class);
TicketEntity ticket = authAPI.createTicket(userName, password);
ClientRequestFilter authFilter = (requestContext) -> {
   String base64Token = Base64.encodeBase64String(ticket.getId().getBytes(StandardCharsets.UTF_8));
   requestContext.getHeaders().add("Authorization", "Basic " + base64Token);
};
target.register(authFilter);

NodesV1 nodesAPI = target.proxy(NodesV1.class);
// load node data for relative path "Data Dictionary" below "-root-" node with "path" and "permissions" included
Node dataDictionaryFolder = nodesAPI.getNodeByPath("-root-", "Data Dictionary", Arrrays.asList("path", "permissions"), Collections.emptyList()); 

// load node data for "-shared-" special node with default data
Node sharedFolder = nodesAPI.getNode("-shared-", Collections.emptyList(), Collections.emptyList());
```

In future iterations, this library may provide additional classes to provide a working client library out-of-the-box.
