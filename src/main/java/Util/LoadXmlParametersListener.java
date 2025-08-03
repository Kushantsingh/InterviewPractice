package Util;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.Map;

public class LoadXmlParametersListener implements ISuiteListener {

    public void onStart(ISuite suite) {

      Map<String,String> xmlParameters= suite.getXmlSuite().getAllParameters();

      for (Map.Entry<String,String> xmlParameter:xmlParameters.entrySet())
      {
          System.setProperty(xmlParameter.getKey(),xmlParameter.getValue());
      }
    }

    public void onFinish(ISuite suite) {
    }

}
