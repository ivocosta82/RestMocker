#Rest Mocker

The objective of this project is to create an app that can read a RAML or OpenAPI definition and dynamically create
mocked interfaces from the definition.

The plan for now is to use whatever examples are in the definition and if no examples exist build a response using the
defined types

Planned command line use: java RestMocker -p8080 example.raml