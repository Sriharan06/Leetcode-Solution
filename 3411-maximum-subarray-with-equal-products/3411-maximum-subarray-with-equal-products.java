class Solution { // standard LeetCode class
    public int maxLength(int[] nums) { // returns the length of the longest product-equivalent subarray
        int n = nums.length; // store array length so I do not recompute it
        int best = 0; // this tracks the maximum valid subarray length found so far

        for (int i = 0; i < n; i++) { // try every possible starting index
            long product = 1L; // running product of the current subarray
            long currentGcd = 0L; // running gcd, initialized from the first element when added
            long currentLcm = 1L; // running lcm of the current subarray

            for (int j = i; j < n; j++) { // extend the subarray one element at a time
                product *= nums[j]; // include the new element in the running product

                if (j == i) { // for the first element, gcd is just that value
                    currentGcd = nums[j]; // initialize gcd with the first number in the subarray
                } else { // otherwise update gcd with the new element
                    currentGcd = gcd(currentGcd, nums[j]); // gcd of the whole subarray so far
                }

                currentLcm = lcm(currentLcm, nums[j]); // update lcm incrementally using gcd

                if (product == currentGcd * currentLcm) { // check the product-equivalent condition
                    best = Math.max(best, j - i + 1); // update the answer with this valid subarray length
                }
            }
        }

        return best; // return the longest valid length found
    }

    private long gcd(long a, long b) { // Euclidean algorithm for greatest common divisor
        while (b != 0) { // keep reducing until the remainder becomes zero
            long temp = a % b; // remainder tells me the next state of the algorithm
            a = b; // shift b into a
            b = temp; // shift remainder into b
        }
        return a; // when b is zero, a is the gcd
    }

    private long lcm(long a, long b) { // least common multiple computed from gcd
        return a / gcd(a, b) * b; // divide first to reduce overflow risk, then multiply
    }
}