# Example Selenium Project 

# How to build project

Requirements:
- Maven 3+
- JDK 17

To only build project:

    mvn package
  
## Developing with IDE

Plugins (required):
* Lombok

Plugins (nice-to-have):
* Checkstyle

# Compiling using terminal

1. Install `asdf` with required plugins.
 ```
  > brew install asdf
  > asdf plugin-add java
  > asdf plugin-add maven
  > asdf install
 ```
2. Set `JAVA_HOME` ([instruction](https://github.com/halcyon/asdf-java#java_home))
  - ZSH *ZSH SHELL*:
    add `. ~/.asdf/plugins/java/set-java-home.zsh` to `~/.zshrc`

## Integration Test
Integration tests can be found at `src/integration-test`. For more info read [README.md](https://github.com/hubzaj/selenium/blob/main/src/integration-test/README.md).

