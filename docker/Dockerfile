FROM openjdk:8
ADD eregister-user-service-1.0.0-SNAPSHOT.jar eregister-user-service-1.0.0-SNAPSHOT.jar
RUN mkdir -p /var/log/eregister-user-service && touch /var/log/eregister-user-service/eregister-user-service.log
CMD java -jar eregister-user-service-1.0.0-SNAPSHOT.jar > /var/log/eregister-user-service/eregister-user-service.log 2>&1
#CMD java -jar eregister-user-service-1.0.0-SNAPSHOT.jar
