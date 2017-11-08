NAME = crescent/readthedocs

build:
	docker build -t $(NAME) .

release:
	docker push $(NAME)

run:
	docker-compose up -d
	#docker-compose run --service-ports --rm readthedocs
	
stop:
	docker-compose stop

debug-run:
	docker-compose run --service-ports --rm readthedocs bash
debug-app:
	docker exec -ti dockerreadthedocs_readthedocs_run_9 bash
