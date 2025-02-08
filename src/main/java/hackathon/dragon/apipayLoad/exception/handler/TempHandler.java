package hackathon.dragon.apipayLoad.exception.handler;


import hackathon.dragon.apipayLoad.code.BaseErrorCode;
import hackathon.dragon.apipayLoad.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}