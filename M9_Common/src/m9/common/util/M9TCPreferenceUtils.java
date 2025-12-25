/*
 * ファイル名	:	M9TCPreferenceUtils.java
 * ファイル説明	:	TCプリファレンス取得ユーティリティクラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/05/10	S.Nomura	新規作成
 */
package m9.common.util;

import com.teamcenter.rac.aif.AIFDesktop;
import com.teamcenter.rac.kernel.TCPreferenceService;
import com.teamcenter.rac.kernel.TCSession;

/**
 * TCプリファレンス取得ユーティリティクラス
 * @file	M9TCPreferenceUtils.java
 * @brief	TCプリファレンスの取得を行うユーティリティクラス
 * @version	01.00.00
 * @date	2017/05/10
 * @author	MCAD S.Nomura
 *
 */
public class M9TCPreferenceUtils {

	/** Singlton */
	private static  M9TCPreferenceUtils instance_ = null;
	
	/** PreferenceService */
	private static TCPreferenceService prefService_ = null;
	
	/** コンストラクタ */
	private M9TCPreferenceUtils() {
		/** TCセッションを取得 */
		TCSession session = (TCSession) AIFDesktop.getActiveDesktop().getCurrentApplication().getSession();
		/** プリファレンスサービスを保持 */
		prefService_ = session.getPreferenceService();
	}
	
	/**
	 * インスタンス取得
	 * @brief	インスタンスを取得する
	 * @return	M9TCPreferenceUtils
	 */
	public static M9TCPreferenceUtils getInstance() {
		/**=========処理開始=========*/
		if (instance_ == null) {
			instance_ = new M9TCPreferenceUtils();
		}
		
		return instance_;
	}
	
	/**
	 * プリファレンス値取得(int)
	 * @brief	intのプリファレンス値を取得する	
	 * @param 	prefName
	 * @return	int	プリファレンス値
	 * @throws M9Exception 
	 */
	public int getIntValue(String prefName) throws M9Exception {
		Integer retVal = null;
		retVal = prefService_.getIntegerValue(prefName);
		
		if (retVal == null) {
			throw new M9Exception("the preference[" + prefName + "is not found");
		}
		
		return retVal.intValue(); 
	}
	
	/**
	 * プリファレンス値取得(double)
	 * @brief
	 * @param prefName
	 * @return	double
	 * @throws M9Exception 
	 */
	public double getDoubleValue(String prefName) throws M9Exception {
		Double retVal = null;
		retVal = prefService_.getDoubleValue(prefName);
		
		if (retVal == null) {
			throw new M9Exception("the preference[" + prefName + "is not found");
		}
		
		return retVal.doubleValue();
	}
	
	/**
	 * プリファレンス値取得(String[])
	 * @brief	Stringのリストのプリファレンス値を取得する
	 * @param 	prefName
	 * @return	String[]	プリファレンス値
	 */
	public String[] getStringValues(String prefName) {
		return prefService_.getStringValues(prefName);
	}	
}

