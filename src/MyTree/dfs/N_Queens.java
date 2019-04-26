package MyTree.dfs;

/*�����һ���㷨�����������n�ʺ����⡣
�����n�ʺ�����ָ��һ��n*n�������Ϸ���n�����ӣ�
        ʹ��ÿ��ÿ�к�ÿ���Խ����϶�ֻ��һ�����ӣ�����ڷŵķ�������
        ����һ��int n���뷵�ط���������֤nС�ڵ���15��*/
public class N_Queens {
    static int n;
    static int cnt;

    static int[] rec; // �±�����У�ֵ������

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
        // ���ڼ��������ܲ��ܷŵ����� �뷨���ǽ������в��ܷŵĵط���ֵ��Ϊ-1,���ֲ�̫�С�
        for (int col = 0; col < n; col++) {
            boolean ok = true;

            for (int i = 0; i < row; i++) {
                // ����ǲ����ڶԽ��߻���ͬ��ͬ��
                // �����Խ��� x-y��ͬ���ڸ��Խ���x+y��ͬ
                if (rec[i] == col || i + rec[i] == row + col || rec[i] - i == col - row) {
                    ok = false;
                    break;
                }
            }
            /* =======���������Ϊ�Ǽ�֦======= */
            // ��һ�е���һ�п��Է�
            if (ok) {
                rec[row] = col; // ���
                dfs(row + 1); // ��������һ��
                // rec[row]=0; //�ָ�ԭ״�����ֽⷨ�����Ƿ�ָ�״̬����
            }
        }
    }
}

