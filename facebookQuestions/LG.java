Given an array of numbers, nums, return an array of numbers products, where 
products[i] is the product of all nums[j], j != i.

Input : [1, 2, 3, 4, 5]
Output: [(2*3*4*5), (1*3*4*5), (1*2*4*5), (1*2*3*5), (1*2*3*4)]
      = [120, 60, 40, 30, 24]
You must do this in O(N) without using division.

---

  int[] helper(int[] A)
  {
    int n = A.length;
    if(n==0) return  null;

    if(n==1)
    {
      int[] re = new int[1];
      re[0] = 1;
      return re;
    }
    else
    {
      int[] re = new int[n];
      int prod = 1;
      re[0] = prod;
      for(int i=1;i<n;i++)
      {
        prod*=A[i-1];
        re[i] = prod;
      }

      prod = 1;
      for(int i=n-2;i>=0;i--)
      {
        prod*=A[i+1];
        re[i] *=prod;
      }
      return re;
    }
  }
=======

Find the node with given value in binary tree in in-order way,and return it;
PS: the binary tree may include two nodes with the same value.


  void helper(TreeNode root, int val, List<TreeNode> re)
  {
    if(root == null) return;
    if(root.val == val) re.add(root);
    helper(root.left,re);
    helper(root.right,re);
  }

  List<TreeNode> findVal(TreeNode root, int val)
  {
    List<TreeNode> re = new ArrayList<TreeNode>();
    helper(root, val, re);
    return re;
  }

===

1.1 gas station

---
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int n = gas.length;
    if(n!=cost.length) return -1;

    int min = Integer.MAX_VALUE;
    int sum = 0;

    int[] G = new int[n];
    for(int i=0;i<n;i++)
      G[i] = gas[i] - cost[i];

    for(int i = 0; i<n;i++)
    {
      sum+=G[i];
      if(sum<min) min=sum;
    }

    if(min>=0) return 0;

    for(int i=1;i<n;i++)
    {
      min = Math.min(sum, min-G[i-1]);
      if(min>=0) return i;
    }
    return -1;
  }


1.2 一个数组，找出一个solution使得1st《2nd， 2nd》3rd。。比如15462就是数组1
，2，4，5，6的一个solution。大牛指点下这个怎么弄？

====

  void reverse(int[] a, int l, int r)
  {
    if(l>=r || l<0 || r>=a.length) return;

    while(l<r)
    {
      int t = a[l]; a[l] = a[r]; a[r] = t;
      l++; r--;
    }
  }

  void reOrder(int[] a, int l, int r)
  {
    int n = r-l+1;
    if(n<=2) return;

    int mid = l+(r-l)/2;
    int left = l+(mid-l)/2;
    int len = left-l+1;

    System.out.println(mid+" "+left+" "+len);
    reverse(a, mid+1, mid+len);
    reverse(a, left+1,mid);
    reverse(a, left+1, mid+len);
    reOrder(a, l, l+2*len-1);
  }

    boolean valid(int[] r, int k)
  {
    if(k==0 || k-1==0) return true;
    if(r[k]<r[k-1]) return r[k-1]>r[k-2];
    if(r[k]>r[k-1]) return r[k-1]<r[k-2];
    return false;
  }


  void helper(int[] a, int k,List<List<Integer>> re,  int[] r, boolean[] used)
  {
    int n = a.length;
    if(n==k)
    {
        List<Integer> rr = new ArrayList<Integer>();
        for(int i:r) rr.add(i);
        re.add(rr);
      return;
    }

    for(int i=0;i<n;i++)
    {
      if(!used[i])
      {
        used[i] = true;
        r[k] = a[i];
        if(valid(r,k))
        {
          
          helper(a,k+1,re, r, used);
        }
        used[i] = false;
      }
    }
    return;
  }

  List<List<Integer>> reOrder(int[] a)
  {
    List<List<Integer>> re = new ArrayList<List<Integer>>();
    int n = a.length;
    if(n==0) return re;

    helper(a, 0, re, new int[n], new boolean[n]);
    return re;
  }

2. most frequent character in a huge string (10works 1master)， 如果一个big
文件在一个机子上怎么弄，如果多个小文件在多个机子上怎么弄？


3.1. return random node of a list, what if it can be modified concurrently

用hash可以，每个node一个lock也行

Reservior sampling. For i, getRandom(0,i)==> if rand == 0; replace the node with current one
      ==> and lock 


3.2. 1k Ads, how to make it only appear once across all servers, no master 
server

1K % numberOfMachine => index of machine to save the Ads.

每个机器上同时 maintain 一份(AdsIndex, machineIndex)的hashtable. 
这个需要做syncrhonization的. 


First of all， 是onsite，被5人轮虐。。。好在lunch哥们比较腼腆，没问问题，就
扯扯淡。
    
    @beefcurtain5
    5.不好意思没说清楚，generialezed cahce只是design方法的signature，比如get
(K), put(K,V)，这个我也是乱答，感觉出了get，put，clear之类不需要别的。。
    3.2 我答的也是consistent hashing
    3.1 这个题分之前需要做hash吗，不用话貌似得check所有的文件每一行，做了的
话貌似只看每个最大就可以。
    
    @Jc2013
    不是找最长。。就是找一个符合要求的序列就行，要求所有数组元素都在。
    
    @bladehaze
    这么多解法，眼花缭乱啊，不过方法4怎么感觉不是O（n），数组swap貌似代价粉
大。。用堆的想法不错，学习了，呵呵。
    
    @xiaofenglu
    1.2 分析过程很清晰！赞一个！
    3.1 这个不要用lock。。。用了就掉进陷阱。。。就try catch一下，如果
exception继续取一次就好。
    3.2 万一某个server挂了，如何让他负责的Ad继续由别的server发？你那个
hashtable维护起来很tricky的。。。所以我觉得consisten hashing应该是对的。
    
    @Icn
    1.2 上代码就比谈想法更进一步，呵呵，interviewer自己说的。。。



4.check generalized tree, follow up:return all generalized tree of its 
children， 比如
    1
2      3
4     5 6

这种情况下，2，4，5，6是valid的节点。

5.how to design general cache


===

今天看到一道Google的面试题，想了半天不知道自己的解法对不对，上来跟大家讨论一
下。

题目是装载问题的变种：要把n个箱子装到m个船上，每个箱子的重量是Wi, 每个船的载
重是Cj，问怎么样装才能把这m个船尽可能装满？即所有船的剩余空间之和最小。


Solution: should be greedy way. Sort Bucket and boxes.

===

1. 国男
2sum
数字有重复，比如如果sum是10，{2,2,2,8,8}里面算两个(2,8)pair。求pair总数。
Merge interval
对我的最后solution表示很满意。
2. 国男
stream of strings like this
1 3 4 5 6
3 4 5 6 3
4 5 6 3 3
...
每行是一个包含数字的string。去除所有数字完全重复的strings.比如这里的第二和第
三行数字完全相同，可以合并成一个。要求合并所有数字完全重复的strings。
最后表示对我的优化结果不满意。

