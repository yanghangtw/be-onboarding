# Spotless usage sample

## Style
### Google Java Format (preferred option for now)
Style guide: `https://google.github.io/styleguide/javaguide.html`
This style is not customizable

### Eclipse JDT
Style guide is default from eclipse, but must generate from eclipse application.
How to create config file: `https://github.com/diffplug/spotless/blob/main/ECLIPSE_SCREENSHOTS.md`

## installing pre-commit
using the pre-commit must install first
guide: `https://pre-commit.com/#install`

and then install pre-commit to git hook
`pre-commit install`

## Formatting

### Manually

run to format
```shell
./gradlew spotlessApply
```

run to check (comparison)
```shell
./gradlew spotlessCheck
```

### Setup in your IDE

#### IntelliJ

Find `spotless` plugin and the option in `Code` > `Reformat Code with Spotless` to format with spotless. To have a shortcut, change the keymap.