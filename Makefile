ci:
	@echo "Compile Java code"
	./batect compile
	@echo "Check code style"
	./batect code-style
	@echo "Scan secrets in code"
	./batect secret-scan
	@echo "Running tests"
	./batect test

localUp:
	@echo "Spinning up local containers"
	./batect local-start

test:
	./gradlew test

conTest:
	./gradlew -t test

buildLocalImage:
	@echo "Building the latest image"
	./gradlew jibDockerBuild
