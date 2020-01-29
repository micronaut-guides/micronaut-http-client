FROM oracle/graalvm-ce:19.3.1-java8 as graalvm
COPY . /home/app/micronautguide
WORKDIR /home/app/micronautguide
RUN gu install native-image
RUN native-image --no-server -cp build/libs/complete-*-all.jar


FROM frolvlad/alpine-glibc
EXPOSE 8080

RUN apk update && apk add libstdc++

COPY --from=graalvm /home/app/micronautguide/micronautguide /app/micronautguide

WORKDIR /app
ENTRYPOINT ["/app/micronautguide"]
