
/////////
L:
/////////


====

(1) the deepest common ancestor of two nodes of a tree
(2) all permutations of a string.

===

  
Implement double pow(double a, int b) without using any already built-in functions (aka, dont use an already defined pow function).   View Answers (4)
Given two (dictionary) words as Strings, determine if they are isomorphic. Two words are called isomorphic if the letters in one word can be remapped to get the second word. Remapping a letter means replacing all occurrences of it with another letter while the ordering of the letters remains unchanged. No two letters may map to the same letter, but a letter may map to itself.

Example:
Given foo, app; returns true
   we can map f -> a and o -> p
Given bar, foo; returns false
   we cant map both a and r to o

Given turtle, tletur; returns true
   we can map t -> t, u -> l, r -> e, l -> u, e -r

Given ab, ca; returns true
   we can map a -> c, b

===
Design web calendar

Print a tree like reading a book, left to right.

 How to find if nodes in graph are exactly 1/2/3 edges apart. how would you distribute graph across machines.
- Given set of characters and a string, find smallest substring which contains all characters.
- Implement a delayed scheduler for jobs using pthread apis (mutex/cond_var)
- You have bunch of numbers coming in, and a given a window size, how would you save numbers so that you can return number if present in window and provide average for current window.
- Manager round: You are given bunch of machines with services running on them, how would you improve things. very vague design talk.
- Reverse words in a string.
- Implement decimal to roman and vice versa.

===

Check if a String contains a number or not

===

return n closest points on a plane

===

Details 
1st call - Phone call with the Recruiter just to talk about what youve done and what currently interests you. Basically to directly communicate with you to see if youd be a good fit for the company to want to go through the interview process with you

2nd call - Technical phone interview #1 using Collabedit. Given two questions to solve in one hour. First question was to find the lowest common ancestor given two nodes and the second question was to determine if two strings are isomorphic (there exact question can be found on this site)

3rd call - Technical phone interview #2 also using Collabedit. Given two questions to solve in one hour as well. First question was find the largest subsequence given an array that yields the largest sum. The second question was a modification of the first that required me to find the largest subsequence of the given array that yields the largest PRODUCT. Didnt complete the code for the second question in time.

===

Certain questions involved designing new data structures and implementing the corresponding interface functions (i.e., insertion, deletion, etc.) from scratch

===

Rest system design. Amazon website design. Read/Write locking. Generate all factoring components. 

===

Print binary tree level by level
* Find distance between words in a string
  eg: String => I am a good girl distance between I and good is 3  

===

Find an element in a rotated array

===

Print out a binary tree with each level printed on its own line

===

write out all the combination of factors of one number. another was about implementing hash table. 

===

Write a function to realise as power of b.

===

Parse the IP in a file, actually, it is not difficult

===

Code a non blocking thread safe queue
and
code a text justification routine (Given a line length insert white space so text is uniformly displayed within the given length).


===
Implement a shooting algorithm for the game of Battleship.   View Answer
Implement an algorithm to convert an integer into a roman numeral string and vice versa

===

Print a tree on the screen line by line

===

Implement a hash table

====

How to write a deep iterator

===

How to validate is a binary search tree is legit?

===

