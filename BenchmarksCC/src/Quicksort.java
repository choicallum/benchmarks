/**
 * Purpose: To sort an array into ascending order using the quicksort algorithm.
 * Author: Maria Litvin and Gary Litvin
 * Modified By: Callum Choi
 * Modified On: November 20th 2019
 */

public class Quicksort
{
  // Begins the quicksort process.
  public static void sort(double[] a)
  {
    recursiveSort(a, 0, a.length - 1);
  }

  // Sorts from the parameters "from" to "to"
  private static void recursiveSort(double[] a, int from, int to)
  {
	// Base case, if from is greater than to, the method stops.
    if (from >= to)
      return;

    // Chooses the pivot value, which all values will be compared against.
    int p = (from + to ) / 2;
    int i = from;
    int j = to;
    
    // Compares values to the pivot and swaps them if they're in the wrong spot.
    while (i <= j)
    {
      if (a[i] <= a[p])
        i++;
      else if (a[j] >= a[p])
        j--;
      else
      {
        swap (a, i, j);
        i++;
        j--;
      }
    }

    // Places the pivot in the correct position.
    if (p < j) 
    {
      swap (a, j, p);
      p = j;
    }
    else if (p > i)
    {
      swap (a, i, p);
      p = i;
    }

    // Recursively calls itself and continues to sort, splitting the array into two, one starting after the pivot and one ending before.
    recursiveSort(a, from, p - 1);
    recursiveSort(a, p + 1, to);
  }

  // Swaps the values given as the parameter.
  private static void swap (double[] a, int i, int j)
  {
    double temp = a[i]; a[i] = a[j]; a[j] = temp;
  }
}
