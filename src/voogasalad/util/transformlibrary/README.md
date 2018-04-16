## Transform Library

The transform library consists of two components: Transform and Vector2.

### Vector2

Vector2 allows the user to store the x and y coordinates in a simple Vector2 object to ease the process of working with world coordinates. The class also facilitates using vector maths to simplify transformation calculations. Users can perform simple operations or even get properties like the magnitude of the vector. All vectors are position vectors, starting from the origin (0,0).

### Transform

The Transform class is made to perform operations with other Transform objects. A Transform object only
needs a Vector2 object that stores coordinates for its constructor. It contains functions like getting the angle or displacement between two objects, as well as moving an object towards another. This class will be updated to implement rotations and transformation in both local and world space. 