----

There are only 10 digits since they are in string. So should assign prime number to 
each digit and compute the product.

If the string is very long, compute prod%(magic) and summation



===
3. 有点像东南亚或者拉美后裔，英文无口音
Reverse linkedlist
不断要求优化。

---


====
4. 欧洲人
写一个小游戏。MxN 的格子上有一条蛇，蛇头可以向前，左，右移动，撞到自己身体任
何部位或者撞到边界就算死。
---
use HashSet<position> pos.

====
5. 阿三
有N个node,每个都不停的向外发送timestamps,具体发送哪些timestamp是每个node决定
的,从其他node来说是随机的.现在要收集这些node发送的所有timestamp.如果某个
timestamp被发现从超过99%的node上发送出来,记录下来.需要怎么做?这些timestamp很
多,是不能完全放进去内存里面的.如果node非常多,怎么scale?
我给的方案是用HashMap<Timestamp, count>,分布存到多台机器上面。阿三表示数据很
多，每台机器的内存都存不下，让我优化。我的进一步方案是再设定一个时限T，过期
的数据可以丢掉。阿三要求进一步优化。我的再进一步方案是对于这个对于这个时限T
再分割成n个小格。这个n需要通过实验根据具体实际情况来确定。如果在T／n时间里面
，某些Timestamp的count小于某个设定值，比如0.01N，认为这个timestamp被收集到0.
99N的可能性已经趋近于0，可以忽略了，从HashMap里面删除。最后阿三还是表示不满
意，不能完全理解我的方案。
---



===


输入是一个自然数T， 输出是(a_1,a_2,...,a_k)
使得a_1^2+a_2^2+...+a_k^=T， 并且k尽可能小
--

====


1. 层序打印 binary tree
2. 实现 BlockingQueue 的 take() 和 put()
public interface BlockingQueue<T>
{
    /** Retrieve and remove the head of the queue, waiting if no elements 
are present. */
    T take();

    /** Add the given element to the end of the queue, waiting if necessary 
for space to become available. */
    void put (T obj);
}

3. 实现一共 TwoSum interface
public interface TwoSum {
    /**
     * Stores @param input in an internal data structure.
     */
    void store(int input);

    /**
     * Returns true if there is any pair of numbers in the internal data 
     * structure which
     * have sum @param val, and false otherwise.
     * For example, if the numbers 1, -2, 3, and 6 had been stored,
     * the method should return true for 4, -1, and 9, but false for 10, 5, 
     * and 0
     */
    boolean test(int val);
}

====

L家电面（最新） 攒RP 求bless
发信站: BBS 未名空间站 (Tue Oct 14 19:58:05 2014, 美东)

运气不好，碰到老印三哥三姐，悲剧了。电话进来Late了。然后讨论简历。。。
上题，头有点晕。。。把题弄错了。一开始思路不对，就悲剧了。

Given a list of child->parent relationships, build a binary tree out of it. 
All the element Ids inside the tree are unique. 

Example:

Given the following relationships:

Child   Parent  IsLeft
15      20      true
19      80      true
17      20      false
16      80      false
80      50      false
50      null    false
20      50      true


You should return the following tree:
    50
   /  
  20   80
/    / 
15 17 19 16

Function Signature
    
/**
* Represents a pair relation between one parent node and one child node 
* inside a binary tree
* If the _parent is null, it represents the ROOT node
*/
public class Relation {
    public Integer _parent;
    public Integer _child;
    public boolean _isLeft;
}


/**
* Represents a single Node inside a binary tree
*/
public class Node {
    public Integer _id;
    public Node _left;
    public Node _right;
}

/**
* Implement a method to build a tree from a list of parent-child 
* relationships
* And return the root Node of the tree
*/
public Node buildTree (List<Relation> data)
{
//TODO
}



投的职位Linkedin Test Engineer(Mobile&Web)

背景：cs master +一年test engineer工作经验；

电面一轮+onsite5轮；

电面：什么是singleton，两道算法，printTreeByLevel，字符串含数字求数字和。

Onsite，第一轮manager面（女阿三），基本都是 behavior questions， 聊下文化和
做的项目；第二轮（国女）问的都是用selenium解决一些实际问题（automation），比
如在google search然后返回search结果的数目，怎么判断页面加载完毕等；第三轮吃
饭；第四轮（国男+印女），test strategy 给一个linkedin的feature，写一个完整的
test plan。最后一轮俩阿三，一男一女，问了一下做的项目，然后两道coding，这轮
答得不好，题目很简单，但是阿三表述一直不太清楚，感觉花了很久才明白到底问什么
；一个leetcode原题（fibonacci 数列的一个），还有一个实现stack的push和pop，但
要求每次返回middle number，主要是考察一些基本data structure。因为linkedin主
要是test在web和mobile，用的工具是selenium和appium，所以面试官也比较喜欢问这
方面。


How to implement a stack which will support following operations in O(1) time complexity?
1) push() which adds an element to the top of stack.
2) pop() which removes an element from top of stack.
3) findMiddle() which will return middle element of the stack.
4) deleteMiddle() which will delete the middle element.
Push and pop are standard stack operations.

The important question is, whether to use a linked list or array for implementation of stack?

Please note that, we need to find and delete middle element. Deleting an element from middle is not O(1) for array. Also, we may need to move the middle pointer up when we push an element and move down when we pop(). In singly linked list, moving middle pointer in both directions is not possible.

The idea is to use Doubly Linked List (DLL). We can delete middle element in O(1) time by maintaining mid pointer. We can move mid pointer in both directions using previous and next pointers

===

发个L家面经
发信站: BBS 未名空间站 (Fri Oct 10 02:23:04 2014, 美东)

phone 1:
1. Search for a Range (leetcode)
2. Decide whether a target is covered by a list of intervals (类似merge 
intervals)
第二题答的不好，感谢国人大哥大姐放水！

phone 2:
1. permutations (leetcode)
2. permutations II (leetcode)
3. 设计一个iterator class处理文件line by line


===
发个G家面经
发信站: BBS 未名空间站 (Fri Oct 10 19:19:53 2014, 美东)

设计一个类来限制query，如1000qps，或者说每秒钟只能发10封邮件
给定的API，now()返回当前milliseconds
实现类的函数allowRequest()，如果当前还有request剩余true,否则返回false


Reference:

boolean allowRequest() {
   int minInterval = 1000/N; // N is the max rate
   
   int current = now();
   int diff = current-last;
   last = current; //last would be a class private member
   if(diff < minInterval) 
      return false;
   else
      return true;
}

--

LinkedIn 电面面经（第一轮），攒人品
发信站: BBS 未名空间站 (Fri Oct 10 14:57:25 2014, 美东)

intern 两个leetcode题目：
Maximum Subarray
Maximum Product Subarray
攒攒人品，希望能进下一轮

===

