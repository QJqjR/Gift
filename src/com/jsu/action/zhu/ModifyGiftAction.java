package com.jsu.action.zhu;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.jsu.dao.zhu.GiftParameterDao;
import com.jsu.entity.GiftParameter;
import com.jsu.util.FormatUtil;

@Controller
public class ModifyGiftAction {
	private String gpId;
	private String gpprice;
	private String gpreseve;
	@Resource
	private List<GiftParameter> giftParameters;
	@Resource
	private GiftParameterDao giftParameterDao;
	private String jsonData;
	
	public String modifyGift() {
		
		/*gpId="11c46bcfb32b,1d4707281ce1";
		gpprice="254,552.42";
		gpreseve="384,8284";*/

		String[] id = FormatUtil.format(gpId);
		String[] price = FormatUtil.format(gpprice);
		String[] reseve = FormatUtil.format(gpreseve);
		double[] price1 = new double[price.length];
		int[] reseve1 = new int[reseve.length];
		 GiftParameter giftParameter;

		for (int i = 0; i < id.length; i++) {
			giftParameter = new GiftParameter();
			price1[i] = Double.valueOf(price[i]);
			reseve1[i] = Integer.parseInt(reseve[i]);
			System.out.println(price[i]);
			giftParameter.setPrice(price1[i]);
			giftParameter.setReseve(reseve1[i]);
			giftParameter.setgId(id[i]);
			System.out.println(id[i]);
		    giftParameters.add(giftParameter);
		}
		giftParameterDao.updateGiftPatameter(giftParameters);
		
		JSONObject obj = new JSONObject();
		obj.put("code", 200);
		obj.put("status", 1);
		obj.put("msg", "礼品参数修改成功！");
		jsonData = obj.toString();
		return "success";
	}

	public String getGpId() {
		return gpId;
	}

	public void setGpId(String gpId) {
		this.gpId = gpId;
	}

	public String getGpprice() {
		return gpprice;
	}

	public void setGpprice(String gpprice) {
		this.gpprice = gpprice;
	}

	public String getGpreseve() {
		return gpreseve;
	}

	public void setGpreseve(String gpreseve) {
		this.gpreseve = gpreseve;
	}


	public List<GiftParameter> getGiftParameters() {
		return giftParameters;
	}

	public void setGiftParameters(List<GiftParameter> giftParameters) {
		this.giftParameters = giftParameters;
	}

	public GiftParameterDao getGiftParameterDao() {
		return giftParameterDao;
	}

	public void setGiftParameterDao(GiftParameterDao giftParameterDao) {
		this.giftParameterDao = giftParameterDao;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	

}
