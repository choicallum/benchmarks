/**
 * Purpose: To sort an array into ascending order using the selection sort algorithm.
 * Author: Maria Litvin and Gary Litvin
 * Modified By: Callum Choi
 * Modified On: November 20th 2019
 */

public class SelectionSort
{
  
  public static void sort(double[] a)
  {
	// Goes through the entire array backwards to find the largest value and move it to the back.
    for (int n = a.length; n > 1; n--)
    {
      int iMax = 0;
      
      // Finds the largest value between the first value in the array and the n-1 value in the array.
      for (int i = 1; i < n; i++)
      {
        if (a[i] > a[iMax])
          iMax = i;
      }

      // Swaps the largest value with the n-1 value in the array.
      double aTemp = a[iMax];
      a[iMax] = a[n-1];
      a[n-1] = aTemp;
    }
  }
}
