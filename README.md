# be-onboarding

## Prerequisites

<details>
<summary>Homebrew</summary>

[Homebrew](https://brew.sh/) is a popular package manager for mMacOS, we will use it to install some tools. Follow the instruction on the homepage to install it.

If you encounter the error:

```text
fatal: ambiguous argument 'refs/remotes/origin/master': unknown revision or path not in the working tree.**
```

It's caused by the configuration of git, try the following solution:

```shell
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/uninstall.sh)"
git config --global core.compression 0
git config --global http.postBuffer 1048576000
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"
```

Then install some tools with Homebrew:

```shell
brew install pre-commit
```

</details>

<details>
<summary>Docker(Powered by Colima)</summary>

Our development process would heavily rely on containers, so we will use docker to manage and host the containers.

Officially only Docker Desktop, which is a paid product, be provided on macOS, so we choose [Colima](https://github.com/abiosoft/colima) to provide the docker engine and client. Follow the instructions to install and configure Colima:

```shell
brew install colima
# start colima with more resources than default setting
colima start --cpu 4 --memory 8 --disk 100
# create a soft link of /var/run/docker.sock, as some tools could connect to this default location of docker scoket
sudo ln -sv $HOME/.colima/default/docker.sock /var/run/docker.sock
```

Finally, set up the correct `DOCKER_HOST` environment variable by appending the following to the `~/.zshrc` file:

```shell
# open ~/.zshrc file with whatever text editor
export DOCKER_HOST="unix://${HOME}/.colima/default/docker.sock"
```

</details>

<details>
<summary>JDK</summary>

We will use `GraalVM` as the JDK, with version 17. The reason is we want to preserve the ability to build native binary as a potential opportunity to boost performance.

Navigate to the [release page](https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.3.0) and download the version 17 which matches your platform, then install it following the instructions:

```shell
# cd into the folder contains the downloaded zip file
tar -xf graalvm-ce-java17-darwin-${ARCH}-22.3.0.tar.gz
# mv the unzipped folder to MacOS system Java folder
sudo mv graalvm-ce-java17-22.3.0 /Library/Java/JavaVirtualMachines
cd /Library/Java/JavaVirtualMachines
# make a soft link to easy upgrade in the future
sudo ln -sv graalvm-ce-java17-22.3.0 graalvm-ce
```

Then you need to set up the correct `PATH` and `JAVA_HOME` environment variables in order to keep the version consistent among the system by appending the following content into the `~/.zshrc` file:

```shell
# open ~/.zshrc file with whatever text editor
export PATH=/Library/Java/JavaVirtualMachines/graalvm-ce/Contents/Home/bin:$PATH
export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce/Contents/Home
```

To instruct your IDEA be aware of this JDK, open `Project Structure` settings (default shortcut: `commond + ;`),
add a new entry in `SDKs` tab which points to `/Library/Java/JavaVirtualMachines/graalvm-ce/Contents/Home`,
then switch to `Porject` tab and specify the `SDK` to your newly created one, and set the `Language level` to `17`.

</details>

## Local Development

Please always use **REBASE** instead of merge to pull the latest code from remote!! as we adopt the TBD practice.

Please remember to **INSTALL** `pre-commit` hooks **BEFORE** writing any code!!

```shell
pre-commit install
```

<details>
<summary>Start local application</summary>

`make localUp`

Uses batect to create application container with its dependencies containers, then run `./gradlew bootRun` inside application container.

Connect to the application at `8080` and database at `5432`. You can find the connection information of database in `dev-database` container definition inside `batect.yml`.

</details>

<details>
<summary>Run tests locally</summary>

`make test` or `make conTest`

Running tests by `./gradlew test` directly. Developers can use continuous testing to keep tracking code changes.

There are several reasons why not use batect:

- The dependency is something the test code should take care themselves.
- It's a must-have requirement to run directly with gradle as running one specified test within IDE is common.
- Gradle inside container can't detect the file changes in case of continuous testing.

[Testcontainers](https://www.testcontainers.org/) is used to solve the dependency on database.

</details>

<details>
<summary>Run CI steps locally</summary>

`make ci`

Uses batect to run all CI steps locally, including: `test`, `code-style`, `secret-scan`, `compile`.
> Ignore `dependency-scan` right now as it's slow and requires a cache mechanism.

</details>

## Documentation

Find other documentation under the `docs` folder.