Linkedin的一道电面
发信站: BBS 未名空间站 (Mon Sep 29 02:04:18 2014, 美东)

请问这道电面题Find the distance between two words in a list. The words can 
repeat.

===

上周onsite的，昨天刚接到电话，悲剧了~~ ，还是有些失落，上来为以后面试求求
bless，顺便说下经过， 签了nda，题目不太好多说，一共5轮：

第一轮：2道coding，问复杂度， 1道design，coding不难
第二轮：1道有关图的题，我给了个解，然后要降低复杂度，又给了一个，然后没时间
，就没写code，面试官说，that should work
第三轮：给了用数组表示的树的题，2问，第一问答得可以，第二问脑袋发晕，程序木
有写完。。。。
第四轮:  一个dp题，中间一个corner case没有考虑，给了个hint，后来做出来
第五轮：聊了点简历，然后做题，我给了个答案，对方让降低复杂度，想到了heap，但
是没完全想通


===

其他都比较常规，有一道图的题目，一个 n*n 矩阵，每个房间可能是封闭的房间，可
能是警察，可能是开的房间，封闭的房间不能过，返回一个 n*n矩阵， 每一个元素是
最近的警察到这个房间的最短距离。

BFS from each house.

===

面试之前在这里看了很多面经。非常感谢大家。现在贡献一下我的。顺便求一下bless
，希望能拿到心仪的offer。

new grad full time position. 一个白人小哥面的。

上来先讲了讲research。感觉就没答好。他问了一个我简历里research的一个linear 
programming的细节，但是我其实只是用了一点皮毛，对深层的原理不是很理解。结果
花了很多时间在这块还没答到重点。教训就是，不是特别在行的东西千万别放简历上。

然后就是coding，一共三题，这时候离结束就只有40分钟了，时间不怎么够。
1. 去除string中的空白
2. largest rectangle in histogram
3. 把regular expression tree 转换成表达式string
最后一题没写完就到只剩下五分钟了。小哥让我停下来跟我介绍了他的组，并且问问我
有什么问题之类。然后就结束了。题目都没答完肯定是没戏了。move on准备下一场。
祝各位找工作的都顺利！

忘了说了，电话打来晚了七分钟。有了这七分钟我最后一题肯定能写完的。郁闷！

update：
居然通知进onsite了，给大家对Google电面的bar提供一个参考。谢谢大家bless。等我
onsite完了再来update。
原帖里贴代码的同学，对不住你们了, 点修改选项不知道怎么点到删除上去了.

===

题目是这样的：学校的club需要申请活动教室来举办活动。学校一共有5个活动教室（
c1,c2,c3,c4,c5），每个教室在同一时段最多只能同时被3个活动占用，而且同一个活
动在同一时间段内可以同时在几个教室进行（比如活动a1可以在1点到两点之间可以同
时在c1和c2举办活动）。现在给你一份学校各种club的活动申请表，让你挑出符合上述
条件的所有活动来。input是个txt文件，里面有活动的Id,开始时间，终止时间。
Output只需打印出活动id，教室id，开始时间，结束时间。 

我想出的解法是简单的贪心算法，就是按照活动的结束时间排序，然后对于每一个活动
遍历5个教室，如果overlap小于等于3就放进去。但是发现这个解法用不到题里的这个
条件：“而且同一个活动在同一时间段内可以同时在几个教室进行”。我跟同学讨论半
天也不知道咋利用这个条件，是不是我们理解得有问题？

===


热腾腾的 LinkedIn 电面题攒RP
发信站: BBS 未名空间站 (Fri Sep  5 18:57:45 2014, 美东)


1. 2D matrix, sorted on each row, first element of next row is larger(or 
equal) to the last element of previous row, now giving a target number, 
returning the position that the target locates within the matrix

2.  Given a binary tree where all the right nodes are leaf nodes, flip it 
upside down
* and turn it into a tree with left leaf nodes.
*
* for example, turn these:
*
*        1                1
*       /               / 
*      2   3            2   3
*     / 
*    4   5
*   / 
*  6   7
*
* into these:
*
*        1               1
*       /               /
*      2---3           2---3
*     /
*    4---5
*   /
*  6---7
*
* where 6 is the new root node for the left tree, and 2 for the right tree.
* oriented correctly:
*
*     6                  2
*    /                 / 
*   7   4              3   1
*      / 
*     5   2
*        / 
*       3   1
*/

Solution:

Morris traverse modify

===

Linkedin 电面 面经x2
发信站: BBS 未名空间站 (Wed Aug 20 10:38:40 2014, 美东)

被recruiter 弄了连续面了2个组：

老印和中国人, 虽然国人不是主面试官，但是是不是的帮我一下，比如纠正我代码的
bug，但是说得不明显

1。查找2个单词的距离
/*
* Example:
*   WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList(the,
quick, brown, fox, quick));
*   assert(finder.distance(fox,the) == 3);
*   assert(finder.distance(quick, fox) == 1);
*/

2. 洗牌 要求in-place

第二面：老印和abc
中间abc一直没有吭声过。。。貌似这个题很常见，我另外2个朋友电面都碰到了，原题
，大家好好准备，其实不难，就是edge容易忽略

* Return the smallest character that is strictly larger than the search 
character, 
* [c, f, j, p, v], a => c

===

新鲜Linkedin电面面经
发信站: BBS 未名空间站 (Wed Sep 17 14:14:39 2014, 美东)

15分钟前结束，不出意外应该挂了，不知道是紧张还是咋的，几个边缘test case没有
想到。面试官一个烙印，一个老中，中国女面试官主考。以下是面的两道题：

1. valid number. LeetCode 原题，但是没写过，讨论了半天edge case，结果还是没
考虑-.的情况，最后经过提示算是勉强写出来了，但是code不是很clean。

2. Nested integer weighted sum. 一个list, 元素可能是list，也可能是Integer，
但是每个元素都包装在NestedInteger类里面了，求weighted sum. 例子是{2, {4, {6}
}}. 应该返回2×1 + 4×2 + 6×3. 我可能该开始就省题不清，写成了 (((6*3) + 4)*
2 + 1)*1. 经过面试官提醒，改了一个小地方就对了。感觉自己代码还算简洁，总共15
行左右。

大家轻拍，但是我个人感觉这在第一轮电面来说算中等偏上难度了，尤其是第一题，没
写过的话需要事先梳理好逻辑。

大家参考一下吧，个人长期潜水此版，获得了无数帮助，以后有面经一定第一时间奉上
。

===

刚面了L家，报个面经来攒攒RP。因为面的是Test Engineer，好像感觉要求没有那么高
。。。
1. 问知不知道什么是Singleton Pattern, 如何实现
2. 写一个Stack的API，包括push, pop和findMiddle功能，答案在这里能找到http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/

===

 我也说说L的面试
发信站: BBS 未名空间站 (Wed Sep 10 04:21:30 2014, 美东)

最近刚开始面了几轮说说我的感想 说错了的还有请L的资深人士指正

