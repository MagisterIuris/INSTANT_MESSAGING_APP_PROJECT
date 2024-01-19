# BuzzChat project

Welcome. This is BuzzChat, an instant messaging app that I've worked on this year. Enjoy your visit! 

## Server

The server is a Spring Boot app that allows receiving and sending messages to other domains. It uses the Server-Sent-Events technique to provide a fast message delivery.

## Client

The client is an Angular Web app that provides a UI.

## API and end-to-end tests

The /api/serverapi.yml file contains the OpenAPI specification of the server API.
The /api/e2etests contains Java end-to-end test code of the server API.

## Router

The message router is a Spring Boot app located in the /router directory.
The server uses it to send and receive messages.
It is deployed at https://cpoo-router.mightycode.tech/.

**You can directly access the application at the link provided in the Resume**
