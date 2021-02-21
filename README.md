<div align="center">
  <img src="https://raw.githubusercontent.com/Pkshields/Chronomancy/assets/logo-256x256.png" />
  <h2>Chronomancy</h2>
</div>

Chronomancy is a handy little time converter bot for Discord! 

When someone in your server posts a message containing a time, Chronomancy will give you a button to let you convert the time into your own timezone without any hassle.

Chronomancy is written in [Kotlin](https://kotlinlang.org/), with [Diskord](https://github.com/JesseCorbett/Diskord) giving us access to the Discord bot APIs and [Ktor](https://ktor.io/) backing our REST API server.

## Development

### Configuration

Before working with the bot, you **must** create and populate a local.properties file at `src/main/resources/local.properties`. 

We recommend you copy the `local_sample.properties` sample file as it contains all the configuration options that are required by the bot.

### Run locally

You can run `./gradlew run` from the command line to launch the bot. You can run/debug the bot by running the `Run Bot` configuration within IntelliJ.

### Build

Run `./gradlew clean build` to build the project.

### Running unit tests

Run `./gradlew test` to run the unit tests.

### Lint the source files

Run a static analysis check using [Detekt](https://github.com/detekt/detekt) and [ktlint](https://github.com/pinterest/ktlint) over the source files by executing `./gradlew check`.
