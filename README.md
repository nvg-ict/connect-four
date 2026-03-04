![CI](https://github.com/nvg-ict/connect-four/actions/workflows/ci.yml/badge.svg)


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