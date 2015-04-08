/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.core.lock.test;

import com.jdpay.ucc.core.lock.DistributedLock;

/**
 * <b>项目名</b>： com.jdpay.customer <br>
 * <b>包名称</b>： com.jdpay.customer.lock.test <br>
 * <b>类名称</b>： ZkTest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/3/23 13:11<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ZkTest {
    public static void main(String[] args) {
        muti();
    }

    public static void muti() {
        ConcurrentTest.ConcurrentTask[] tasks = new ConcurrentTest.ConcurrentTask[60];
        for(int i=0;i<tasks.length;i++){
            tasks[i] = new ConcurrentTasks3();
        }
        new ConcurrentTest(tasks);
    }

    public static void single() {
        new Thread(new Runnable(){
            public void run() {
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock("127.0.0.1:2181","test");
                    lock.lock();
                    Thread.sleep(3000);
                    System.out.println("===Thread " + Thread.currentThread().getId() + " running");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(lock != null) {
                        lock.unlock();
                    }
                }
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                DistributedLock lock = null;
                try {
                    lock = new DistributedLock("127.0.0.1:2181","test");
                    lock.lock();
                    System.out.println("---Thread " + Thread.currentThread().getId() + " running");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if(lock != null) {
                        lock.unlock();
                    }
                }
            }
        }).start();
    }

    static class ConcurrentTasks3 implements ConcurrentTest.ConcurrentTask {

        @Override
        public void run() {
            DistributedLock lock = null;
            try {
                lock = new DistributedLock("127.0.0.1:2181","test1");
                lock.lock();
                System.out.println("Thread " + Thread.currentThread().getId() + " running");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
