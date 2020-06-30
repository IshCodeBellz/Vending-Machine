/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bello.ishcodebellz.vma.app;

/**
 *
 * @author ahmbe
 */
public class MavenAndSpring {
    /*
    
    Explain how Maven manages the Spring libraries required for DI.
     
    + Maven just needs to be told what it needs to do due to its built in lifecycle
    + Libraries can be added by supplying the version number Maven will make it available for use in code
    + A main feature of Maven is its dependancy management, it has dependencies with tags which represent an external  library.
      without it youd have to manually download, identify and include jar files of all libraries needed for given projects 
    + a ~/.m2 directory is where it creates a central repository for all dependencies, simplifying it
      for developers to have all dependencies for the project downloaded and ready to use in the repository.
    
    Explain how dependency injection and programming to interfaces work together.
    
    + DI makes the class independent of its dependencies by decoupling the use of an object from its creation.
    + Dependency injection means that the dependencies are injected into objects by some other
      entity
    + An example would be the applicationContext.xml code
    + Introduction of spring and xml allows code to modified simply
    + Loose coupling from vlient and implementation of service can be done via DI
    + Externalisation of the systems configuration is allowed, allowing changes without recompiling the app
    + Developers can program against the interface and use stubbed and mock implementations while the real implementation is being built.
    
    Explain how Spring can simplify Java development.
    
    + Spring works by saving memory and manages object initialization and instances of service classes. It acts as a wrapper
      and wraps Java applications in Spring application context (i.e applicationContext.xml).
    + Allowing developers to delegate the maintenance of object life cycyles and dependencies to Spring
    + This also helps with minimising storage within applications
    + This called Dependency Injection (DI) and makes the program a lot more efficient and less time consuming.
   
    
    Explain the Maven lifecycle.
    
    + 5 main stages to Maven lifecycle.
    - Compiling : To compile the project source code
    - test-compile: Compile the test source code
    - test: run the unit tests for the project.
    - package: builds and packages the project.
    - install: installs the project package into the ~/.m2 repository - project packages can then be used in other
      projects.
    + other stages include validate, verify, and deploy which are also commonly included in the Mavenlifecycle
    
    */
}
