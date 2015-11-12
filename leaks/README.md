These are heap leaks examples.
http://www.codeproject.com/Articles/30593/Effective-Java
http://javarevisited.blogspot.sk/2011/09/javalangoutofmemoryerror-permgen-space.html

In Java you can have impression that you don't have to think about memory management. This can't be further from the truth, because
if you create too many objects too fast, garbage collector must work harder and because of this you application can become slow.

Concept of memory leak is very simple, you introduce memory leaks by preserving obsolete references to Objects.
Another true memory leaks are different story:
http://stackoverflow.com/questions/6470651/creating-a-memory-leak-with-java
We will demonstrate in this post, that it's easy to create memory leak in Java and in extreme cases memory leaks can cause disk paging and
program failure with an <i>OutOfMemoryError</i>.

how much one space object consumes:
http://btoddb-java-sizing.blogspot.sk/2012/01/object-sizes.html

There are basically two types of out of memory errors in Java:
1) Java.lang.OutOfMemoryError: Java heap space
2) Java.lang.OutOfMemoryError: PermGen space

Why arrays consume more space:
http://stackoverflow.com/questions/24559839/memory-usage-of-byte-array-in-java