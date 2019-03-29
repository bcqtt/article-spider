package example;

public class MultiThread extends Thread {
	public void run() {
		for (int n = 1; n <= 20; n++) {
			try {
				sleep(300);
				System.out.println("输出第" + n + "次");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		MultiThread t = new MultiThread();
		t.start();
	}
}
