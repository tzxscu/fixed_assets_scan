package com.tzxscu.cdfy.dbutils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 2018/3/12.
 */

public class webserviceutil {
    private static final String WEB_SERVER_URL = "http://192.168.101.4/WebService.asmx";
    private static String NameSpace=" http://tempuri.org/ ";

    public String dbupdate() throws Exception{
        String ret="";
        String methodname = "getexpdict";
        String soapAction = "http://tempuri.org/getexpdict";
        SoapObject request = new SoapObject(NameSpace,methodname);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.bodyOut=request;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transportSE = new HttpTransportSE(WEB_SERVER_URL);
        try {
            transportSE.call(soapAction,envelope);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        SoapObject object = (SoapObject)envelope.bodyIn;
        ret = object.getProperty(0).toString();
        return  ret;
    }
}
