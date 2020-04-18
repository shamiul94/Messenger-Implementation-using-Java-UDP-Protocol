# Messenger-Implementation-using-Java-UDP-Protocol
## Introduction 
This is a simple implementation of a messenger (no GUI) where two or more people can chat with each other over UDP protocol from different machines connected to the same LAN. I implemented this for a weekly assignment of our 'Object Oriented Programming Language' sessional course.

## How does it work? 
You have to read the `Problem Statement.docx` file carefully to understand how it works. Basic idea is that we are implementing a messenger using UDP protocol. But we don't have any user interface implemented. We will run the surver from some pc. Then, the clients will be run from other PCs or the same PC using the terminal. After that if you send a message, other clients can see it and they can also send reply back to you. 

## How to run it
Run these commands from command prompt to run the client and server. This project was tested on Windows 10. If you are using any other system, commands will change.
```
javac -version
f:
cd F:\LearningNetworking\src\FinalPack
javac Server.java
java Server CSE
```