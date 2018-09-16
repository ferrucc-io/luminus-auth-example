FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/clj-auth.jar /clj-auth/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/clj-auth/app.jar"]
