public class C1 {
    protected int x = 1;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args){
        System.out.println(new C1().getX());
        System.out.println(new C2().getX());
        System.out.println(new C3().getX());
        System.out.println(new C4().getX());
    }

}


class C2 extends C1{
}



class C3 extends C2{
    protected int x = 3;
}



class C4 extends C3{

    protected int x = 4;

    @Override
    public int getX() {
        return x;
    }

}
