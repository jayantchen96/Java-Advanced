import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassLoader().findClass("Hello");

            // new 实例
            Constructor constructor = helloClass.getConstructor();
            Object instance = constructor.newInstance();

            // 调用hello方法
            Method helloMethod = helloClass.getDeclaredMethod("hello");
            helloMethod.setAccessible(true);
            helloMethod.invoke(instance);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] info = new byte[0];
        try {
            info = getByteCode(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return defineClass(name, info, 0, info.length);
    }

    /**
     * 将经过加密的文件读入后返回解密后的byte数组
     * @param name 需要读进来的文件名 本题为 "Hello"
     * @return 解码后的byte数组
     * @throws IOException
     */
    private byte[] getByteCode(String name) throws IOException {
        FileInputStream fis = new FileInputStream("week01/src/" + name + ".xlass");
        BufferedInputStream bis = new BufferedInputStream(fis);

        List<Byte> byteList = new ArrayList<>();
        byte[] buffer = new byte[1024];
        int len;
        int offset = 0;
        while((len = bis.read(buffer)) != -1) {
            for(int i =0; i < len; i++) {
                byteList.add((byte)(255 - buffer[i + offset])); // 解码
            }
            offset += len;
        }

        bis.close();
        byte[] res = new byte[offset];
        for (int i = 0; i < offset; i++) {
            res[i] = byteList.get(i);
        }
        return res;
    }
}
