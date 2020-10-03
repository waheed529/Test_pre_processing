# Start from JRE
FROM openjdk:8-jre

# Copy into our app, shellscripts, and log4j2 files
COPY  ./target/Test_pre_processing-0.0.1-SNAPSHOT.jar /app.jar

#COPY ./target/classes/shellscripts/add-widget.sh /shellscripts
#COPY ./target/classes/log4j2.xml /log4j2.xml
# Expose the port required
EXPOSE 8080
#EXPOSE 443
# Log File Directory

RUN mkdir -p /data/logs

# Copy any files into the container
#COPY ./certs/ /certs/
#RUN keytool -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt -trustcacerts -import -file /certs/basics.keystore.jks
#RUN keytool -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt -trustcacerts -import -alias jenkins.optum.com -file /certs/kaas.client.truststore.jks
#COPY ./certs/*.jks ./
#COPY ./configProd/properties/EEMSPrime-Consumer/*.jks ./
# Set the app label

LABEL app=TestPreProcessing

# make sure the log file is present and writable

RUN mkdir /logs
RUN touch /logs/audits.log
RUN chmod 777 -R /logs

# run the app

ENTRYPOINT java \

#  -Dcom.sun.management.jmxremote \
#  -Dcom.sun.management.jmxremote.port=9010 \
#  -Dcom.sun.management.jmxremote.local.only=false \
#  -Dcom.sun.management.jmxremote.authenticate=false \
#  -Dcom.sun.management.jmxremote.ssl=false \

  -jar /app.jar