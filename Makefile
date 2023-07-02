
run:
	./gradlew bootRun -Dspring.profiles.active=local

local-dev-up:
	make -C ./docker local-dev-up

local-dev-down:
	make -C ./docker local-dev-down
