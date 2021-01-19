/**
 * Purpose: To sort an array into ascending order using the insertion sort algorithm.
 * Author: Maria Litvin and Gary Litvin
 * Modified By: Callum Choi
 * Modified On: November 20th 2019
 */

public class InsertionSort
{
  public static void sort(double[] a)
  {
	// Navigates the entire array starting at the first value.
    for (int n = 1; n < a.length; n++)
    {
      double aTemp = a[n];
      int i = n;
      
      // Goes through the array backwards until it finds a value that is less than the current value, while shifting values to the right.
      while (i > 0 && aTemp < a[i-1])
      {
        a[i] = a[i-1];
        i--;
      }
     
      a[i] = aTemp;
    }
  }
}

