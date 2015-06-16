Chatter
=======

DoS system for my [Chat-IRC](https://github.com/aurbano/Chat-IRC) application.

It contains a subset of the IRC Commands with a simple implementation that generates the appropriate byte streams, and a simple Client that allows the tool to connect to an IRC server.

To launch an attack there are two options:

```bash
# First compile the source
$ javac Flooder.java
# Run the DoS tool
$ java Flooder {host} {port} {clients}
```

The number of clients is the number of simultaneous connections you want to send to the server. If the server is a real IRC using more than 1 will likely get you banned, although to be fair, any setting you use will quickly get you banned.

[![Analytics](https://ga-beacon.appspot.com/UA-3181088-16/chatter/readme)](https://github.com/aurbano)
