![CI](https://github.com/nvg-ict/connect-four/actions/workflows/ci.yml/badge.svg)


## Build project

To build this project, run the following command: `./gradlew build`

## Build Jar

To build a jar, run the following command: `./gradlew clean shadowJar`

## Build Docker

To build a Docker image, run the following command: `docker build -t connect-four .`

The pre-requisite is that a Jar was already build by the above command. The docker file does not take care of the building

## Deploy package to Artifactory

To deploy the package to the Artifactory, create a tag and push it to the repository. 
The tag should be in the format `v<version>`, for example `v1.0.0`. 
The ci will pick this version up and deploy the package to Github Packages.

## Run deployed package

To run the deployed package, run the following command: `docker run -it --rm --name connect-four-game nvgict/connect-four:<version>`, where `<version>` is the version of the package you want to run, for example `1.0.0`.

Running it with latest will give you the latest package.

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