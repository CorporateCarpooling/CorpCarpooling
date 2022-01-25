mvn clean install -DskipTests
docker build mariadbservice -t mariadbservice
docker build bookingservice -t bookingservice
docker build carpoolservice -t carpoolservice
docker build userservice -t userservice

#command i terminalen i rooten: .\buildAll.sh
#sedan kör: docker compose up
