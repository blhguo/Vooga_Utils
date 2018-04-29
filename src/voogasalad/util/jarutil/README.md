###Using ths JarBuilder
1. To use the jar builder, create a new instance of JarWriterUI while a stage is showing and follow the prompts
2. The primary methodology for this util is that it would allow authors to create jar files of games
within the authoring environment and it allows the user to specify which packages not to include in the jar 
so they are not unnecessarily large
3. Issues that still need testing
    * it is configured to work in eclipse and intellij but eclipse has not been fully tested. 
    * adding modules is not fully supported yet.
    * saving games has not been tested in the jar environment
4. Inportant things to consider
    * the use of a jar files means that the data side of this project must use resource streams to load 
    files and images. Since datapaths change, and jars have their own file system it is necessary that all 
    calls to get files images and other resources are not made without a resource stream which will dynamically load files
    