最好面的应该是application engineer,这个onsite就是manager面（一半behavior一半
project），tech communication(深入介绍你一个project) ，两轮算法，一轮design

前两轮人品口语不太差都能过 算法的话其实要求很低 “有些”(注意是有些)面试官居
然不需要你给出最优解 说实话我们题库99%都跟leetcode差不多 design不大好拿高分 
但只要不大差 别的轮都是positive hire commitee那都能过 所以其实挺好过的

别的position
infrastructure的会多面一两轮design
mobile的或者front end都有可能让你上机写一个简单project 我感觉都不是很好搞 还
不如写算法题 反正早刷烂了

至于需不需要bug free完全是看面试官各人喜好  code速度太慢了自然不行 一给出题
想都不想就迅速给出最优解显然也太假（看到好多面试官抱怨这个）我感觉最好还是 
先沉吟一下说一个比较naive的解 但不要着急code 自己跟面试官说觉得这个解答太慢
了 想一想接着再把最优解告诉他 当然仅供参考 大家自己把握

最后如果recuiter给你报价了 你有别的offer bid最好 没有的话也一定要找点借口说
钱太少了 很多时候都会给你稍微加点

最后最后祝大家都来L


=====

上周SWE电面，问了个超级简单的题目，我很快列举出各种corner case和三种复杂度的
解法，并把最优解写出（面试后在IDE下编译通过、运行各种test case通过）。第二天
HR电话回来说hire committee觉得我coding能力不强，改成SET的track。

昨天SET电面，问了个更令人发指的简单题——给一个string的ArrayList，把其中的
duplicate给删掉。。这回我写完程序后还列举了20个functional test cases和5个non
-functional test cases（面试后在IDE下编译通过，20个test cases）。今天接到HR
电话说依然被拒。。。


===

问道G家的题
发信站: BBS 未名空间站 (Sun Jul 27 02:17:33 2014, 美东)

看面经看来的，求大牛开解

Abbreviation: apple can be abbreviated to 5, a4, 4e, a3e, …
    Given a target string (internationalization), and a set of strings, 
return the minimal length of abbreviation of this target string so that it 
won’t conflict with abbrs of the strings in the set. 

“apple”, [“blade”] -> a4 (5 is conflicted with “blade”)
“apple”, [“plain”, “amber”, “blade”]  ->  ???

Problem changed to:
If given a string and an abbreviation, return if the string matches abbr.

“internationalization”, “i5a11o1” -> true

后半道是正常的string processing, 问题是前半道毫无头绪，跪求思路!

用个最土的方法，BFS来解，时间复杂度可能不是最优，抛砖引玉。
建一个bitset table，C++用vector<boost:dynamic_bitset<> >
每个row就是dynamic_bitset<> 存dict里面长度与target相同的string的第column位的
与target第column位的异同值，比如
apple, [blade] -> [[0],[0],[0],[0],[1]] 
apple, [“plain”, “amber”, “blade”] -> [[0,1,0],[0,0,0],[0,0,0],[0,0,0]
,[0,0,1]]。
完成转换以后可以一眼看出，如何得到答案，就是每个row全0的可以完全区分。以上第
二题1p3,2p2,3l1都是答案。
其中，头尾两个row最特殊，比中间row要abbr度要高，比如第一题a4,显然比1p3,2p2,
3l1要高。
对于复杂问题，没有一个row全0的话，就要用BFS，逐个logic_and组合，得到全0的row
。组合时
可以把全1的row全部踢掉，因为没帮助。
比如 aabadaa，[aabaxaa,aaxadaa] -> [[1,1],[1,1],[1,0],[1,1],[0,1],[1,1
],[1,1]] -> 第3位[1,0] & 第5位[0,1] -> 2b1d2


===

我看了一下你的解法，大概理解但是还是有疑问。比如这样的例子你的解法怎么计算？
target: abcde   字典：[aaaaa, bbbbb, ccccc, ddddd, eeeee]
我的想法跟你差不多不过是按照行进行编码（你把一个位置的放到一个数组里面，我一
个单词就是一个二进制如下）:
比如target apple [amble, plain] 这里amble就是10011 plain就是00000。
然后我们需要找到apple的一个最短abbr X满足一定的条件。
X也是可以对应二进制的，比如5就相当于00000   a4相当于10000  4e相当于00001  
ap3相当于11000
你就会发现符合条件的X是满足X|dict[i] != dict[i] （i is any binary code in 
dict）
比如你看a4在这里就不行因为10000 | 10011 == 10011 所以必须用1p3才能最短。

知道这个以后就把apple的各种缩写进行遍历，找到第一个不喝dict里面的binaries冲
突的就行。
复杂度2^(target lenth)*O(dict size)
当然可以优化，我觉得可以把dict转化成binaries的时候就可以记录下很多可以排除的
abbr了。但是具体怎么弄还没想清楚。

=-===


 google 电面
发信站: BBS 未名空间站 (Mon Sep  1 11:06:18 2014, 美东)

问了一个就是dictionary的题：
给一个dictionary：“hello,world,opt,pot;
一个target： pto,找出dictionary中的opt 和pot

===

电面：
第一次：印男，implement string matching and replacing
第二次：国男，producer consumer,谢谢中国小弟弟出了这个我非常熟悉的题

onsite:

第一轮：hiring manager，主要就是谈project，我讲了我最近在做的一个OO design的
东西，因为和面的组没啥关系，看得出来hiring manager是耐着性子听我说完的 :-P

第二轮：印男加国男，given a stream of data and a sliding window, implement 
put(), getAverage(),和另外一个function（忘了是啥了）。考虑multithreading的情况

第三轮: 吃饭

第四轮：最坑爹的一轮，recruiter告诉我也是跪在了这一轮，所以多说两句。来的是
个中年老印加一个中年国男，国男shadow。老印一出现就是一幅超鄙夷超不屑的臭脸。
出了一个inverted index的题，就是有一大堆doc，对doc里出现的word建inverted 
index，doc很多所以是distribute在很多machine上的，问怎么实现这个inverted 
index。我听了题目暗爽，这种题我准备的很充分啊！因为这题有很多解法，我就从差
到好一一说来，每个都说了为啥不好，然后引出我认为最好的那一个。可是我每说一个
老印就急吼吼的跳起来反驳我。搞了两回，我意识到可能老印就是想听最佳答案，我就
解释说我只是想list一下所有的option，我也问他你是不是prefer直接告诉你最佳答案
？他说是。我就直接给了我认为的最佳答案。

接下来还有几个相关的小问题我都忘了，有一个是关于map reduce的。最后一个，是找
出前K个最常用的word。我说用min heap找出每个machine的K个最常见word，再用一个
min heap merge这些list。奇葩的事情就出现了，老印跳出来说，不对，你用min heap
是不对的，应该用max heap！这时国男也一脸诚恳的“提醒”我说：是的，你用min 
heap找出来的是最不常用的K个word哦！我我我，我当场就愣了！不会吧，俩linkedin
的老员工了，好歹是个面试官啊，居然连min heap和max heap是啥都不知道？愣了两秒
，我就给他们讲了一遍啥是min heap啥是max heap，和为啥找K个最常用的word要用min
heap而不是max heap。一遍讲我一边想：我这是来面试的还是来给linkedin的员工培
训基本data structure的？最后俩人还是将信将疑，又问了一个有关于thread的小问题
就结束了。

