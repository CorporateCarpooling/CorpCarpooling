mvn clean install -DskipTests
docker build mariadbservice -t mariadbservice
docker build bookingservice -t bookingservice
docker build carservice -t carservice
docker build userservice -t userservice

#command i terminalen i rooten .\buildAll.sh