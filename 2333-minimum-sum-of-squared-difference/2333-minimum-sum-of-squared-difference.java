class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;
        int[] diff = new int[n];
        long total = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            max = Math.max(max, diff[i]);
        }
        if (total <= k) {
            return 0;
        }
        int left = 0, right = max;
        while (left < right) {
            int mid = left + (right - left) / 2;
            long operations = 0;
            for (int d : diff) {
                if (d > mid) {
                    operations += d - mid;
                }
            }
            if (operations <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int limit = left;
        long remaining = k;
        long answer = 0;
        for (int d : diff) {
            if (d > limit) {
                remaining -= d - limit;
                d = limit;
            }
            answer += (long) d * d;
        }
        for (int d : diff) {
            if (remaining == 0) break;
        }
        return calculate(diff, limit, k);
    }
    private long calculate(int[] diff, int limit, long k) {
        long answer = 0;
        long operations = 0;
        for (int d : diff) {
            if (d > limit) {
                operations += d - limit;
                d = limit;
            }
            answer += (long) d * d;
        }
        long remaining = k - operations;
        for (int d : diff) {
            if (remaining == 0) break;
            if (d >= limit && d > 0) {
                answer -= (long) limit * limit;
                answer += (long) (limit - 1) * (limit - 1);
                remaining--;
            }
        }
        return answer;
    }
}