第五轮：小印女加小印男，也是问了一个类似在stream里找k个最大数的题，我还是用
了min heap，还好俩人都知道啥是min heap，也比较认同我的做法。然后大部分的时间
都在讨论multi threading的实现，我提到了read write lock，和三种fairness 
policy，不过他俩都是一脸茫然，好像他们只知道read write lock，但不知道
fairness这回事，挺奇怪的。（另：刚又想起，这一轮还问了max point on a line一
题，leetcode上有，只要求pseudo code，我做了个square的，问小印男还有没有更快
的，他说就他所知没有了）。

第六轮：亚裔男（不是国男）加印男，问了romanToInt和intToRoman的题，intToRoman
和leetcode一样，但romanToInt要考虑很多corner case。这些corner case在leetcode
和EPI上都没有提到过。另外，好像EPI上的解法很多错误，我没看几道题就已经找出很
多错了，有的错的很离谱，大家得注意一下。

第七轮：白男加国女，问了一个design的题，design monitoring system，自我感觉是
发挥的最好的一轮。

面完以后我就觉得，成败就在第四轮，最后果然就跪在了这一轮。不过我是一点遗憾都
没有，要是给了我offer，让我去和对我天生有敌意的老印，还有分不清min heap和max
heap的人一起共事，其实也不是什么好事。还有我觉得好几个问道我multithreading
问题的人，本身对multithreading也不是很熟，像那个read write lock fairness 
policy的问题，还有lock free algo的ABA问题，他们好像都不知道，那他们平时是咋
把multithreading的程序写对的啊？

所以我现在挺疑惑的：是不是我特别倒霉，碰上的都是linkedin里水平比较低的一些人
，还是linkedin的员工水平并不像原本我想的那么高？




===



就是实现一个strchr，只不过第二个参数不是字符，而是字符串，返回第一次出现的指
针。

/*
Find the first occurrence in str of _any_ character in set. Both are NULL 
terminated ASCII C-strings. This is like strchr, except that the second 
parameter functions as a set, rather than a single character.

str       set     returns
qxcdef csz   str + 2 == &str[2]
axcdef wya  str + 0 == &str[0]
axcdef cxa   str + 0 == &str[0]
abc      efg    NULL

*/

这个就用一个hashtable做就可以了。结果麻烦开始了，他说用unicode怎么样，结果我
对unicode完全不了解，他就开始解释，就说unicode一个字符可能用1-5个char来表示
？那我就说假设有一个table帮你判定是不是unicode，那么就像wordbreak那个题一样
，往前走 碰到完整的unicode就记下来。

结果他说

ㅈㅏㅇㅏ ！= 장ㅏ

那我说怎么确定这个字符串的分割方式啊。。弄了半天。。然后他又说用_分割，因为
时间很紧张了，我就照这个意思简单地写了下code。应该让他举一个完整的例子的。写
完了他有点不置可否。

可能是因为时间不够，就用了10多分钟问了些简历，回答的也不算很好，就结束了。

有人能讲讲unicode用c字符串来表示是怎么个情况么。。

结果的话，估计是攒rp了。


===


1从本版获益非浅，献上面经并求 blessing.
共有四个技术面试
第一个面试官问题是 password generation.
一个四位的 password, 每位从 0-9, 求 generate 出一个最短的数字串, 使之能包括
所有的 possible combination.
这个面试回答得很糟糕，思考了半天也找不出 pattern 来。
最后用 brute force 方法草草了事。
后面三个问题比较轻松。
第二个问题是对无序数组找 Maximum 前n个的问题，
回答用 heap 解决。又问如果返回答案按原来次序，应该如何做，回答heap 里用
index，他好像很满意。
第三个面试 Given a charactor board[4][4] and a dictionary, count valid words
within the board
很简单，用 trie preprocess dictionary + 递归与回溯
第四个面试给两个整数串求公共元素，问不同长度又是如何，分析复杂度。
总体感觉不是很好，不能很好地表现自己。
希望借本版仙气增加一下人品，呵呵


===

Today I had my third-round interview with this company. Got some pretty 
tough questions (from my standard). I wont regret that I failed this one, 
because some questions are really beyond my ability.

1. n boxes, k balls, expected number of empty boxes. 

2. an array with 0s and 1s, find in O(n) time and O(1) space the longest 
sequence with equal number of 1s and 0s.

can do it with O(n) time and O(n) space. How to do O(1) space.

3. Suppose you have a four digit code. each one takes 0~9. 

Now suppose that you have a very stupid code machine. you can enter any 
length of coed into it:

if you enter 123456

it considers you entered three code:

1234
2345
3456

What is the smallest length of string I need to enter to guarantee that I 
find out your code? How would you write such a string?

===

国内Google电面两轮 已挂
关键字: google,电面
发信站: BBS 未名空间站 (Mon Nov 11 12:42:55 2013, 美东)

10月17日，第一轮电面：
   第一题：上海的电话isTree(vector<pair<int,int> >& edges);  (离散化+dfs判环
判联通)
   第二题，               
     Given a 2D space of maximum size NxN which supports two operations :
     [1] void UPDATE(x,y,v) - sets the value of cell [x,y] to v
     [2] int QUERY(x1,y1,x2,y2) - returns sub-rectangle sum (x1,y1) to (x2,
y2)
      inclusive, and there is an infinite stream of such 2 types of 
operations which have to supported. How would you store the values for 
efficient updates and retrievals ? （二维线段树  说算法+分析复杂度）
    第一轮答得还可以。
10月30日，第二轮电面：(挂)
    美国的电话，面试官很nice：
    第一题。一个二叉树，节点值有正有负，求树中的任意路径的最大值。路径的值就
是路径经过点的值的和。然后我说dfs，面试官就让写代码  。 写代码一开始dfs的接
口声明有问题，期间停下来修改了一下，然后写完。被面试官查出1个bug。面试官提示
了，一开始找出了另外个bug；面试官又提示了一下，才找出了面试官想要我fix的bug
。杯具。
    第二题。给三个字符串，a，b，c。问a 和b 能不能组成c，且保证c 中a b 的字母
顺序不变。 一开始我给了一个没验证贪心的想法。然后面试官让我验证或举个反例。
我想贪心多数没戏，就又说了种dp的思路。面试官让我写下来。我写完之后，让我解释
了解释。然后突然悲催地发现我的解法是O(2^n)的。。面试后想想如果我的解法状态去
重后，就和普通的dp无异了。。  杯具  。。 然后就挂了。
    哎，悲伤，简单题目答成这样，白白浪费了这次机会。
    背景：国内渣本科毕业一年，有ACM竞赛经验。平常做算法题还可以，leetcode上
