# Secrets scan

Uses [detect-secrets](https://github.com/Yelp/detect-secrets) library to scan for potential secrets in the source code

## Pre-requisite(s)

-   Ensure that you have python
    -   You can install it via `brew install python`

## Initial Setup

1. Install [pre-commit](https://pre-commit.com/) either via brew (`brew install pre-commit`) or python (`pip install pre-commit`)
2. Add provided .pre-commit-config.yaml to project root directory
3. Set up the hooks with `pre-commit install`

> -   pre-commit will fail if there are any potential secrets flagged out
> -   subsequent runs will be automated by the pre-commit library

## Guidelines

-   Secret scan will be run as part of the pre-commit hook, only on staged files
-   Potential secrets will be flagged out in the console
-   There are 2 ways to whitelist,

```typescript
// pragma: allowlist nextline secret
const secret = "dhlknsdfndsl";
const secret = "dhlknsdfndsl"; // pragma: allowlist secret
```

-   Only dummy secrets for tests should be allowed in the source code
-   Variable naming should not use secret-sensitive keywords like ‘password’
-   The whitelisting is coupled with the code
    -   easier to maintain the whitelist
    -   harder to review an overview of all whitelisted issues (will not be picked up & displayed in the .secrets.baseline file)
-   The library does not scan for secrets in comments

## Alternative secrets scan (can be run on pipeline)

Generates a report for the scan that contains the

-   plugins & filters used
-   potential secrets
-   timestamp

### Set up

1. Go to the project root directory
2. Create initial baseline file by running `` detect-secrets scan `pwd`/src > .secrets.baseline ``
3. Add provided audit-secrets.sh
4. Change permissions of added script `chmod +x audit-secrets.sh`
5. Run script

> The library does not update the .secrets.baseline file even if the potential secret is removed
>
> -   consider generating a brand new .secrets.baseline file every time instead

For more details, refer to https://weiyuan-liu.medium.com/stranger-danger-prevent-the-leaking-of-secrets-when-committing-code-12c13ebbc336
