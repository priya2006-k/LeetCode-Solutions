import java.util.*;

class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        events.sort((a,b)->{
            int ta=Integer.parseInt(a.get(1)), tb=Integer.parseInt(b.get(1));
            if(ta!=tb) return ta-tb;
            return a.get(0).equals("OFFLINE")?-1:1;
        });

        int[] mentions=new int[numberOfUsers];
        int[] offlineUntil=new int[numberOfUsers];

        for(List<String> e:events){
            int t=Integer.parseInt(e.get(1));
            if(e.get(0).equals("OFFLINE")){
                int id=Integer.parseInt(e.get(2));
                offlineUntil[id]=t+60;
            } else {
                String s=e.get(2);
                if(s.equals("ALL")){
                    for(int i=0;i<numberOfUsers;i++) mentions[i]++;
                } else if(s.equals("HERE")){
                    for(int i=0;i<numberOfUsers;i++) if(offlineUntil[i]<=t) mentions[i]++;
                } else {
                    for(String tok:s.split(" ")) {
                        if(tok.startsWith("id")) {
                            int id=Integer.parseInt(tok.substring(2));
                            mentions[id]++;
                        }
                    }
                }
            }
        }
        return mentions;
    }
}
