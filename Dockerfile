FROM java:8
EXPOSE 8080
RUN  apt-key adv --keyserver keyserver.ubuntu.com --recv-keys AA8E81B4331F7F50
RUN echo "deb [check-valid-until=no] http://archive.debian.org/debian jessie-backports main" > /etc/apt/sources.list.d/jessie-backports.list
# As suggested by a user, for some people this line works instead of the first one. Use whichever works for your case
# RUN echo "deb [check-valid-until=no] http://archive.debian.org/debian jessie main" > /etc/apt/sources.list.d/jessie.list
RUN sed -i '/deb http:\/\/deb.debian.org\/debian jessie-updates main/d' /etc/apt/sources.list
RUN apt-get -o Acquire::Check-Valid-Until=false update
RUN apt-get install make && apt-get install -y build-essential && apt-get install -y libc6-dev libc6-dbg && apt-get install git && git clone https://github.com/liuzhengyang/hsdis && cd hsdis && cp build/linux-amd64/hsdis-amd64.so /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/amd64/server/
ADD /target/jvm-insights-api-0.0.1-SNAPSHOT.jar jvm-insights-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "jvm-insights-api-0.0.1-SNAPSHOT.jar"]
