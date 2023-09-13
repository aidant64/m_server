
echo "Hosting at private ip: "
VARR=$(ifconfig | sed -En 's/127.0.0.1//;s/.*inet (addr:)?(([0-9]*\.){3}[0-9]*).*/\2/p')
echo "http://${VARR}:8080/index.html"

javac simpleServer.java
java simpleServer.java
