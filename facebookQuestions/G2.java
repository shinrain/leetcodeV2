简单的 if n even then n = n/2, if n odd then n = 3*n-1; 终止条件是 n==1; there is overflow


find min and max in an array. 有一个算法:
    compare arr[i], arr[i+1] -> tmin, tmax
    compare min, tmin
    compare max, tmax
time and space complexity; compared with simple scanning method??


Snake and ledder: arc-->dest need how many dices? (dice:1-6)


在x,y平面上有一些正方形的房子. 如果用它top, down, left, righ(data type: float)来描述这些房子的话, down永远是0 (房子不会在天上); 接着把所有房子的正方形涂黑,要我list 黑色区域的顶点 [contour following]


Topological sort.


一个更儿戏的设计题 根本没让我整理思路 就他一直在follow up:
先问我怎么设计一个 刷卡开门系统的数据库. 我还打算画一个state machine 给他
a. 他说不用,就考虑lock - unlock状态,假设每个卡有一个id 
b. 假设不同的门有不同的priority
c. 假设有些人的id可以同时打开Google Lond 和 Google MV的门
d. 假设这个人的卡丢了, etc.
整个过程我都在见招拆招(所以feedback可能不会很好). 如果一开始把所有东西都考虑一遍一步到位就好了.


An arbitrary tree. split it into as many subtrees as you can. the number of nodes of the subtree must be even. [nodes with even #nodes in the subtree can be the root of an even-subtree]


An online stream, with infinite numbers, tell me the most frequent number



==============================================================================


Return random node of a list, what if it can be modified concurrently[?].


1k Ads, how to make it only appear once across all servers, no master server. [add a flag with each Ad?]


Check generalized tree, follow up:return all generalized tree of its children, i.e.
    1
  2   3
4    5 6
In this tree, 2,4,5,6 are valid nodes.

------------------------------------------------------------------------

#1. design a calculator for pow(x,y), 1<=x<=9, 1<=y<=99
def pow(x,y):
    if y < 0: return 1/pow(x,-y)
    elif y == 0: return 1
    elif y == 1: return x
    elif y%2 == 1: return x*pow(x*x, y>>1)
    else: return pow(x*x, y>>1)


# 2. we have a list of schedules, defined by start time and end time. e.g. (9:00 - 10:00) (10:00 - 11:00). Find all the conflicts and print out the pairs.
# inclusive ending points
def find_conflict(intvs):
    LEFT = 1; RIGHT = -1
    ends = []
    for i, (x,y) in enumerate(intvs):
        ends.append((x,i,LEFT))
        ends.append((y,i,RIGHT))
    ends = sorted(ends, key = lambda x:x[2]) # RIGHT is in front of LEFT
    ends = sorted(ends, key = lambda x:x[0])
    print ends
    S = []
    pairs = []
    for x, id, label in ends:
        if label == LEFT: S.append(id)
        else:
            i = None
            for j in range(len(S)):
                if S[j] == id: i = j
                else: pairs.append((id,j))
            S.pop(i)
    print S
    return pairs



3. design data structure supporting insert(int v), remove(int v), getRandom(), what is your choice given a stream of integers, how to get median value efficiently. what if we want to get the top x% number? [list+hash table; max-heap + min-heap; keep it sorted!?]

{max-min heap with x% number difference}

==
4. we have a history of match result of pingpong games, assume each match is <player1, player2, result, timestamp>, player1 and player2 are long type, result is a bit value (1 means player1 win). design a algorithm to sort the players by their possibility to win future games. [For each play, winning score = \sum 1* {delay factor^(current time - winning time)}; pagerank 
]

Learning

===

1. Multiple threads can publish and receive each other's message: whenever a thread publishes a message, all the other threads can receive and print out that message; if multiple message get published, the messages should be queued or whatever recorded and other threads can print out the message one by one; no published message should be missed by any other threads.


Give a list of documents, find the minimal document set that can 
cover all the characters in all the documents, like "a b c h j",  "c d", "a b c", "a f g", "a h j". the result should be "a b c h j" "c d" and "a f g". [greedy algorithm]
#docs = [set(["a","b","c","h","j"]), set(["c","d"]), set(["a","b","c"]), set(["a","f","g"]), set(["a","h","j"])]
def doc_cover(docs):
    doc_ids = set(range(len(docs)))
    uncovered = set()
    selected = []
    for doc in docs: uncovered |= doc
    while len(uncovered) > 0:
        id = max(doc_ids, key=lambda x:len(docs[x] & uncovered))
        selected.append(id)
        uncovered -= docs[id]
    print [docs[i] for i in selected]
    return selected


Design a system to return the top k Google query that occurred before certain time.
(1) Hash table to count the frequency --> min-heap to find the top k
(2) Stream-summary algorithm


Gas station ~ O(n) [DP]

一个数组，找出一个solution使得1st < 2nd, 2nd > 3rd, i.e. 15462 is a solution for 12456. [
def gen_solution(A, cands, go_up):
    if not A: yield ''
    else:
        for i,a in enumerate(A):
            if not a in cands: continue
            if go_up: new_cands = set([x for x in A[:i]+A[i+1:] if x > a])
            else: new_cands = set([x for x in A[:i]+A[i+1:] if x < a])
            for other in gen_solution(A[:i]+A[i+1:], new_cands, not go_up):
                yield a+other
]


Most frequent character in a huge string (10works 1master). What if (1) having one big file on one machine? (2) What if having many small files on multiple machines?
[(1) hashtable counter --> min-heap to find top chars, or use steam-summary, or use count-mean-min sketch; (2) Map-reduce --> min-heap]


How to design general cache [cyclic linked list + hash table]


找一个字符串里面频率最高的字符， 问了好多小问题，如果是 unicode 怎么办。如果每个字符串5,6个字符怎么办。如果一个机器，四个核怎么处理。在一个cluster里面，每个机器单核，所有机器互相连接怎么处理（数据量500g， 网速 1g/每秒）问了20分钟左右。


一个数，比如7可以拆成 1+3+3 或者3+4。 求拆成的因子相乘积最大的那个值。 我先给了个recursion的solution， 每次从1开始拆。 他说不够有效，然后我又改成dp，用了10分钟，刚好到45分钟，面试结束。
# S(n) = max product of a split of n
# S(n) = max{S(k)*S(n-k) for 1 <= k <= n-1}
def max_split(number):
    S = {0:1, 1:1}
    for n in range(2,number+1):
        S[n] = n
        for k in range(1,n+1):
            S[n] = max(S[n], S[k]*S[n-k])
    print S


Find mean/median among numbers on many machines.


5. 设计怎么test狗狗产品的feature，我也不知道答的怎么样，我说面试官在那边记录，感觉比较冷淡


(1) 怎么随机洗牌
def shuffling(A):
    import random
    n = len(A)
    for i in range(n):
        j = random.randint(i,n-1)
        A[i], A[j] = A[j], A[i]
    return A


(2) you have a binary image (0 black, 1 white), count how many white blobs 
in the image? Assume 4 neighborhood.


(3)三个状态 A, B, C and a special state exit. P(A->A) = a1, P(A->B) = a2, P(
A->exit) = a3, a1+a2+a3 = 1, P(B->B) = b1, P(B->C) = b2, P(B->exit) = b3, b1
+b2+b3 = 1, P(C->C) = c1, P(C->exit) = c2, c1+c2 = 1. The first state you 
will visit will always be A. Suppose you visit the following sequence of 
states A->A->B->exit, define the above sequence to be of length 3, so what 
is the probability that you will get a sequence of states of length n?


(4) 每个任务之间有dependency，怎么安排任务顺序，使得执行任务i的时候，所有被
depend的任务已经执行过了。[DAG?]

(1)
char* f()
{
  char x[100];
  sprintf(x, "hello world\n");
  return x+6;
}

void main()
{printf("%s", f());}

输出是什么，有什么问题，如果f is called by multiple threads，会出现什么情况


(2) 爬楼梯，一次爬一格或者两格，多少种爬法. [C(n) = C(n-1) + C(n-2)]


(3) uniform sampling of objects coming in stream


graph的isConnected() [BFS traversal]


crawl URL, 给一个url把嵌套的hyperlink也都crawl了，我就用DFS做的，他也没意见，后面就鬼扯判断 了一大堆hash function/collision的东西 －－因为判断visitedURL的时候，我用hashset判断的。


给一大堆string，让我buildModel可以去generateWord. 我就建议用简单的NLP里面的model，估计起始letter的prob， 还有任意两个连续的letters的出现的条件概率， 面试官对这个model没意见
，我就开始code －－基本就是scan input data，外加数数了。


integer sqrt()


list<String> serialization/deserialization
list<String>那个题在板上见过不过自己当时没有好好想，还好当场跟interviewer讨论了一下也确定了serialization的规则, 后来只用code deserialization部分



======================================================


1. Serialize and deserialize a binary tree.
How to test your methods? write sample test cases.
How to deserialize a binary tree based on network stream?
How to serialize/deserialize a tree with arbitrary number of children?


给一个binary tree(不是binary search tree, 也不一定balanced). 要求efficiently store it in other data structure on disk, and reconstruct the binary tree from this data structure. [serialization and deserialization]



2. 找两个排好序的list的共同元素
    5 -> 6 -> 6 ->8
    4 -> 4-> 6 -> 6 -> 8
答案是 6 ->  6 -> 8
两个list长度差不多是怎么做？ [two pointers; go one step on smaller value; each goes one step when find the same element] 长度相差非常大时如何做？ [hash the short one or bloom filter]


3. 有一个字典因为某种原因每个字符都被替换成一个别的字符了（但还是一一对应)，
但是单词的顺序没有改变，比如
cat
coffee
common
变成了
dkc
dbhhzz
dbllbq
让找出的这个替换的规则（guaranteed to have a unique one)
[according to frequencies; vowal][order of letters]


