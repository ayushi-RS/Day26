class Solution {
    
    public int[] nextGreaterElementIndex(int[] nums){
        int[] arr = new int[nums.length];
        Stack<Integer> st = new Stack<>();

        st.push(arr.length-1);
        arr[arr.length-1] = arr.length;

        for(int i= arr.length-2; i >= 0; i--){
            while(st.size() > 0 && nums[i] >= nums[st.peek()]){
                st.pop();
            }
            if(st.size() == 0){
                arr[i] = arr.length;
            }
            else{
                arr[i] = st.peek();
            }
            st.push(i);
        }

        return arr;
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] nGE = nextGreaterElementIndex(nums);
        int n = (nums.length - k) + 1;
        int[] arr = new int[n];
        int j = 0;
        
        for (int i=0; i < arr.length; i++){
            if(j < i){
                j = i;
            }
            while(nGE[j] < i + k ){
                j = nGE[j];
            }
            arr[i] = nums[j];
        }
        return arr;
    }
}