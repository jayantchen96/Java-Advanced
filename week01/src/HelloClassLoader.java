import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> helloClass = new HelloClassLoader().findClass("Hello");

            // new 实例
            Object instance = helloClass.getConstructor().newInstance();

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
     * @throws IOException 抛出IO异常，让上层调用者处理
     */
    private byte[] getByteCode(String name) throws IOException {
        String postfix = ".xlass";
        BufferedInputStream bis = new BufferedInputStream(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(name + postfix)));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while((len = bis.read(buffer)) != -1) {
            for(int i = 0; i < len; i++) {
                buffer[i] = (byte)(255 - buffer[i]);
            }
            bos.write(buffer, 0, len);
        }

        byte[] res = bos.toByteArray();
        bos.close();
        bis.close();
        return res;
    }
}