刷题也从没看过其他人的题解。还是把google电面想的太easy了，所以心态上有松懈。
而且加之早上起早有些困。。。 总之这些都是次要原因，主要还是因为太他妈挫了。
    好好努力，来年再战！

    ====
google面的是SRE

电面是国人大哥，一些c语言的pointer问题，然后一道leetcode原题

onsite1:
1)combination,还是没dupliate的，要把结果保存起来，我说用linked list，因为是
用c写，还要自己implement linkedlist， 略坑爹
2) 就是很简单的统计两个string分别有多少个单独的letter

onsite2:
bst inorder iterator

onsite3:
一个文件，每行是rack_name + machine id，输出每个rack有多少个machine,按大小排
序，我是先扫一遍存hashtable，再存进linkedlist再sort，这回没让我实现hashtable
跟linkedlist了，不过要我把用过的api单独再declear一下，最后再写个mergesort

onsite4:
有很多个machine，要求检测哪些die了，要求parallel，就写了一个for loop创建若干
个thread来执行任务，有点thread pool的感觉,用一个array来表示哪个machine被检测
了与否,最后再写个检测的函数，要求socket programming，我就用tcp connect了一下
，里面的各种api(pthread_thread, connect)我都不记得了，随便写写意思意思

onsite5，system design
1) 电话号码查询，其实就是hashtable，先一个machine再scale，consistent hash跟
redundancy 
2）如何提高disk 数据访问的latency，我给的答案有 1,cache, 2 seqencial visit 3
, redundancy disks一起访问，用最快返回的数据 
3）连不上server了怎么trouble shooting，这题坑太大了，就随便答答 

HR的反馈是我虽然代码写的快但考虑的不够仔细，我估计是第四轮吧，呵呵


===


google 店面

就是如何实现find, insert, delete, getRandom 都是O(1)，然后扯了下google的
spanner那篇论文.

twitter 店面

第一轮.
1.如何判断一棵树是BST.
2.用2个栈实现队列。

第二轮
1.讨论hash table和如何解决collision, 各种解决策略的优缺点.

2. 关于图的简单BFS的一道题。


然后就是onsite了，这个我真的是是准备有问题，第一天面的google, 第二天面
twitter, 去google面试第一天坐了8个小时飞机，到了都晚上8点了，搞得第二天不在
状态了。

google onsite

第一轮，一个front end的人就问了一道题，写个程序，接收客户端的请求，如何保证
每秒钟只发送10个请求给服务器。这题他的意思我现在都不明白，他的意思是用平均速
度，看当前请求的时间和上个请求的时间相差多少，如果大于0.1秒就转发，否则就丢
弃。我觉得有问题啊，然后就郁闷了

。。

第二轮，一个印度哥们问如何用mutex和condition variable实现读写锁。这个好久没
碰了，答得也不好。

午饭，觉得吃的一般啊，比twitter的差些。

第三轮，扯点工作经验，然后考了从inorder, preorder数组构建二叉树，我这题写的
有bug, 当时想就废了.

第四轮，国人哥们，安慰了我一下。出了2道题，第一个是给个数组，打乱了，比如

索引 0 1 2 3 4
值   3 2 1 4 0
数组的值是下次跳的索引位置，这样的话数组有环，比如 0 -> 3 -> 4 -> 0  1 -> 2 
-> 1， 求最长环的长度.

第二题，直线上有一个机器人从原点开始移动，每次可以向左移，也可以向右移，移动
n步，再回到原点的概率是多少, 可以写程序实现。

这两题我答得挺好的，才进入状态。。

第五轮。
就讨论如何一个程序大多数情况下运行的好好的，有时有问题，可能的原因.
感觉答的还可以。

twitter onsite
我觉得twitter onsite的题目我都写对了，面试官没有发现任何bug，本来希望还蛮大
的，但还是被拒了。但反馈说了2点。 1是思路代码写得太快了

，我挺无语的，我写之前都和面试官说了下我的idea啊, 等确认了再写的，题做多了也
不行啊。。2是系统设计能力欠缺。

第一轮, 第一题，一个数组，求连续（每个元素挨着的）的最长递增子序列的长度，如
果数组很长，如何优化.
        第二题，就是那个爬梯子的题目了.

第二轮，就是设计一个手机上的下棋的游戏，涉及到客户端服务器端.

第三轮，Subsets problem. 然后扯项目经验.

中午吃饭，twitter的伙食真的很好，比google要强多了。

第四轮，扯扯项目经验，然后给2个sorted array, 求kth largest.

第五轮，n个排序链表，每个有m个元素，如何合并成一个。最开始说的是min heap的方
法，他要求的是O(1) space但是时间效率一样的，想出来了，然后证明时间开销，写了
代码。
        mirror a binary tree.

第六轮 我觉得这个可能是个我杯具的原因之一吧，老印老板问的问题都很奇怪，比如
从cache, ram, disk读一个字节需要多少时间。 1个200G的文件在硬盘上是如何存储的
，我没在我简历上说这些啊.

反正2家都杯具还是有点郁闷的，后悔没先其它公司先练练，到google的时候刚开始写
白板都没状态. 没有很难的题目，基本题练好就行了。twitter是家非常好的公司，但
是会很忙，我2次电话面试都因为面试官救火而重新安排的.

但愿这些能给后来的兄弟一些帮助啦。


====



1. 将一个数字的二进制形式以字符串的形式返回

2. 找两个已经排好序了的数组中的中位数（LeetCode原题）

3. 找一个字符串中最长的只含有N种不同的字符的子字符串

4. 设计题：设计一个随机数产生器，有一个以列表形式保存的已经排序blacklist，输
出的数字如果出现在其中就要剔除。（是CareerCup原题）

==


2. Given a list of words, find two strings S & T such that:
    a. S & T have no common character
    b. S.length() * T.length() is maximized

刚从板上看到这题， 我能想到的就是greedy search解法, 都需要O(n^2 * l), n是单
词个数, l是单词平均字母数

这题怎么才能做得比O(n^2)更快

==

昨天面的，背景聊了快20分钟，然后
（1）what is BST?
(2) 可不可以里面有duplicate value.
(3) 如何handle duplicate value, 不同handle 策略有什么优缺点。
（4）然后选了一种我说的，把duplicate全放在left sub tree, 然后写一个function 
判断是不是BST, 就是 validateBST
我写了一个最简单的，分析了复杂度， 是O(n^2), 问可不可以optimize,我说可以。
便写了一个O(n)的用low 和high 来bond. 

然后程序有个小bug,经提示改了，又问这个方法有什么drawback,我说遇到 root=INT_
MAX, root->right=INT_MAX会overflow, 然后要求处理overflow. 

面了50分钟。

今天打电话过来说要onsite.

给大家一个参考。。
--

L:

问答题
Write-through cache vs write-back cache 
whats memory mapped file

算法题，都是老题
1) 给一个nested的int array， 返回sum of int weight by its depth
2) 写一个支持removeRandom的hashtable
3) 一串字符串，返回有多少个substring符合某些pattern，这些pattern都是10char的
长度，所以逐个比较就可以了
4) tree lowest ancestor( tree node have parent pointer)


===

google面的是TSE, technical solution engineer

network trouble shooting，场景是客户说无法上网，让你如何一步步isolate 
problem，很坑爹的题目

还有就是给一堆http的报文，问你发生了什么


==

Google电面面经并求Bless
发信站: BBS 未名空间站 (Wed May 14 13:18:36 2014, 美东)

周一google电面，现在还在等消息，发发面经，攒攒RP，也希望得到大家的Bless！

第一题，水题，数组加一操作，for example, 输入[2, 7, 8, 9] 数组，加一后变成 [
2, 7, 9, 0]

第二题，给定输入这样的字符串
fft, fcp, aac, act, acd, atp, tbk, tdf, …
这些都是按照字母排序好的，但是字母顺序改了，比如 f 在 a之前，t在d之前等等，
给定一些这样的rule，问怎么rebuild the alphabet?

Topology sort.

==
用啥data structure设计linkedin的connection。（how about twitter)

接着讨论linkedin的各层connection之间的一些逻辑关系，比如第三层connection肯定
不会直接连到第一层或者本人。怎么给同一层的connection排序（应该推荐那些）

之后让写code，提供得到first level connection的函数，让实现得到second level 
connection。定义需要的相关class （比如people)。

面完问我对我们local的小office感不感兴趣，也不知道是有戏还是没戏。

===

平面N个点，找两点连线正好把点分到两半

Solution：极坐标算斜率 取median

===


session 1一个class {int a,  bool c, int b} 里面每个variable所占的空间都不同
，比如a,b是int 所以分别占4byte. bool的c只占1byte。还有其他变量，可能占8bytes
或者16bytes。都是2的次方就是。
问题是写一个程序让他们可以很好的被放到8byte为单位的block里面去然后空间不会浪
费。
比如如果是 就按照a, c, b的话它一共要占12个byte。因为当把a和c放到一个block的
时候就会浪费一些空间。
所以最好摆成a，b，c这样的话更合理。占9个byte。剩下的空间还可以放一些小的
object。
其实这个就是用排序，然后从大的变量依次放进block。
有个followup的问题就是：因为我不想过多移动这些变量，所以怎么才能设计一个算法
所需要移动的object最少。
比如如果变量的size一次是4, 4, 1, 1, 8, 8, 1, 1最好的排法是4, 4, 8, 8, 1, 1, 
1, 1.而不是8 8 4 4 1 1 1 1因为前一种所需要移动的cost最小。这个没想出来了。。
应该用divide and conquer？
session 2： 1. 设计算法找出平面上点的convex hull 不用写code（不熟。讨论下想
出，但是应该悲剧）
                   2. code 插入元素到max heap。
session 3： 1. 一个bit的stream， 每次读取6个bit。转化成char。
                   2. 很多URL，找到所有distinct的URL。（分布式计算）
session 4:   写出长度小于N的所有旋转对称数。
                   例子 689 顺时针旋转180度还是689
                   递归。也可以dp。
session 5: 设计数据结构，满足insert,delete,getRandom都是O(1)
就是这样了。结果估计不咋地。
anyway move on。
希望后面的面试结果不错。

==

given a string ,return the longest substring that contains at most two 
characters.


===

google面经
发信站: BBS 未名空间站 (Sat Aug  2 17:36:57 2014, 美东)

LZ在GOOGLE面试过两次， 第一次是大学毕业。 为了追CHICAGO的一个女孩子， 申请了
GOOGLE CHICAGO的位子。第一轮CAMPUS面试通过， 第二轮NEW YORK后， 收到CHICAGO
的EMAIL问我availability说要为我订机票第三轮面试， 三天后却收到NEW YORK的电话
说我申请的位子取消了， 我没被录用。后来我在NEW YORK找了一份IT的工作。 三年后
， 我决定跳槽其他的IT工作， GOOGLE刚巧邀请我去面试， 所以我第二次去GOOGLE面
试。 这次拿到了offer， 但是也拿到了更好的HFT的offer。虽然没去GOOGLE工作， 但
是我很喜欢GOOGLE的面试， 觉得每次都有收获。在此分享一下我被问过的问题。我也
很希望看到其他朋友们的面试问题， 我会当兴趣爱好来试解答。

Behavior Questions:

1. In java, a method declared as private restrict access to within the class
. For example, a private void doHeartbeat() method within Heartbeat class 
can only be called within the Heartbeat class. However, it doesnt prevent 
the Heartbeat class to access the method of a different Heartbeat object. 
For example, a private void forceHeartbeat(Heartbeat other) method is 
allowed to call other.doHeartbeat(). Java doesnt provide a way to limit 
access to a per object level. Why not?

2. Given set amount of memory and ram, how do you implement a process that 
takes the longest amount of time to finish? The process has to finish, it 
can not be an infinite loop. (Of all of the questions I got asked from 
Google, Im not sure I know the right answer to this one to this day)

Coding Questions:

1. Bar chart island. Given a two dimensional island that looks like a bar 
chart represented by an int array. Calculate the amount of water it can 
collect when it rains on the island. For example, [1,2,3] collects no water,
[2,1,3] collects 1 unit of water, [3,2,1,4] collects 3 units of water, [3,2
,4,1,5] collects 4 units of water. 

2. Ancestor. You are ancestry.com, you have a graph of related ancestors. 
One ancestor node contains the following fields: Node mother, Node father, 
Node[] children. Write a method that checks if two Nodes are blood related. 
For example, you and your half brother is blood related, your father and 
your mother are (hopefully but not guaranteed to be ) not blood related. 
Please note that information might be incomplete meaning mother, father or 
children can all be null.

3. Eviction Hash Map. Write a hash map that can store at most N key value 
pairs. If more than N key value pairs are associated, the least recently 
accessed key value pair is removed from the map. For example, for a map of 
capacity 3. put(1,1), put(2,2), put(3,3), put(4,4) will cause key 1 to be 
removed. However, put(1,1), put(2,2), put(3,3), get(1), put(4,4) will cause 
key 2 to be removed, put(5,5) will cause key 3 to be removed. 

4. Meeting place. You have a city with streets running parallel both 
horizonally and vetically creating a giant grid. The dimension of each grid 
is 1 X 1. All street corners in the city can be represented by a coordinate 
(int x, int y). Given an array of people represented by their closest street
corner, calculate a street corner to meet where their combined traveling 
distance is the shortest. Assume everyone can only travel on road. For 
example, the traveling distance from [1,1] to [2,2] is 2. 

5. Nth largest from tree. Given a binary search tree where the left node is 
smaller and the right node is larger. Calculate the Nth largest number in 
the tree throwing exception when there is less than N elements in the tree.

