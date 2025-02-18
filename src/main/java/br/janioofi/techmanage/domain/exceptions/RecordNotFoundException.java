package br.janioofi.techmanage.domain.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String msg){
        super(msg);
    }
}
