public class NextBiggerNumber{

  public static int getNextBiggerNumber(int n) {
    char[] digits = String.valueOf(n).toCharArray();
    int i = digits.length - 2;
    while (i >= 0 && digits[i] >= digits[i + 1]) {
      i--;
    }
    if (i < 0) {
      return -1;
    }
    int j = digits.length - 1;
    while (digits[j] <= digits[i]) {
      j--;
    }
    char temp = digits[i];
    digits[i] = digits[j];
    digits[j] = temp;
    reverse(digits, i + 1, digits.length - 1);
    long result = Long.parseLong(new String(digits));
    if (result > Integer.MAX_VALUE) {
      return -1;
    }
    return (int) result;
  }

  private static void reverse(char[] arr, int left, int right) {
    while (left < right) {
      char temp = arr[left];
      arr[left] = arr[right];
      arr[right] = temp;
      left++;
      right--;
    }
  }

  public static void main(String[] args) {
    System.out.println(getNextBiggerNumber(17));
    System.out.println(getNextBiggerNumber(528));
    System.out.println(getNextBiggerNumber(6008));
    System.out.println(getNextBiggerNumber(2));
    System.out.println(getNextBiggerNumber(33));
    System.out.println(getNextBiggerNumber(975));
  }
}