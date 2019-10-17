RABBITMQ_NAME=myrabbitmq

run:
	docker-compose up -d

recreate:
	docker-compose stop
	docker-compose up -d --force-recreate

stop:
	docker-compose stop

exec:
	docker exec -it $(RABBITMQ_NAME) bash

logs:
	docker logs -f -t --tail 100 $(RABBITMQ_NAME)
