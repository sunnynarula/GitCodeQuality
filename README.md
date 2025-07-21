# GitCodeQuality

This project is now configured as a Maven project. All required
libraries are declared in `pom.xml`, so no additional setup is
necessary.

## Building

To compile the project run:

```bash
mvn package
```

This will download JavaFX and JGit automatically.

To run the example application use the JavaFX plugin:

```bash
mvn javafx:run
```
