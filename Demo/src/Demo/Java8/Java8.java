package Demo.Java8;

public class Java8 {
	
	public void testInterface()
	{
		InterfaceTest tt=new InterfaceTest() {
			
			@Override
			public void test() {
				// TODO Auto-generated method stub
				System.out.println("test");
				test1();
			}
		};
		tt.test();
		tt.test1();
		tt.test2();
	}
}

interface InterfaceTest {
	public void test();
	
	default void test1()
	{
		System.out.println("test1()");
	}
	
	default void test2()
	{
		System.out.println("test2()");
	}
}
