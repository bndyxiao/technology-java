import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: huangzhb
 * @Date: 2019年03月29日 09:18:48
 * @Description:
 */
public class MultiThreadTest {


    public static void main(String[] args) {
        List<Integer> idList = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            idList.add(i);
        }

        int threadNum = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        int perSize = idList.size() / threadNum;


        for (int i = 0; i < threadNum; i++) {
            MultiThread multiThread = new MultiThread(idList.subList(i * perSize, (i + 1) * perSize), countDownLatch);
            executorService.submit(multiThread);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();

    }

    static class MultiThread extends Thread {

        private List<Integer> idList;
        private CountDownLatch countDownLatch;

        public MultiThread(List<Integer> idList, CountDownLatch countDownLatch) {
            this.idList = idList;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            try {
                System.out.println("thread:" + Thread.currentThread().getName());
            }  catch (Exception e) {

            } finally {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }

        }

        public List<Integer> getIdList() {
            return idList;
        }

        public void setIdList(List<Integer> idList) {
            this.idList = idList;
        }

        public CountDownLatch getCountDownLatch() {
            return countDownLatch;
        }

        public void setCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
    }
}
