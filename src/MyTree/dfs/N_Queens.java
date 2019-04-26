package MyTree.dfs;

/*请设计一种算法，解决著名的n皇后问题。
这里的n皇后问题指在一个n*n的棋盘上放置n个棋子，
        使得每行每列和每条对角线上都只有一个棋子，求其摆放的方法数。
        给定一个int n，请返回方法数，保证n小于等于15。*/
public class N_Queens {
    static int n;
    static int cnt;

    static int[] rec; // 下标代表行，值代表列

    public static void main(String[] args) {
        n = 4;
        rec = new int[4];
        dfs(0);
        System.out.println(cnt);
    }

    private static void dfs(int row) {
        //
        if (row == n) {
            cnt++;
            return;
        }
        // 关于检查这个点能不能放的问题 想法就是将将所有不能放的地方的值存为-1,发现不太行。
        for (int col = 0; col < n; col++) {
            boolean ok = true;

            for (int i = 0; i < row; i++) {
                // 检查是不是在对角线或者同行同列
                // 在正对角线 x-y相同，在副对角线x+y相同
                if (rec[i] == col || i + rec[i] == row + col || rec[i] - i == col - row) {
                    ok = false;
                    break;
                }
            }
            /* =======这里可以认为是剪枝======= */
            // 这一行的这一列可以放
            if (ok) {
                rec[row] = col; // 标记
                dfs(row + 1); // 继续找下一行
                // rec[row]=0; //恢复原状，这种解法这里是否恢复状态都行
            }
        }
    }
}

