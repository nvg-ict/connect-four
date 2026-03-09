![CI](https://github.com/nvg-ict/connect-four/actions/workflows/ci.yml/badge.svg)


## Build project

To build this project, run the following command: `./gradlew build`

## Build Jar

To build a jar, run the following command: `./gradlew clean shadowJar`

## Build Docker

To build a Docker image, run the following command: `docker build -t connect-four .`

The pre-requisite is that a Jar was already build by the above command. The docker file does not take care of the building

## Run Docker locally

To run the docker on your local machine, run the following command: `docker run -it --rm --name connect-four-game connect-four`

## Git hook (local quality gate)

Install the pre-commit hook (runs `./gradlew check` before every commit):

```bash
cp githooks/pre-commit .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit
```

## Tech Debt

Technical debt should be made explicit.

If you introduce tech debt in the code, mark it with:

// TODO(TECHDEBT): <description>

Every `TODO(TECHDEBT)` must also be listed in `TECH_DEBT.md`.  
The build (`./gradlew check`) verifies this and will fail if tech debt is not registered.