6. Anagram solver. An anagram is two words that contains the same letters 
the same amount of times. For example, angle and angel are anagrams. Given a
dictionary, perform some preprocessing for a anagram solver. The anagram 
solver takes a string as input and prints out a list of all anagrams 
contained in the dictionary.

7. Next tree sibling. Given a tree where each node has left and right 
pointers
, implement a function that sets the next pointer. Next pointer will point 
to a node in the same level immediately to the right. For example, if a node
has both left and right children, next pointer of the left child will point
to the right child. The next pointer of the right child will point to 
parents siblings left child. The fact that left child and right child can 
both be null make things complicated.


====

电面：
第一次：印男，implement string matching and replacing
第二次：国男，producer consumer,谢谢中国小弟弟出了这个我非常熟悉的题

onsite:

第一轮：hiring manager，主要就是谈project，我讲了我最近在做的一个OO design的
东西，因为和面的组没啥关系，看得出来hiring manager是耐着性子听我说完的 :-P

第二轮：印男加国男，given a stream of data and a sliding window, implement 
put(), getAverage(),和另外一个function（忘了是啥了）。考虑multithreading的情况

第三轮: 吃饭

第四轮：最坑爹的一轮，recruiter告诉我也是跪在了这一轮，所以多说两句。来的是
个中年老印加一个中年国男，国男shadow。老印一出现就是一幅超鄙夷超不屑的臭脸。
出了一个inverted index的题，就是有一大堆doc，对doc里出现的word建inverted 
index，doc很多所以是distribute在很多machine上的，问怎么实现这个inverted 
index。我听了题目暗爽，这种题我准备的很充分啊！因为这题有很多解法，我就从差
到好一一说来，每个都说了为啥不好，然后引出我认为最好的那一个。可是我每说一个
老印就急吼吼的跳起来反驳我。搞了两回，我意识到可能老印就是想听最佳答案，我就
解释说我只是想list一下所有的option，我也问他你是不是prefer直接告诉你最佳答案
？他说是。我就直接给了我认为的最佳答案。

接下来还有几个相关的小问题我都忘了，有一个是关于map reduce的。最后一个，是找
出前K个最常用的word。我说用min heap找出每个machine的K个最常见word，再用一个
min heap merge这些list。奇葩的事情就出现了，老印跳出来说，不对，你用min heap
是不对的，应该用max heap！这时国男也一脸诚恳的“提醒”我说：是的，你用min 
heap找出来的是最不常用的K个word哦！我我我，我当场就愣了！不会吧，俩linkedin
的老员工了，好歹是个面试官啊，居然连min heap和max heap是啥都不知道？愣了两秒
，我就给他们讲了一遍啥是min heap啥是max heap，和为啥找K个最常用的word要用min
heap而不是max heap。一遍讲我一边想：我这是来面试的还是来给linkedin的员工培
训基本data structure的？最后俩人还是将信将疑，又问了一个有关于thread的小问题
就结束了。

第五轮：小印女加小印男，也是问了一个类似在stream里找k个最大数的题，我还是用
了min heap，还好俩人都知道啥是min heap，也比较认同我的做法。然后大部分的时间
都在讨论multi threading的实现，我提到了read write lock，和三种fairness 
policy，不过他俩都是一脸茫然，好像他们只知道read write lock，但不知道
fairness这回事，挺奇怪的。（另：刚又想起，这一轮还问了max point on a line一
题，leetcode上有，只要求pseudo code，我做了个square的，问小印男还有没有更快
的，他说就他所知没有了）。

第六轮：亚裔男（不是国男）加印男，问了romanToInt和intToRoman的题，intToRoman
和leetcode一样，但romanToInt要考虑很多corner case。这些corner case在leetcode
和EPI上都没有提到过。另外，好像EPI上的解法很多错误，我没看几道题就已经找出很
多错了，有的错的很离谱，大家得注意一下。

第七轮：白男加国女，问了一个design的题，design monitoring system，自我感觉是
发挥的最好的一轮。

面完以后我就觉得，成败就在第四轮，最后果然就跪在了这一轮。不过我是一点遗憾都
没有，要是给了我offer，让我去和对我天生有敌意的老印，还有分不清min heap和max
heap的人一起共事，其实也不是什么好事。还有我觉得好几个问道我multithreading
问题的人，本身对multithreading也不是很熟，像那个read write lock fairness 
policy的问题，还有lock free algo的ABA问题，他们好像都不知道，那他们平时是咋
把multithreading的程序写对的啊？

所以我现在挺疑惑的：是不是我特别倒霉，碰上的都是linkedin里水平比较低的一些人
，还是linkedin的员工水平并不像原本我想的那么高？


===


<Coding Interviews: Questions, Analysis & Solutions>的第75道例题。

只能被2, 3, 5整除的数字被称之为丑数Ugly Number。下面的代码是求出按大小顺序第
index个丑数。稍作改动就能打印出前n个丑数，因为之前的丑数都存在数组uglyNums里。

下面是参考代码：

int GetUglyNumber_Solution2(int index) { 
    if (index <= 0) 
        return 0;
  
    int[] uglyNums = new int[index]; 
    uglyNums[0] = 1; 
    int nextUglyIndex = 1;  
    int index2 = 0; 
    int index3 = 0; 
    int index5 = 0;  
    while (nextUglyIndex < index) { 
        int min = Math.Min(uglyNums[index2] * 2, uglyNums[index3] * 3); 
        min = Math.Min(min, uglyNums[index5] * 5);  
        uglyNums[nextUglyIndex] = min;  
        while (uglyNums[index2] * 2 <= uglyNums[nextUglyIndex]) 
            ++index2; 
        while (uglyNums[index3] * 3 <= uglyNums[nextUglyIndex]) 
            ++index3; 
        while (uglyNums[index5] * 5 <= uglyNums[nextUglyIndex]) 
            ++index5;  
        ++nextUglyIndex; 
    }  
    int ugly = uglyNums[nextUglyIndex - 1]; 
    return ugly;
} 

===

发个L家面经，攒rp
发信站: BBS 未名空间站 (Wed Oct 15 00:40:01 2014, 美东)

发个第一轮电面的面经，为第二轮攒rp了
两道题
打印一个数的所有乘数组合，从大到小，不要有重复
merge interval
--


24=2*2*2*3
  =2*3*4
  =2*12
  =3*8
  =4*6

List<List<Integer>> factorCombination(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(n, 2, result, new ArrayList<Integer>());
        return result;
    }
    void dfs(int n, int start, List<List<Integer>> result, List<Integer> sub
) {
        if(n == 1) { 
            result.add(new ArrayList<Integer>(sub));
            return;
        }
        
        for(int i = start; i <= n; i++) {
            if(n % i != 0) continue;
            if(i == n && sub.isEmpty()) continue;
            sub.add(i);
            dfs(n / i, i, result, sub);
            sub.remove(sub.size() - 1);
        }
    }



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







