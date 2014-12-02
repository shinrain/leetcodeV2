/*
Given a 2–d matrix , which has only 1’s and 0’s in it. Find the total number of connected sets in that matrix. Explanation: Connected set can be defined as group of cell(s) which has 1 mentioned on it and have at least one other cell in that set with which they share the neighbor relationship. A cell with 1 in it and no surrounding neighbor having 1 in it can be considered as a set with one cell in it. Neighbors can be defined as all the cells adjacent to the given cell in 8 possible directions ( i.e N , W , E , S , NE , NW , SE , SW direction ). A cell is not a neighbor of itself.

*/
import java.util.*;
import java.lang.*;

class Solution
{
	static class pair
	{
		int i, j;
		pair(int _i, int _j)
		{
			i=_i; j=_j;
		}
	}

	int connectedSets(int[][] board)
	{
		int m = board.length;
		if(m==0) return 0;
		int n = board[0].length;
		if(n==0) return 0;
		int re = 0;

		boolean[][] visited = new boolean[m][n];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(board[i][j]==1 && !visited[i][j])
				{
					if(dfs(board, visited, i, j)) re++;
				}
			}
		}
		return re;
	}

	boolean dfs(int[][] board, boolean[][] visited, int i, int j)
	{
		int count =0;
		Stack<pair> q = new Stack<>();
		visited[i][j] = true;
		q.push(new pair(i,j));
		while(!q.isEmpty())
		{
			pair cur = q.pop();
			int ii = cur.i, jj = cur.j;
			count++;
			if(check(ii+1,jj,board,visited)) q.push(new pair(ii+1, jj));
			if(check(ii-1,jj,board,visited)) q.push(new pair(ii-1, jj));
			if(check(ii,jj+1,board,visited)) q.push(new pair(ii, jj+1));
			if(check(ii,jj-1,board,visited)) q.push(new pair(ii, jj-1));
			if(check(ii-1,jj-1,board,visited)) q.push(new pair(ii-1, jj-1));
			if(check(ii+1,jj-1,board,visited)) q.push(new pair(ii+1, jj-1));
			if(check(ii-1,jj+1,board,visited)) q.push(new pair(ii-1, jj+1));
			if(check(ii+1,jj+1,board,visited)) q.push(new pair(ii+1, jj+1));
		}
		if(count>1) return true;
		else return false;
	}

	boolean check(int i, int j, int[][] board, boolean[][] visited)
	{
		if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=1 || visited[i][j]) return false;
		visited[i][j] = true;
		return true;
	}

	public static void main(String[] args) {
		int[][] a = {
			{0,0,0,0,0,1},
			{0,0,0,0,0,1},
			{1,0,1,1,0,1},
			{0,0,1,1,0,1},
			{0,0,0,0,0,1},
		};
		System.out.println(new Solution().connectedSets(a));
	}
}