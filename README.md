# Serialize_Deserialize-Objects
Generic Library to Serialize and De-serialize Objects using Java reflection and Strategy Design pattern

---------------------------------------------------------------------------------------------------------------------------
Project Description:

The code allows the conversion of objects into a wire format.

The code is designed using Java reflection so that addition of new objects or serialization formats causes minimal changes ( reduces the ripple effect ).

Strategy design pattern is used .

-------------------------------------------------------------------------------------------------------------------------------

DEBUG_VALUE=0 [Print to stdout the name of output file where output of program is stored]

-------------------------------------------------------------------------------------------------------------------------------
An example of input.txt:
(fqn is fully qualified name of class. First and Second are classes used for implementation)

<fqn:genericSerDeser.util.First>
<type=boolean, var=BooleanValue, value=true>
<type=byte, var=ByteValue, value=99>
<type=char, var=CharValue, value=a>
<type=double, var=DoubleValue, value=6818483877085415424.000000>
</fqn:genericSerDeser.util.First>
<fqn:genericSerDeser.util.Second>
<type=double, var=DoubleValue, value=-3759083905444020224.000000>
<type=double, var=DoubleValue2, value=-4623670297545811968.000000>
<type=long, var=LongValue, value=8871853512295682239>
</fqn:genericSerDeser.util.Second>

---------------------------------------------------------------------------------------------------------------------------------
Output.txt should exaclty match with input.txt as same objects will be saved after desearilaization in output file.

---------------------------------------------------------------------------------------------------------------------------------
Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

---------------------------------------------------------------------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

---------------------------------------------------------------------------------------------------------------------------------
## To run by specifying arguments from command line 

ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=0

---------------------------------------------------------------------------------------------------------------------------------

justification for Data Structures used in this assignment in term of Big O complexity (time and/or space)

Arraylist :- Time complexity for ArrayList is O(1) for add which is faster, O(n) for remove and O(1) for Get. 
I have used Arralist to store First and Second class object instances as complexity is O(1).we need to maintain the order in which objects are getting created and Arraylist maintain the elements insertion order which means while displaying ArrayList elements the result set would be having the same order in which the elements got inserted into the List.
