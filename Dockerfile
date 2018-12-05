FROM jboss/wildfly
ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true -Djboss.socket.binding.port-offset=100"
COPY truck-service-board/target/truck-service-board.war /opt/jboss/wildfly/standalone/deployments/
