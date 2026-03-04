![CI](https://github.com/nvg-ict/connect-four/actions/workflows/ci.yml/badge.svg)


## Git hook (local quality gate)

Install the pre-commit hook (runs `./gradlew check` before every commit):

```bash
cp githooks/pre-commit .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit