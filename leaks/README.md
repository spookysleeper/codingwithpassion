Heap leaks examples.
http://www.codeproject.com/Articles/30593/Effective-Java
http://javarevisited.blogspot.sk/2011/09/javalangoutofmemoryerror-permgen-space.html

In Java you can have impression that you don't have to think about memory management. This can't be further from the truth, because
if you create too many objects too fast, garbage collector must work harder and memory becomes more fragmented and because of this
you application will be slower. Nowadays, modern VM are very efficient and can deal efficiently with rapid small object creation, but
there are limits. If you hit limit you application will die due to memory leak.

Concept of memory leak is very simple, you introduce memory leaks by maintaining obsolete references to Objects. An obsolete reference
is simply a reference that will never be dereferenced again.
This is so called "simple memory leak". "True memory leaks" are different story. "True memory leaks" are those where you create objects that
are inaccessible by running code but still stored in memory.

One famous example of true leak is basically concoction of custom class loader, long running thread with thread local variables preferably inside of application container.
This works because the ThreadLocal keeps a reference to the object, which keeps a reference to its Class, which in turn keeps a reference to
its ClassLoader. The ClassLoader, in turn, keeps a reference to all the Classes it has loaded.
With multiple deploys this will break with totally unexpected perm gen memory leak exception.


There many out of memory errors and you can see for description here: https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/memleaks002.html
But in practise, you will see this tree most often.
1) Java.lang.OutOfMemoryError: Java heap space
   Heap is full
2) Java.lang.OutOfMemoryError: PermGen space
   Permament generation space is full.
3) java.lang.OutOfMemoryError: GC Overhead limit exceeded
   GC is working way to hard with little or no result.

We will demonstrate in this post, that it's easy to create memory leak in Java and in extreme cases memory leaks can cause disk paging and
program failure with an <i>OutOfMemoryError</i>.

Why arrays consume more space:
http://stackoverflow.com/questions/24559839/memory-usage-of-byte-array-in-java