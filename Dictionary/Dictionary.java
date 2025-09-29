public class Dictionary {
    private String[] words;

    public Dictionary(String[] words) {
        this.words = words;
    }

    public String findMostSimilar(String term) {
        String bestWord = null;
        int minDistance = Integer.MAX_VALUE;

        for (String word : words) {
            int dist = levenshteinDistance(term, word);
            if (dist < minDistance) {
                minDistance = dist;
                bestWord = word;
            }
        }

        return bestWord;
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j; // всі символи b вставити
                } else if (j == 0) {
                    dp[i][j] = i; // всі символи a видалити
                } else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // символи збігаються
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],    // видалення
                        Math.min(
                            dp[i][j - 1],    // вставка
                            dp[i - 1][j - 1] // заміна
                        )
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    public static void main(String[] args) {
        Dictionary fruits = new Dictionary(new String[]{"cherry", "pineapple", "melon", "strawberry", "raspberry"});
        System.out.println(fruits.findMostSimilar("strawbery")); // strawberry
        System.out.println(fruits.findMostSimilar("berry"));     // cherry

        Dictionary things = new Dictionary(new String[]{"stars", "mars", "wars", "codec", "codewars"});
        System.out.println(things.findMostSimilar("coddwars"));  // codewars

        Dictionary languages = new Dictionary(new String[]{"javascript", "java", "ruby", "php", "python", "coffeescript"});
        System.out.println(languages.findMostSimilar("heaven"));     // java
        System.out.println(languages.findMostSimilar("javascript")); // javascript
    }
}