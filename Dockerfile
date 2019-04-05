FROM openjdk:8-jre-alpine
EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/bookslib-api/bookslib-api.jar"]
# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/books-library-rest           /usr/share/bookslib-api/books-library-rest
# Add the package itself
ARG PACKAGE_FILE
ADD target/${PACKAGE_FILE} /usr/share/bookslib-api/bookslib-api.jar