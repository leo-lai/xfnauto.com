package main.com.utils.eyekey;

/**
 * @Title: EyeKeyException.java
 * @Package: com.eyekey.exception
 * @ClassName: EyeKeyException
 * @Description: eyekey exception
 * @author zhangxinlei
 * @date 2015年1月6日 下午4:27:58
 * 
 */
public class EyeKeyException extends RuntimeException {

    /**
     * @Fields serialVersionUID : generate id
     */
    private static final long serialVersionUID = 316868376513512184L;

    public EyeKeyException(String msg) {
        super(msg);
    }

    public EyeKeyException(Exception e) {
        super(e);
    }

}
