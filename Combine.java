import java.util.ArrayList;

// Checks whether any combination of items in shoppingCart is present in Discounts map
public class Combine<T>{
    ArrayList<T> itemsToCombine;
    ArrayList<Integer> whereAreWe;

    public Combine(ArrayList<T> list){
        this.whereAreWe = new ArrayList<Integer>();
        this.whereAreWe.add(1);
        this.itemsToCombine = new ArrayList<T>();
        this.itemsToCombine.addAll(list);
    }

    public ArrayList<T> nextComb(){
        if(whereAreWe.size() > itemsToCombine.size())
            return null;
        ArrayList<T> comb = new ArrayList<T>();
        for(int i = 0; i < whereAreWe.size(); i++)
            if(whereAreWe.get(i) == 1)
                comb.add(itemsToCombine.get(i));
        int i = 0;
        do{
            if(whereAreWe.get(i) == 0)
                whereAreWe.set(i, 1);
            else
                whereAreWe.set(i, 0);
        }while(whereAreWe.get(i++) == 0 && i < whereAreWe.size());
        if(i == whereAreWe.size())
            whereAreWe.add(1);
        return comb;
    }

    /*
    public static void main(String[] argv){
        ArrayList<Integer> a = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++){
            a.add(i);
        }
        Combine<Integer> c = new Combine<Integer>(a);

        ArrayList<Integer> x;
        do{
            x = c.nextComb();
            System.out.print("{");
            if(x != null)
                for(int i : x)
                    System.out.print(i + ",");
            System.out.println("}");
        }while(x != null);
    }
    */

}
