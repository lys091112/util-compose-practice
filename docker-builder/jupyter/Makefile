NAME=crescent/jupyter

build:
	docker build -t $(NAME) .
release:
	docker push $(NAME)

recreate:
	docker-compose stop
	docker-compose up -d --force-recreate
run: 
	docker-compose up -d

stop:
	docker-compose stop
exec:
	docker exec -it jupyter_01 bash
