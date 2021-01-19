/**
 * Purpose: To sort an array into ascending order using the mergesort algorithm.
 * Author: Maria Litvin and Gary Litvin
 * Modified By: Callum Choi
 * Modified On: November 20th 2019
 */

public class Mergesort
{
  private static double[] temp;
  
  // Begins the sorting process.
  public static void sort(double[] a)
  {
	// Takes the last value of the array and calls a recursive method.
    int n = a.length;
    temp = new double[n];
    recursiveSort(a, 0, n-1);
  }

 // Sorts from the parameters "from" to "to" with the given array.
  private static void recursiveSort(double[] a, int from, int to)
  {
	// Creates a base case, determines if "to" is next to "from" or if "from" is larger than "to".
    if (to - from < 2)      
    {
      // If the value of the "to" index in the array is larger than the "from" index, and "to" is greater than "from" by 1, it swaps the values.
      if (to > from && a[to] < a[from])
      {
        double aTemp = a[to]; a[to] = a[from]; a[from] = aTemp;
      }
    }
    // Otherwise, it splits the array in half and recursively calls itself again and calls merge after the recursive calls.
    else
    {
      int middle = (from + to) / 2;
      recursiveSort(a, from, middle);
      recursiveSort(a, middle + 1, to);
      merge(a, from, middle, to);
    }
  }

  // Merges two arrays from the overall array back together.
  private static void merge(double[] a, int from, int middle, int to)
  {
    int i = from, j = middle + 1, k = from;

    // Loops while both arrays have elements to continue to process.
    while (i <= middle && j <= to)
    {
      // Determines if the first or second array should go first in the overall array.
      if (a[i] < a[j])
      {
        temp[k] = a[i];
        i++;
      }
      else
      {
        temp[k] = a[j];
        j++;
      }
      k++;
    }

    // Copies the remaining values of the first half into the temp array if there are any.
    while (i <= middle)
    {
      temp[k] = a[i];   
      i++;
      k++;
    }

    // Copies the remaining values of the second half into the temp array if there are any.
    while (j <= to)
    {
      temp[k] = a[j];  
      j++;
      k++;
    }

    // Copies the temp array back into the original array.
    for (k = from; k <= to; k++)
      a[k] = temp[k];
  }
}
