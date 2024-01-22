
run:
	docker-compose up -d

stop:
	docker-compose stop

recreate:
	docker-compose stop
	docker-compose up -d --force-recreate

execEs:
	docker exec -it elasticsearch bash

execKibana:
	docker exec -it kibana bash 

logEs:
	docker logs -f -t --tail 100 elasticsearch
