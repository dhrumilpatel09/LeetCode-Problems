class Solution {
    public int lengthAfterTransformations(String s, int t) {
        final int MOD = 1_000_000_007;

        // dp[i] represents the length of character (char)('a' + i) after t transformations
        int[] dp = new int[26];
        // Initially, each character contributes 1 to the length
        for (int i = 0; i < 26; i++) {
            dp[i] = 1;
        }

        for (int step = 0; step < t; step++) {
            int[] newDp = new int[26];
            for (int i = 0; i < 26; i++) {
                if (i == 25) { // 'z'
                    newDp[i] = (dp[0] + dp[1]) % MOD; // 'z' -> "ab"
                } else {
                    newDp[i] = dp[i + 1]; // 'a' -> 'b', 'b' -> 'c', ..., 'y' -> 'z'
                }
            }
            dp = newDp; // update for the next round
        }

        // Calculate final length after t transformations
        long result = 0;
        for (char ch : s.toCharArray()) {
            result = (result + dp[ch - 'a']) % MOD;
        }

        return (int) result;
    }
}
