class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> Map = new HashMap<>();
        for(int i = 0 ; i<s.length();i++){
            char ch = s.charAt(i);
            Map.put(ch,Map.getOrDefault(ch,0)+1);
        }

        for(int i = 0;i<t.length();i++){
            char ch = t.charAt(i);
            if(!Map.containsKey(ch) || Map.get(ch)==0){
                return false;
            }
            Map.put(ch,Map.get(ch)-1);
        }

    return true;
    }
}