How would you design amazon.com?   View Answer
Program an iterator for a Linked List which may include nodes which are nested within other nodes. i.e. (1)->(2)->(3(4))->((5)(6). Iterator returns 1->2->3->4->5->6   View Answer

===

minimal spanning trees

Do you know Design Patterns and can you write a function in java to implement it?   Answer Question
What is Abstract Class and its use. Gave me a example and asked me to extend and implement its methods   Answer Question
Write a function to implement BFS. 

Check if a String contains a number or not

Do a depth first traversal of a Rose Tree (the data-structure) and print out each element   Answer Question
Generate a list of all permutation of a string   Answer Question



1. merging intervals
2. serialize and deserialize a binary tree
3. Edit distance
4. co-linear points in a plane   Answer Question

Design a system to integrate education certificate from external vendors to LinkedIn

First Phone Screen: Collabedit coding was expected with perfect language syntax
Implement power function efficiently.
Find maximum sum subset in an array with negative integers

Second Phone Screen:
Find if a string is a number or not.
Repeat question of maximum sum subset. After telling them that I already answered this, they were surprised as the first interviewer had not left any notes! This was a hard problem which I had answered correctly. I was very disappointed.
Variation of the same problem, maximum product subset. I faultered a bit and was not able to get to the right code. However my idea was correct.
Interview Questions 
Maximum product subset with negative and positive integers

  
Question1
/**
 * Given a nested list of integers, returns the sum of all integers in the list weighted by their depth
 * For example, given the list {{1,1},2,{1,1}} the function should return 10 (four 1s at depth 2, one 2 at depth 1)
 * Given the list {1,{4,{6}}} the function should return 27 (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3)*/

 How would you design a URL shortener

 1st round (phone screening):
1. WAP to implement pow(a,b) function.
2. WAP to build a BST.   View Answer
2nd skype:
- Lets say you have to build an activity (screen) which has a drop-down & once you select an item in the drop-down, it loads an image corresponding to that on the screen below.
- How will handle cases where user is very rapidly changing his selection from the drop down   View Answer
3rdSkype (he allowed checking online android apis guide)
1- Write an activity which displays device build number, imei.
2- Implement a scrolable list to show ints from 1 to 10.   Answer Question
4th skype (design round):
- How will you design an app (client) & server to implement a word guess game. For e.g. app will ask user to guess the word (it will show blank spaces first equal to size of the word) one character one time. He will have n chances to guess correct word. Based on the result, he will win or loose money (prize).

Assume you have well defined payment & user registration system. Assume external systems required for this game already present. Just concentrate on designing the above core requirement   View Answer
5th skype:
- Most questions to check my job compatibility and behavioral questions
- How will you reverse a string. Tell me the number of ways you can solve this problem.

6th skype:
- WAP to implement function public String replace (String originalString, String pattern, String replacement)
Cover all boundary cases. Write clean code




  
Question were simple. They do have a question bank.
a) Find the nearest K point given a set of N point.
b) Print a tree level-by level.
c) Given a dictionary find and set of two words find path from one word to another such that all the intermediate words are also from dictionary.
 Example: GOD -> GID -> DID -> DIG -> DOG.
At each time we are allowed only one character change.
d) Design an Hangman. { They expect MVC architecture. } 


One was given 2 Nodes in a binary tree, find the closest ancestor and the other one was given a 2D array of people who follow one another, determine whos an influencer(followed by everyone, follows no one). Example: If A[i][j] is true, then person i follows person j.

public interface PointsOnAPlane {

    /**
     * Stores a given point in an internal data structure
     */
    void addPoint(Point point);

    /**
     * For given center point returns a subset of m stored points that are
     * closer to the center than others.
     *
     * E.g. Stored: (0, 1) (0, 2) (0, 3) (0, 4) (0, 5)
     *
     * findNearest(new Point(0, 0), 3) -> (0, 1), (0, 2), (0, 3)
     */
    Collection<Point> findNearest(Point center, int m);

}


1st round Phone Screen: The lady was sweet and asked what position I was interested in and the general HR questions like what am I looking for in an internship.

2nd round Phone Technical:
Given a sorted array with duplicates and a number, find the range in the
form of (startIndex, endIndex) of that number. For example,

find_range({0 2 3 3 3 10 10}, 3) should return (2,4).
find_range({0 2 3 3 3 10 10}, 6) should return (-1,-1).
The array and the number of duplicates can be large.
Interview Questions 
Find all the possible factors of a given number

Sorted infinite character stream

What are the differences between processes and threads?

One technical question was a greedy interval scheduling problem, and the other was a binary search variant.



implement List interface of java. I had to implement all add, get and remove methods using any data structure

One of them involved searching in a matrix sorted row wise. The other one involved finding the exponent of a number in an efficient manner. I figured the exponentiation by squaring method and wrote the pseudo code for that which was correct. All in all, nothing unexpected and the questions can be answered with a little bit of practice.
For the first technical phone screen, the questions involved permutations (Scramble an array with an equal chance for every value. Return a list of all permutations of an array.).   


Consider an X x Y array of 1s and 0s. The X axis represents influences meaning that X influences Y. So, for example, if $array[3,7] is 1 that means that 3 influences 7.

An influencer is someone who influences every other person, but is not influenced by any other member.

Given such an array, write a function to determine whether or not an influencer exists in the array. 


Code a non blocking thread safe queue
and
code a text justification routine (Given a line length insert white space so text is uniformly displayed within the given length).


Program an iterator for a Linked List which may include nodes which are nested within other nodes. i.e. (1)->(2)->(3(4))->((5)(6). Iterator returns 1->2->3->4->5->6 







