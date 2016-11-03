# Train Simulator [![Build Status](https://travis-ci.com/vibbix/TrainSimulation.svg?token=moTUzkBKjMxZqr8uWPyy&branch=master)](https://travis-ci.com/vibbix/TrainSimulation) [![codecov](https://codecov.io/gh/vibbix/TrainSimulation/branch/master/graph/badge.svg?token=yKDFGZoZQw)](https://codecov.io/gh/vibbix/TrainSimulation)

![Railroad Engineering](https://img.gta5-mods.com/q95/images/railroad-engineer/f0174b-Cover.jpg)

## What is this
This is the Group 23's Train Simulation project to demonstrate the Queue ADT.


## How to build
1. Install the latest version of JDK 8 or newer. 
2. Clone the repository to a local directory. 
3. Import the project in your preferred IDE(I suggest IntelliJ IDEA, but Eclipse will work. If you say NetBeans, I'll delete your repository access).
4. Build and run the project.

The project is setup as a Maven-based project, so any IDE thats Maven aware should be fine. If you have Maven installed, and its on your path, running this will compile a jar: 

```
mvn package
```

### How to import in IntelliJ IDEA
1. If you have a project open in IDEA already, go to File -> Close Project.
2. Press 'Import Project', and navigate to the directory where you cloned the repository.
3. Finish the wizard.

Source: https://www.jetbrains.com/help/idea/2016.2/importing-project-from-maven-model.html


### How to import in Eclipse
1. Clone the project using either the Git CLI or Github Desktop. SourceTree, Git Kraken are all fine. Downloading the zip file of the latest commit isn't.
2. In Eclipse, go to File -> Import
3. Navigate to the Maven dropdown and select 'Existing Maven Projects'
4. Navigate to the directory where you cloned the repository.
5. Press 'OK'

Source: http://stackoverflow.com/a/36242422
### How to import in Netbeans
1. Reconsider life choices.
2. Email Mark Beznos with the subject line "Help, I thought using Netbeans was a good idea"
3. Wait for repository access to be removed.

Source: [http://stackoverflow.com/a/43423489](https://www.youtube.com/watch?v=oHg5SJYRHA0)
### How to import in EMACS
1. GOTO 'How to import in Netbeans'

Source: [http://stackoverflow.com/a/63735612](https://www.youtube.com/watch?v=oHg5SJYRHA0)
	
## How to contribute
Create a branch prior to making any changes. Before making a pull request, make sure its tests pass. TravisCI will warn us if you don't regardless.