4. Find successor in in-order traversal [
if node.left: return node.left
elif node.right: return node.right
elif node.parent.left == node: return node.parent
else:
	p = node.parent
	while p.parent and p.parent.right == p: p = p.parent
	return p.parent
]

将1->2->3->4->5->6->7 变成 1->7->2->6->3->5->4，不能用额外空间

Valid Panlindrome, "A man, a plan, a canal: Panama" is a palindrome.
[
def valid_panli(s):
    n = len(s)
    i = 0; j = n-1
    while True:
        while i < n and not s[i].isalpha(): i += 1
        while j >= 0 and not s[j].isalpha(): j -= 1
        if i >= j: break
        if s[i].lower() != s[j].lower(): return False
        i += 1; j -= 1
    return True
]

#abcdef (eg 0x1F2A3B) 这种形式， 每个字母代表四个bit (hex)，两个字母代表一种原色, 比如 ab = R, cd = G, ef = B
现在需要压缩空间改#abcdef 为#xyz; 实际上#xyz = #xxyyzz,所以减小一半，问怎么找到最好的压缩让 (ab-xx)^2 + (cd-yy)^2 + (ef-z)^2 最小[00->[00,08], 11->[08,18], 22->[19,28],…]


设计一个算法，在分布式系统中拷贝某一个节点上的某一个文件到其他所有的节点上，要考虑时间代价和fault tolerance. [p2p copy]


