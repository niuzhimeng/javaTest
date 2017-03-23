package toArrayTest;

/**
 * Created by nzm on 2017/3/21.
 */
public class ClassLoader {
    public static void main(String[] args) {
        Test1 singleton = Test1.getInstance();
        System.out.println("counter1:" + singleton.getCounter1());
        System.out.println("counter2:" + singleton.getCounter2());
        // System.out.println(singleton.getClass().getClassLoader());
    }
}

class Test1 {
    private static Test1 test1 = new Test1();
    private static int counter1;
    private static int counter2 = 0;
    //private static Test1 test1 = new Test1();

    private Test1() {
        counter1++;
        counter2++;
    }

    static int getCounter1() {
        return counter1;
    }

    static int getCounter2() {
        return counter2;
    }

    static Test1 getInstance() {
        return test1;
    }
}
