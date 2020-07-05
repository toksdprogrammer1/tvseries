FROM openjdk:11

MAINTAINER Tokunbo Ojo <ojotokunboibukun@gmail.com>

EXPOSE 9083 9083

# Install and setup
COPY install.sh /root/tvseries/install.sh
COPY setup.sh /root/tvseries/setup.sh

RUN chmod +x /root/tvseries/setup.sh

RUN /root/tvseries/setup.sh


COPY src/main/resources/application-docker.properties /opt/tvseries/application-docker.properties

ADD target/tvseries.tar.gz /opt/tvseries
WORKDIR /opt/tvseries

RUN chmod +x /root/tvseries/install.sh
CMD  /root/tvseries/install.sh

ENV http_proxy http://172.25.30.117:6060/
ENV https_proxy http://172.25.30.117:6060/

RUN rm -f /etc/localtime
RUN ln -s /usr/share/zoneinfo/Africa/Lagos /etc/localtime