class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int res = 0, n = heights.length;
        for(int i = 0 ; i<heights.length ; i++){
            while( st.isEmpty()==false && heights[st.peek()]>=heights[i] ){
                int val = heights[st.pop()];
                int left = st.isEmpty() ? -1 : st.peek();
                res = Math.max((i-left-1)*val, res);
            }
            st.push(i);
        }
        while(st.isEmpty()==false){
             int val = heights[st.pop()];
             int left = st.isEmpty() ? -1 : st.peek();
             res = Math.max((n-left-1)*val, res);
        }
        return res;
    }
}
3 pass solution

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int left[] = new int[n];
        int right[] = new int[n];
        Stack<Integer> st = new Stack<Integer>();
        
        for(int i = 0 ; i<n ; i++){
            while(st.isEmpty()==false && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            if(st.isEmpty()){
                left[i] = -1;
            }else{
                left[i] = st.peek();
            }
            st.push(i);
            
        }
        
        st.clear();
        for(int i = n-1 ; i>=0 ; i--){
            while(st.isEmpty()==false && heights[st.peek()]>=heights[i]){
                st.pop();
            }
            
            if(st.isEmpty()){
                right[i] = n;
            }else{
                right[i]= st.peek();
            }
            st.push(i);
        }
        
        int res = 0;
        for(int i = 0 ; i<n ; i++){
            res = Math.max(res, (right[i]-left[i]-1)*heights[i]);
        }
        return res;
    }
}
