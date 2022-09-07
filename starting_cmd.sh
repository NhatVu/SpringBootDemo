# for start postgres
docker container run --name postgresdb -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres
# if container is already created, use cmd to start
docker start postgresdb
# access postgres in docker
docker container cp expensetracker_db.sql postgresdb:/
docker container exec -it postgresdb bash

# run sql file
psql -U postgres --file expensetracker_db.sql