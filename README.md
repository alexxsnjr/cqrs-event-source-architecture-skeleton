# cqrs-event-source-architecture-skeleton
cqrs-event-source-architecture-skeleton is a simple,  boilerplate to kickstart any project with cqrs , event source, ddd architecture.


## Modules Reference

| Module             | desc                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Shared | All abstractions and ports of event source and envent driven architecture  |
| User | Microservice example with a CQRS architecture, this module have 2 submodules for query and commands |
| User/query | Module for query user from relational DataBase and subcribe to events from command module|
| User/Command | Module for persist user and raise event to message broker |
| User/shared |module with user domain objects shared between query and command module  |


## Technologies
For this architecture we use :
 * Mongodb like event store in the command module.
 * MySql like persistence tecnologie in the query module.
 * kafka like message brokering.
 
All modules are building with Spring boot framework and maven for dependencies management.


## Running Tests

To run tests, run the following command

```bash
  ./mvnw clean install
```


## Run locally

first run docker compose to mount the containers 

```bash
docker-compose up -d
```
after that you can compile the project 

```bash
./mvnw clean install
```

then in user service  we have 2 compilations 

```bash
java -jar ./user/query/target/query-0.0.1-SNAPSHOT.jar 
```
  for start user query service in port 5001


  ```bash
  java -jar ./user/command/target/command-0.0.1-SNAPSHOT.jar 
```
  for start user command service in port 5000
## Author contact

- [github](https://github.com/alexxsnjr)
- [twitter](https://twitter.com/Asax_ACK)
- alexxsnjr@gmail.com


## License

MIT License

Copyright (c) [2022] [alexxsnjr]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.