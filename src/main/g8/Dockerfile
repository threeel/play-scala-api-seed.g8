FROM lkameris/openjdk8:0.2.0
WORKDIR /app
COPY . .
RUN apt-get update && apt-get install unzip -y && sbt dist
RUN set -x && unzip -d svc target/universal/*.zip && mv svc/*/* svc/ && rm svc/bin/*.bat && mv svc/bin/* svc/bin/start

EXPOSE 9000 9443
CMD svc/bin/start -Dhttps.port=9443 -Dplay.crypto.secret=secret