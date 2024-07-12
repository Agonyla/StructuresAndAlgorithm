# 前缀树的相关题目

---

### 题目1：接头密匙

> 前置知识 : 讲解044-前缀树原理和代码详解-静态空间的方式实现

牛牛和他的朋友们约定了一套接头密匙系统，用于确认彼此身份

密匙由一组数字序列表示，两个密匙被认为是一致的，如果满足以下条件：

密匙 `b` 的长度不超过密匙 `a` 的长度。
对于任意 `0 <= i < length(b)`，有`b[i+1] - b[i] == a[i+1] - a[i]`

现在给定了 `m` 个密匙 `b` 的数组，以及 `n` 个密匙 `a` 的数组

请你返回一个长度为 `m` 的结果数组 `ans`，表示每个密匙 `b` 都有多少一致的密匙

数组 `a` 和数组 `b` 中的元素个数均不超过 `10^5`，`1 <= m`, `n <= 1000`

牛牛和他的朋友们约定了一套接头密匙系统，用于确认彼此身份
密匙由一组数字序列表示，两个密匙被认为是一致的，如果满足以下条件：
密匙 b 的长度不超过密匙 a 的长度。
对于任意 0 <= i < length(b)，有b[i+1] - b[i] == a[i+1] - a[i]
现在给定了m个密匙 b 的数组，以及n个密匙 a 的数组
请你返回一个长度为 m 的结果数组 ans，表示每个密匙b都有多少一致的密匙
数组 a 和数组 b 中的元素个数均不超过 10^5
1 <= m, n <= 1000

用前缀树方法：

- 时间复杂度，O(a数组的数字个数 * 10) + O(b数组的数字个数 * 10)
- 空间复杂度，O(a数组的数字个数 * 10)，这是树上的节点数量

--- 

### 题目2：数组中两个数的最大异或值

> 讲解030-异或运算的骚操作、讲解044-前缀树原理和代码详解

给你一个整数数组 `nums` ，返回 `nums[i] XOR nums[j]` 的最大运算结果，其中 `0<=i<=j<=n`,
`1 <= nums.length <= 2 * 10^5`,
`0 <= nums[i] <= 2^31 - 1`

前缀树做法 & 哈希表做法

- 时间复杂度O(n * logV)
- 空间复杂度O(n * logV)，V是数值范围

---

### 题目3: 在二维字符数组中搜索可能的单词

> 前置知识 : 讲解038-常见经典递归过程解析、讲解044-前缀树原理和代码详解

给定一个 `m x n` 二维字符网格 `board` 和一个单词（字符串）列表 `words`
返回所有二维网格上的单词。

单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格
同一个单元格内的字母在一个单词中不允许被重复使用
`1 <= m, n <= 12`,
`1 <= words.length <= 3 * 10^4`,
`1 <= words[i].length <= 10`

复杂度

- 时间复杂度，O(m * n * 4^10) 不管用不用前缀树都是这个复杂度，只不过前缀树可以大量剪枝，优化常数时间
- 空间复杂度，O(words中所有字符串的全部字符数量)
