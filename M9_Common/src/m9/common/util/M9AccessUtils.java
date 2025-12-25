/*
 * ファイル名	：	M9AccessUtils.java
 * ファイル説明	：	オブジェクトアクセス権管理クラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/005/19	T.Nishi		新規作成
 * 02.00.00		2021/08/03	井上 建		メソッドへアノテーションを追加し、非推奨メンバ利用による警告を抑制
 * 										処理中で利用されていないインポートを除去
 */
package m9.common.util;

import com.teamcenter.rac.aif.AIFDesktop;
import com.teamcenter.rac.kernel.TCAccessControlService;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentUser;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;

/**
 * オブジェクトアクセス権管理クラス
 * @version	01.00.00
 * @date	2017/05/19
 * @author	MCAD T.Nishi
 */

public class M9AccessUtils {

	private TCSession tcSession;			//TCセッション
	private TCComponent component;			//対象オブジェクト
	private boolean isGranted = false;		//書込権が変更されたか
	
	
	/**
	 * コンストラクタ
	 * @brief TCセッションの取得と処理対象オブジェクトをクラス変数に保持する
	 * @param comp
	 */
	public M9AccessUtils(TCComponent comp){
		this.tcSession = (TCSession)AIFDesktop.getActiveDesktop().getCurrentApplication().getSession();
		this.component = comp;
	}
	
	/**
	 * オブジェクトをロックする。
	 * @brief オブジェクトをロックする。書込権限が存在しない場合、書込権限を付与してからロックをする。
	 * @param 
	 * @throws TCException, M9Exception 
	 * @throws M9Exception 
	 */
	@SuppressWarnings("deprecation")
	public void lock() throws TCException, M9Exception{
		//オブジェクトの書き込み権限をチェック。
		TCSession tcSession =(TCSession)AIFDesktop.getActiveDesktop().getCurrentApplication().getSession();
		if(!tcSession.getTCAccessControlService().checkPrivilege(component, TCAccessControlService.WRITE)){
			//書き込み権限を付与
			TCComponentUser user = tcSession.getUser();
			TCComponent accessor = tcSession.getTCAccessControlService().findAccessor("User", user.getUserId());
			tcSession.getTCAccessControlService().grantPrivilege(component, accessor,TCAccessControlService.WRITE);
			this.isGranted = true;	
		}
		//ロックが可能か？
		if(!M9TCUtils.canLockComponent(component)){
			throw new M9Exception("Object is Locked");
		}
		component.lock();
	}
	
	/**
	 * オブジェクトをアンロックする。
	 * @brief オブジェクトをアンロックする。書込権限が付与されていた場合、削除する。
	 * @param 
	 * @throws TCException, M9Exception 
	 */
	@SuppressWarnings("deprecation")
	public void unlock()throws TCException{
		//アクセサーを取得
		TCComponentUser user = tcSession.getUser();
		TCComponent accessor = tcSession.getTCAccessControlService().findAccessor("User", user.getUserId());
		
		//オブジェクトにアクセス権が付与されていた場合、削除する。
		if(this.isGranted){
			tcSession.getTCAccessControlService().removeAccessor(this.component, accessor);
		}
		//オブジェクトをアンロック
		this.component.unlock();
	}
	/**
	 * オブジェクトをセーブする。
	 * @brief オブジェクトのセーブ前後にロック,アンロック処理を行う
	 * @param 
	 * @throws TCException, M9Exception 
	 * @throws M9Exception 
	 */
	@SuppressWarnings("deprecation")
	public void saveAndUnlock() throws TCException, M9Exception {
		//セーブのためオブジェクトをロック
		lock();
		//オブジェクトをセーブ
		component.save();
		//オブジェクトをアンロック
		unlock();
	}
	
	/**
	 * オブジェクトをセーブする。(リレーション作成時)
	 * @brief オブジェクトのセーブ前後にロック,アンロック処理を行う
	 * @param isRelation
	 * @throws TCException 
	 */
	@SuppressWarnings("deprecation")
	public void saveAndUnlock(boolean isRelation) throws TCException {
		//セーブのためオブジェクトをロック
		component.lock();
		//オブジェクトをセーブ
		component.save();
		//オブジェクトをアンロック
		component.unlock();
	}
	
	/**
	 * オブジェクトを返却する
	 * @brief　オブジェクトを返却する
	 */
	public TCComponent getComponent(){
		return component;
	}
}
