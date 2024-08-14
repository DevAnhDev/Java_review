# Sử dụng image openjdk phiên bản 17.0.9 LTS làm base
FROM maven:3.8-openjdk-17 as build
# Thiết lập thư mục làm việc trong container
WORKDIR /workspace/app
COPY .mvn .mvn
COPY src src
COPY pom.xml .
RUN mvn package -DskipTests=true
#FROM --platform=linux/amd64 openjdk:17-jdk-slim
FROM openjdk:17-jdk-slim
COPY --from=build /workspace/app/target/*.jar app.jar
# Cổng mà ứng dụng Spring Boot chạy (có thể thay đổi nếu cần)
EXPOSE 8080
# Lệnh để chạy ứng dụng Spring Boot khi container được khởi động
CMD ["java", "-jar", "app.jar"]