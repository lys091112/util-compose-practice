MOCK_NAME=spark2.3

build:
	docker build -t $(MOCK_NAME) .
release:
	docker push $(MOCK_NAME)
run: 
	docker-compose up -d

stop:
	docker-compose stop

exec-master:
	docker exec -it spark_master_1 bash

exec-slave1:
	docker exec -it spark_slave1_1 bash

exec-slave2:
	docker exec -it spark_slave2_1 bash
