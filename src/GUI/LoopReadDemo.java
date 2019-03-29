package GUI;

import com.jlrfid.service.GetReadData;
import com.jlrfid.service.MainHandler;
import com.jlrfid.service.RFIDException;

public class LoopReadDemo implements GetReadData{

	/**
	 * @param args
	 * @throws RFIDException 
	 */
	public static void main(String[] args) throws RFIDException {
		// TODO Auto-generated method stub

		MainHandler handler = new MainHandler();
		if(handler.dllInit("R2k.dll")){
			if(handler.deviceInit("",4, 115200)){
				//System.out.println(handler.StopInv());
				handler.BeginInv(new LoopReadDemo());
			}
		}
	}

	public void getReadData(String data, int antNo) {
		if ("F0".equals(data)) {
			System.out.println("1");
		}else if ("F1".equals(data)) {
			System.out.println("2");
		}else if ("F2".equals(data)) {
			System.out.println("3");
		}else if ("F3".equals(data)) {
			System.out.println("4");
		}else if(!"".equals(data)){
			System.out.println("数据" + data + "天线" + antNo);
		}
	}

}