给定两个list of integer，问是否他们是否互相是对方的一个从排列follow up: 如果不停的有新的list of integer过来，问是否这一列数以前出现过。 怎么存储？怎么查询？复杂度？[hashtable?]


发邮件的时候，我发给你一附件，你再发给100个人，这些人再要发来发去的，他不想存这么多份COPY,问我怎么设计，只保存一份附件。[the attachment is pointed to a static link?]



Decide if two graphs are with same structures drawn on board.


Given a 2–d matrix , which has only 1’s and 0’s in it. Find the total number of connected sets in that matrix. Explanation: Connected set can be defined as group of cell(s) which has 1 mentioned on it and have at least one other cell in that set with which they share the neighbor relationship. A cell with 1 in it and no surrounding neighbor having 1 in it can be considered as a set with one cell in it. Neighbors can be defined as all the cells adjacent to the given cell in 8 possible directions ( i.e N , W , E , S , NE , NW , SE , SW direction ). A cell is not a neighbor of itself.



设计
class  webcounter {
   void  increment();
   int  lastmin();
   int  lasthour();
   int  lastday();
}


Print function: odd line complete print
奇数行完全打印; 偶数行隔一个打印. 
i.e. 
	this is first line
	this is second line
Print:
	this is first line
	this second


给定内存buffer的指针void *buffer以及size，实现malloc()和free()，对内存的管理不允许使用动态数据块 (任何长度未知、可扩展的数据类型)
[free list/first fit/best fit/quick fit/buddy system][http://www.memorymanagement.org/articles/alloc.html][http://fileadmin.cs.lth.se/cs/Education/EDA050/F05.pdf]

实现double pow(double x, int y)即x^y（或x**y）。
[def pow(x,y):
    if y == 0: return 1
    if y < 0:
        if x == 0: return float('inf')
        y = -y
        neg = True
    else:
        neg = False
    ret = 1.0
    while y > 0:
        if y & 1 == 1: ret *= x
        y >>= 1
        x *= x
    return 1/ret if neg else ret]


1. Given a array of integers , find 3 indexes i,j,k such that, i<j<k and a[i] < a[j] < a[k]. Best possible is a O(n) algorithm.


2.You are given an array of n elements [1,2,....n]. For example {3,2,1,6,7,4,5}. Now we create a signature of this array by comparing every consecutive pair of elements. If they increase, write I else write D. For example for the above array, the signature would be "DDIIDI". The signature thus has a length of N-1. Now the question is given a signature, compute the lexicographically smallest permutation of [1,2,....n].


Given a serial of points (Xi, Yi), find the line containing most points from the list. [Hough transform][y=bx+c --> (b,c)]


如何实现find, insert, delete, getRandom 都是O(1) [hash table + array]


然后扯了下google的spanner那篇论文.


一个front end的人就问了一道题，写个程序，接收客户端的请求，如何保证每秒钟只发送10个请求给服务器.

一个印度哥们问如何用mutex和condition variable实现读写锁. [http://docs.python.org/2/library/threading.html#condition-objects]

从inorder, preorder数组构建二叉树

给个数组，打乱了，比如
索引 0 1 2 3 4
值   3 2 1 4 0
数组的值是下次跳的索引位置，这样的话数组有环，比如 0 -> 3 -> 4 -> 0  1 -> 2 -> 1， 求最长环的长度.

直线上有一个机器人从原点开始移动，每次可以向左移，也可以向右移，移动n步，再回到原点的概率是多少, 可以写程序实现。

如何一个程序大多数情况下运行的好好的，有时有问题，可能的原因.

1. Binary Tree Inorder Traversal

2. UTF-8 String 编码

3. 3sum的问题 [O(n^2)]

给一个筛子， 最大可以抛3次， 每次可以选择是否继续丢， 给一个策略让最后筛子的点数最大化。

1. biggest challenge in your current project.


知道(n&(n-1))得出什么结果吗? 可以用来做什么? [remove the leftmost 1]

以1/(2^n)的概率返回1，其它的时候返回0，题目应该假设有个函数可以生成1或者0，以1/2的概率--> 以p的概率返回1，其它的时候返回0，题目应该假设有个函数可以生成1或者0，以1/2的概率
http://blog.sina.com.cn/s/blog_b9285de20101h463.html


Use stacks to implement a queue. amortized time complexity? worst case complexity?


Least cost to cut a batten: the cost of cutting each segment is the cost of the segment length, an array is storing each end point, eg: [0, 3, 7, 8, 11, 12], the batten length is 12, there are 4 cutting point.

DP

删除一个单链中的节点， 不知该链表的head [node.prev.next = node.next]


50个白石头 50个黑石头， 放到两个盒子里。 任选一个盒子中的任意一个石头，怎么摆放石头使得拿到白石头的概率最大？[49W+50B in one box + 1W in the other]


两个骰子，一个是1-6的正常骰子，问怎么设置另一个骰子六个面上的数值，使得掷出两个骰子之后的和在1-12之内均匀分布[0,0,0,6,6,6]


Two sum


8瓶酒一瓶有毒， 用人测试。 每次测试结果8小时后才会得出， 而你只有8个小时的时间。 问最少需要几人并如何测试？


给一个文本， 然后给出几个关键词及他们所出现的位置，比如
this: 1, 16, 55....
is: 5, 33, 77...
要求找出最短的一段文章使其具备给出的关键词。[
L[i] = shortest length of such a message ended at i-th word.
L[i] = i-min{indices of each word < i and closest to i}


给出一颗tree, 该tree没有任何特征， 即可以有多个子节点， 父节点和左右子节点也没有大小关系。但每个节点的值不相等。现给出几个值， 如(12， 24) 请找出从根节点到值为12 和24的节点的subtree.


数据库里如何实现(解决？)一对多的关系？


给一个array, 再给一个sh值， 设计函数将数组内的所有元素向右偏移sh个位置(将数组看成一个圈)。[O(shN) in place; O(N) with space O(N)]


用binary search决定一个不知道多大的数组有多大

很多intervals, 比如谁几点到几点要开会， 找最大overlap还是什么的

九宫格的字母， 怎么从一个字典里面的词转到下个词，用bst

天上好多个星星， 找出最近的一对

二维版本water trap

给个字符串S，判断它是不是某个字符串T复制K次的结果。K未知，T未知。不要n^2的解

linux kernal和一些特殊的command

Write a Program to fill empty cell of a Partially filled MxM Crossword Puzzle to form a valid English words. So the cells initially will be either empty, filled or blocked(cannot be filled). You have access to English Dictionary.


abstract vs interface的区别。回答的内容忘说abstract只能单继承，interface可以多implement的区别了，对方通过不通过方式问了几遍，我才想起这个，感觉自己太二了。然后接着又问为什么java不让多继承，回答avoid dimond-shaped inheritance。

给一个文件，返回vowel（就是原因aeiou）数量最多的行。


设计一个cache。我写了一个HashTable的框架，用chaining处理collision。注意hush function这个核心东西没写，对方也说这个可以不写。然后说如果只允许table里面放1000个数据，怎么处理，答在put的时候如果超过可以抛异常。然后问如果不跑异常，而是在发现超过的时候，删去一个数据，放新数据怎么处理。答用LRU cache的思想。然后就实现这个cache，我用的java本身的LinkedList封装了一下。然后对方让在Hash table的put和get，remove方法里加入这个LRU cache的逻辑。写完这个时间也差不多了。就问了问设计的这个东西平均和最坏的时间和空间复杂度。结束。


有一块pizza，被分成n个slices。有两个人来分这块pizza。规则是你先拿一块，你的对手拿你之前拿掉的相连的两块。设计算法，拿到最多的pizza。~O(n2)的算法
http://www.mitbbs.com/article_t/JobHunting/32363487.html
http://www.mitbbs.com/article_t/JobHunting/31526013.html


2d matrix, each point represents the location of a person, find the meeting point which would minimize the distance traveled by all of them.
[Manhattan distance。答案的x/y坐标分别是N个点x/y坐标的median。二维问题可以分离：sum_i(|x-x_i|+|y-y_i|) = sum_i(|x-x_i|) + sum_i(|y-y_i|)。median的两边各有N/2个人，从median往左（上）走一步，有N/2个人多走一步，N/2个
人少走一步。如果左边人数多于右边，往左挪一步可以减少总步数。当然如果N是偶数，其实不需要真正的median，可以是最中间两个人之间的任何地方。]


(i) Define a quad-tree for a black and white image, count black pixels; (ii) Given two quad-trees and find the intersection of black pixels


----------------------------------------------------------------
Write a class DominoChecker that has a method called addBox(int[]) that takes a box of five dominoes, described as a list of 10 integers (explained after), adds it to a collection, and returns true if a box with the same dominoes was already in the collection and false otherwise. A box of dominoes is encoded as a list of 10 integers from 0 to 9, where a pair of numbers represent a domino. 
For example: 0,2,9,1,3,3,7,4,5,6 represents a box containing dominoes: (0,2); (9,1); (3,3); (7,4); (5,6).
注意: (1) 对于domino（0,2）和（2,0）是一样的.
      (2) addBox的时候需要track以前添加的box
DominoChecker checker = new DominoChecker(???? up to you ???);

checker.addBox("1234567811"); // returns false

checker.addBox("1233445566"); // returns false

checker.addBox("1233445566"); // returns true;

checker.addBox("3344556612"); // returns true;
checker.addBox("3344556621"); // returns true;


----------------------------------------------------------------

找出一个树中最深的结点。
明显留了好多细节让我问，于是我开始clarify:
1. 是不是binary tree。答：good question, yes, assume it's a binary tree
2. 是不是balanced binary tree。答：does that matter? 我边想边说，好像没关系然后我再也想不出其它问题了。开始想算法。想一半，他才提醒我说还有一个细节我没问：如果有多个最深结点怎么办。回答是返回最右的。这是一个失分的点
然后解释算法，可以用深度优先遍历也可以用广度优先遍历，记录所有叶节点的深度，
然后找出最深的。他问复杂度，我说时间是O(N), 空间是O(N). 他说空间能优化吗？我
说能， 在遍历过程中只记录最深的就行。他问这下的空间复杂度，我说是O(1) 。然后
让我开始写代码，我说深度优先呢还是广度优先，他说有什么区别，我说差不多，然后
想想不对，广度优先需要一个queue，这是O(N)的空间，深度的只要O(lgN)的空间，这
才想起刚才说O(1)的空间是错的，又一个失分点

Java: abstract class能不能定义constructor？我想了想，说好像不行，因为abstract class不能构造对象。然后他说其实不对，可以定义constructor，但是如果想建立它的对象，编译器会报错。我说，哦……那也是reasonable。他问这样的设计有什么好处？我说如果多个类的对象的构造步骤一样，只是各个步骤的细节有点不同，那么抽象类的constructor就可以调用抽象方法，来完成这些构造步骤；至于各个抽象方法的实现则取决定子类。他说没错。我知道这个某个design pattern的思想，但是当时想不出名字
，就没说（刚才查了一下，是template method)。

结出一个数组，代表一个小岛的横切面，每个数字代码高度，求这个岛中的湖能积多少水先是想着按数组的顺序一个个检查高度，找出各个湖的位置。想了会，没想出比较好的解法。于是换成另一种思路，一层层地检查，从0到小岛的最高点，记录每层能容的水量。这下就迅速把代码写出来了。然后问复杂度，我说是O(maxHeight*N*N)，如果maxHeight是个相对小的数，那么就是O(N*N)。他说不错，是polynomial time，然后自己说这个问题没法优化到O(N)，他以前试过。接着问我以前做过的项目，最喜欢哪个项目，为什么，然后就结束了。


int数组，实现increment +1.

两个不知长度的 int 数组，实现相加；

数组加1, 问变成16进制的时候怎么办？

实现encode,decode函数encode的参数是字符串的链表, 返回字符串. decode参数是字符串返回字符串链表. 最后list.equals(list.decode(encode(list))

如何找比一个int大的下一个palindrome。 Write a function which returns the next palindrome greater than the given number n. e.g., f(231) = 232; f(999) = 1001; f(111) = 121
[http://www.mitbbs.com/article_t/JobHunting/32274397.html]


Check if a number power of 3.


1. check if a string is anagram

2. Given a matrix which contains black and white grids, use a method to find out if the white grids are connected or not, if yes, return true. [start with a white cell, do BFS; after this if we still can find unvisited white cells…then False]


Given a string, find longest substring which contains just two unique characters. 

Design an API which is used for a cache big data

多个sorted list merge, 大致说了下思路，用最小堆。然后开始写

又问如果提供了一个merge两个list的函数，如何做。我先说了每次选两个进行merge,分析了一下时间复杂度。问有没有更好的解法，给了个hint,然后我就说了下用分治来做的算法的思路，分析了一下时间复杂度，面试官也说ok了，没让写代码。  


substring with concatenation of all words


有一个gym，用block表示。里面有健身器材，还有障碍物。让找一个最佳的位置放置椅子，使得椅子到所有健身器材的曼哈顿距离最短。
[http://www.mitbbs.com/article_t/JobHunting/32447621.html]


有一个item set a，和里面每个item出现的probability p，写一个function，每次调用返回一个item，很多次调用后，各个item返回的频率要符合其概率。可以调用一个random number generator。e.g. a = {'A', 'B', 'C'}, p = {0.3, 0.4, 0.3}
题目简单，把p转换成cumulative的，再做binary search。我是忘了preprocess p将其转换成cumulative，开始写的一个就是O（n）。问是否可以优化，我再指出可以对p做binary search。估计问题出在这里。


有很大一个电子图书馆，里面每本书的每一页都是OCR转换出来的text，所有每页大约有5%的error（转换错误，错误分割单词，跳脱。。。）。设计一个方法判定图书馆里是否有完全一样的书（duplicate），或者将来有书进来时判定同样的书是否已存在。这个问题有点类似face recognition，要自己extract features，自己做predictive modeling。[features: #pages, authors, title, similarities between each page, etc.]

一个整数plus one

Lowest common ancestor

A binary search tree is given. Find the ceiling value present in the BST of a given key.



给一个大文件每一行是: parentId:childId. parentId 和 childId 都是string.定义自己的数据结构，写一个函数预处理数据。写一个函数能够快速判断两个给定的id有没有关系，即有没有共同祖先。[Graph or tree?]


给定二维网格里N个点的坐标，取其中任意一个点，找到所有距离这个点不超过K的坐标点。距离的定义是X轴距离+Y轴距离。[http://www.mitbbs.com/article_t/JobHunting/32449259.html] [|x-xi| + |y-yi| <= k]


平面N个点着两点连线正好把点分到两半 [create a convex: the first point and N/2-th point (zero-based)]

Hash table (collision, probability)

Generate fuzzy words (Not in dictionary, but look like the given strings)
implement build() and nextWord(); e.g. ["APE", "APPLE", "ACE"] -> "ACE" (randomly)


Find intersection from two lists
How many 0s tailing in N! [count 5's]


10 buttons passcode with 4 digitals. Generate a sequence to brute force it. Upper bound and lower bound of length, code to generate an optimal one.
[itertools.product(range(10), repeat=4)]


Guess how a PDF file is structured
Program structure.

How to present a rectangle. Check two rectangle is intersected [left-bottom point, w, h]


Run Unit twice, passed the first time, and failed at the second. Why?
Forced context switching

Design a system to upgrade patches on remote data center
How to transfer the patch
How to install patch between computers (in the data center), fast and safe
If error happens, how to fix it
What will make this system down


A function to validate UTF-8 String [http://en.wikipedia.org/wiki/UTF-8]


How to break down a the watch-video page

Design a system to server video thumbs


Design a class to serialize and deserialize an object


Given a graph, and a source node, find all the descendants


Given an array, print the sequence in up-and-down manner, the first element is the biggest. e.g. 134652 will be printed to 645231



