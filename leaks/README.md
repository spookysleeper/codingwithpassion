These are heap leaks examples.

In Java you can have impression that you don't have to think about memory management. This can't be further from the truth, because
if you create too many objects too fast, garbage collector must work harder and because of this you application can become slow.

Concept of memory leak is very simple, you introduce memory leaks by preserving obsolete references to Objects.
We will demonstrate in this post, that it's easy to create memory leak in Java and in extreme cases memory leaks can cause disk paging and
program failure with an <i>OutOfMemoryError</i>.

There are basically two types of out of memory errors in Java:
1) Java.lang.OutOfMemoryError: Java heap space
2) Java.lang.OutOfMemoryError: PermGen space
