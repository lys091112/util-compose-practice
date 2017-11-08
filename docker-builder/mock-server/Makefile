MOCK_NAME=crescent/mock-server

build:
	docker build -t $(MOCK_NAME) .
release:
	docker push $(MOCK_NAME)
run: 
	docker-compose up -d

stop:
	docker-compose stop

exec:
	docker-compose -it exec mock-server bash
