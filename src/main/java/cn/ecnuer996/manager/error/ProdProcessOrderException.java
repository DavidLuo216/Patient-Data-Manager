package cn.ecnuer996.manager.error;

public class ProdProcessOrderException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProdProcessOrderException(String message) {
        super(message);
    }
}
