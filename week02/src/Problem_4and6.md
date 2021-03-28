##### 4、(必做)根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

串行GC：只用一个线程来进行垃圾回收，并且是串行的，所以在进行垃圾回收时需要进行STW，并且由于只有一个线程，因此效率很低。

并行GC：在串行GC的基础上进行了改进，使用多个线程进行垃圾回收，但是与串行GC一样，在进行垃圾回收时业务线程是暂停的，也就是说也需要进行STW，但是吞吐量比串行GC高很多，在我们系统需要一个很高的吞吐量的时候可以使用并行GC。

CMS GC：CMS的意思是并发整理清除GC，即改GC是并发的，是可以和业务线程同时进行的，并且该GC只需要在初始标记和最终标记的时候进行短暂的STW，别的阶段都可以和业务线程同时执行，减少了STW的时间。

G1 GC：G1的意思是垃圾优先，G1GC在CMSGC的基础上做了进一步的改进，进一步减少了STW的时间。G1GC将内存空间分为一个一个的region，每个region存放的对象会随着时间的推移而改变，G1GC优先对那些垃圾比较多的region进行垃圾回收，由于每个region很小，所以垃圾回收需要的时间也越短，而且G1GC可以将垃圾回收控制在一个我们指定的时间内。

堆内存：堆内存在我们运行一个java程序的时候可以指定初始的堆内存大小以及最大堆内存大小，一般来说我们会把这两个参数设置成一样。相对来说，我们的堆内存至少需要能够容纳程序运行时所需的最大空间，否则就会报OOM错误。另一方面，相对来说，堆内存越大，GC发生的次数越少，每次GC所需要的时间也会越长。

**6.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [http://localhost:8801 ](http://localhost:8801/)，代码提交到 GitHub

```Java
private void testHttpClient(){
        String host = "http://localhost:8801";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(host);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        try {
            String content = httpclient.execute(httpget,responseHandler);
            System.out.println(content);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            httpclient.getConnectionManager().shutdown();
        }
    }
```





