class Solution {
    int n, k;
    long[][] cnt;
    int[] prod;
    int[] nums;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;
        this.n = nums.length;

        cnt = new long[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            nums[index] = value;
            update(1, 0, n - 1, index);

            long[] res = query(1, 0, n - 1, start, n - 1);

            ans[q] = (int) res[x];
        }

        return ans;
    }

    void build(int node, int l, int r) {
        if (l == r) {
            prod[node] = nums[l] % k;
            cnt[node][prod[node]] = 1;
            return;
        }

        int mid = (l + r) >>> 1;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node);
    }

    void update(int node, int l, int r, int index) {
        if (l == r) {
            prod[node] = nums[index] % k;

            for (int i = 0; i < k; i++) {
                cnt[node][i] = 0;
            }

            cnt[node][prod[node]] = 1;
            return;
        }

        int mid = (l + r) >>> 1;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        merge(node);
    }

    void merge(int node) {
        int left = node * 2;
        int right = node * 2 + 1;

        prod[node] = (prod[left] * prod[right]) % k;

        for (int i = 0; i < k; i++) {
            cnt[node][i] = cnt[left][i];
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int rem = (prod[left] * j) % k;
                if (rem == i) {
                    cnt[node][i] += cnt[right][j];
                }
            }
        }
    }

    long[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return cnt[node].clone();
        }

        int mid = (l + r) >>> 1;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        long[] a = query(node * 2, l, mid, ql, qr);
        long[] b = query(node * 2 + 1, mid + 1, r, ql, qr);

        int leftProd = getProduct(node * 2, l, mid, ql, qr);
        long[] res = new long[k];

        for (int i = 0; i < k; i++) {
            res[i] += a[i];
        }

        for (int i = 0; i < k; i++) {
            int rem = (leftProd * i) % k;
            res[rem] += b[i];
        }

        return res;
    }

    int getProduct(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return prod[node];
        }

        int mid = (l + r) >>> 1;

        if (qr <= mid) {
            return getProduct(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return getProduct(node * 2 + 1, mid + 1, r, ql, qr);
        }

        int a = getProduct(node * 2, l, mid, ql, qr);
        int b = getProduct(node * 2 + 1, mid + 1, r, ql, qr);

        return (a * b) % k;
    }
}
