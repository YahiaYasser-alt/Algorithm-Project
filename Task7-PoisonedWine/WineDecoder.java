public class WineDecoder implements Decoder{
    private final int numOfBarrels;

    public WineDecoder(int numOfBarrels) {
        this.numOfBarrels = numOfBarrels;
    }

    @Override
    public int decode(boolean[] dead) {
        int l = 0;
        int r = numOfBarrels-1;
        int level=0;

        while(l < r){
            int mid = (l+r)/2;
            if(dead[level])
                l = mid + 1;
            else
                r = mid;
            level++;
        }
        return l;
    }
}
