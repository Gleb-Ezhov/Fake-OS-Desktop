# Fake OS Desktop

This is a cross-platform Java Swing application, which are imitating Plasma KDE desktop environment from ROSA Fresh Linux distribution. The program is initially written with Russian localization.

The goal of this project has been achieved and I don't plan to develop it further.

## Build and run

To compile java code and prepare all the resources execute this:

```bash
mvn clean validate compile install -f pom.xml
```

Now you can set up run configuration in your IDE for running program in a simple way with classpath dependencies and Main class. Alternatively you can run prepared .jar archive from target directory with the following command:

```bash
java -jar ./target/fake-os-desktop-1.1.0.jar
```

## Screenshots
[There you can find more screenshots.](./docs/screenshots.md)

![Dolphing App and start menu screenshot](./docs/images/dolphin_and_start_menu.png "Dolphing App and start menu screenshot")

## Features

Application itself has a working icons grid with a wallpaper behind it, fully functional window and task bar system, start menu panel and some of additional widgets in the corner.

Along with the Plasma KDE desktop this program has some applications which were stylized with the same KDE look:

- **Dolphin** — basic file manager. I must say, it is rather file browser, because it can't perform any operations, but moving around directories structure.
- **Microsoft's cmd console shell** — basically it is UNIX shell with Windows like formatting and command style.
- **KDE Konsole console shell** — same as cmd, but in a KDE and UNIX style.
- **Device Manager** — in the end it turned out to be a device browser which outputting some information about current systems' hardware.
- **About** — some info about the program.

## Third parties acknowledgement

- [Apache Batik](https://xmlgraphics.apache.org/batik/) for SVG image manipulations;
- [jHardware](https://github.com/profesorfalken/jHardware) for hardware info fetching;
- Maven for building;
- [KDE Plasma](https://kde.org/) and [ROSA Fresh](https://www.rosalinux.ru/rosa-fresh/) for icons;
- Authors of the other helpful classes are credited in the corresponding files.
