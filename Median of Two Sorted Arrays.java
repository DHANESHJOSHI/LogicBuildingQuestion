public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int totalLength = m + n;
        
        // Ensure nums1 is the smaller array to simplify calculations
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int leftHalfLength = (totalLength + 1) / 2; // Number of elements in the left half
        
        // Binary search on the smaller array nums1
        int low = 0;
        int high = m;
        
        while (low <= high) {
            int partitionX = (low + high) / 2; // Partition of nums1
            int partitionY = leftHalfLength - partitionX; // Corresponding partition of nums2
            
            // Calculate the elements to the left and right of the partitions
            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            int minY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            
            // Check if the partitions are correct
            if (maxX <= minY && maxY <= minX) {
                // Median found
                if (totalLength % 2 == 0) { // Even number of elements
                    return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
                } else { // Odd number of elements
                    return Math.max(maxX, maxY);
                }
            } else if (maxX > minY) { // Too many elements from nums1 in the right side
                high = partitionX - 1; // Move left
            } else { // Too many elements from nums1 in the left side
                low = partitionX + 1; // Move right
            }
        }
        
        // Control should never reach here if inputs are valid
        throw new IllegalArgumentException("Invalid input arrays");
    }
}
