/*
 * ファイル名	：	M9TCUtils.java
 * ファイル説明	：	Teamcenter AIP ユーティリティクラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/04/28	S.Nomura	新規作成
 * 02.00.00     2018/12/19  和泉諒               createDataset()のツール指定版を作成
 */
package m9.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teamcenter.rac.aif.AIFDesktop;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentDatasetType;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentFolderType;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentItemType;
import com.teamcenter.rac.kernel.TCComponentTcFile;
import com.teamcenter.rac.kernel.TCComponentType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;

/**
 * Teamcenter AIP ユーティリティクラス
 * @version	01.00.00
 * @date	2017/04/28
 * @author	MCAD S.Nomura
 */
public class M9TCUtils {
	
	/**
	 * アイテムを作成する。
	 * @brief
	 * @param itemID	作成アイテムに割り当てるアイテムID(nullの場合は自動発版)
	 * @param revID		作成アイテムに割り当てるリビジョンID(nullの場合は自動発版)
	 * @param itemName	作成アイテムのオブジェクト名
	 * @param type		作成アイテムタイプ
	 * @return newItem 作成したアイテム
	 * @throws M9Exception 
	 */
	public static TCComponentItem createItem(String itemID,String revID,String itemName,String type) throws M9Exception {
		/**=========処理開始=========*/
		TCComponentItem newItem = null;
		try {
			
			AbstractAIFApplication app = AIFDesktop.getActiveDesktop().getCurrentApplication();	
			TCComponentItemType itemCompType = (TCComponentItemType) ((TCSession) app.getSession()).getTypeComponent(type);
			
			// ItemIDと一致する既存のオブジェクトを取得
			TCComponentItem[] itemComps = itemCompType.findItems(itemID);
			if (itemComps.length == 0) {
				newItem = itemCompType.create(itemID, revID, type,itemName, "", null);
			} else {
				// すでにアイテムが存在する場合nullを返却する
				return null;
			}
		} catch (TCException e1) {
			M9Logger.error(e1.getMessage());
			throw new M9Exception(e1);
		}catch(Exception e2){
			M9Logger.error(e2.getMessage());
			throw new M9Exception(e2);
		}
		return newItem;
	}
	
	/**
	 * データセットを作成する。
	 * @brief
	 * @param datasetName	オブジェクト名
	 * @param type			オブジェクトタイプ
	 * @return newDataset	 作成したデータセット
	 * @throws M9Exception 
	 */
	public static TCComponentDataset createDataset(String datasetName,String type) throws M9Exception{
		/**=========処理開始=========*/
		TCComponentDataset newDataset = null;
		try {
			AbstractAIFApplication app = AIFDesktop.getActiveDesktop().getCurrentApplication();	
			TCComponentDatasetType datasetType = (TCComponentDatasetType) ((TCSession) app.getSession()).getTypeComponent(M9Constant.DATASET_TYPE);
			//データセット作成
			newDataset =  datasetType.create(datasetName, "", type);
			 
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
		return newDataset;
	}
	
	/**
	 * データセットを作成する。（ツール指定版）
	 * @brief
	 * @param datasetName	オブジェクト名
	 * @param type			オブジェクトタイプ
	 * @param tool			使用ツール
	 * @return newDataset	 作成したデータセット
	 * @throws M9Exception 
	 */
	public static TCComponentDataset createDataset(String datasetName,String type,String tool) throws M9Exception{
		/**=========処理開始=========*/
		TCComponentDataset newDataset = null;
		try {
			AbstractAIFApplication app = AIFDesktop.getActiveDesktop().getCurrentApplication();	
			TCComponentDatasetType datasetType = (TCComponentDatasetType) ((TCSession) app.getSession()).getTypeComponent(M9Constant.DATASET_TYPE);
			//データセット作成
			newDataset =  datasetType.create(datasetName, "", type, tool);
			 
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
		return newDataset;
	}
	
	
	/**
	 * フォルダ作成
	 * @brief	指定された名前とタイプでフォルダを作成する。
	 * @param	revName		作成フォルダ名
	 * @param	type		作成フォルダタイプ
	 * @throws M9Exception 
	 * 
	 * @throws	TCException
	 */
	public static TCComponentFolder createFolder(String revName, String type) throws M9Exception  {
		
		TCComponentFolder folder = null;
		try {
			//フォルダタイプ取得
			AbstractAIFApplication app = AIFDesktop.getActiveDesktop().getCurrentApplication();	
			TCComponentFolderType folderType =(TCComponentFolderType) ((TCSession) app.getSession()).getTypeComponent(type);
			//フォルダ作成
			folder = folderType.create(revName, null, type);
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
		
		return folder;
	}
	
	
	/**
	 * リレーションを作成する
	 * @brief
	 * @param primaryComp	プライマリオブジェクト
	 * @param secondaryComp	セカンダリオブジェクト
	 * @param relName		リレーション
	 * @throws M9Exception
	 */
	public static void addRelation(TCComponent primaryComp, TCComponent secondaryComp, String relName) throws M9Exception {
		try {
			primaryComp.add(relName, secondaryComp);
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e.getMessage());
		}
	}
	
	/**
	 * uidからTCComponentを取得する
	 * @brief
	 * @param uid			UID
	 * @return TCComponent	取得コンポーネント
	 * @throws M9Exception 
	 */
	public static TCComponent getTComponent(String uid) throws M9Exception {
		/**=========処理開始=========*/	
		TCComponent comp = null;
		
		try {
			AbstractAIFApplication app = AIFDesktop.getActiveDesktop().getCurrentApplication();
			TCSession session = (TCSession) app.getSession();
			
			// TCComponentを取得
			comp = session.stringToComponent(uid);
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
		
		return comp;
	}
	
	/**
	 * オブジェクトタイプ名からTCComponentTypeを取得する
	 * @brief
	 * @param typeName	オブジェクトタイプ名
	 * @return TCComponentType 
	 * @throws M9Exception 
	 */
	public static TCComponentType getTypeComponent(String typeName) throws M9Exception {
		TCComponentType typeComp = null;
		
		try {
			AbstractAIFApplication app = AIFDesktop.getActiveDesktop().getCurrentApplication();
			TCSession session = (TCSession) app.getSession();
			typeComp = session.getTypeComponent(typeName);	// typeComponent取得
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
		
		return typeComp;
	}
	
	/**
	 * プロパティの表示名を取得する
	 * @brief
	 * @param tcComp	TCコンポーネント
	 * @param propName	プロパティ名
	 * @return 表示名 (表示名がない場合はプロパティ名を返却する)
	 */
	public static String getPropertyDisplayName(TCComponent tcComp, String propName) {
		String dispName = propName;
		
		try {
			TCProperty tcProp = tcComp.getTCProperty(propName);	// プロパティを取得
			dispName = tcProp.getPropertyDisplayName();		// 表示名を取得
		} catch (TCException e) {
			
		}
		
		return dispName;
	}

	
	/**
	 * タイプの表示名を取得する。
	 * @brief
	 * @param typeName	タイプ名
	 * @return 表示名(表示名がない場合は実名を返却する)
	 */
	public static String getTypeDisplayName(String typeName){
		String dispName = typeName;
		try{
			AbstractAIFApplication app = AIFDesktop.getActiveDesktop().getCurrentApplication();	
			TCComponentType tcType = (TCComponentType) ((TCSession) app.getSession()).getTypeComponent(typeName);
			
			// 存在しない場合は、typeNameを返却する
			if (tcType == null) {
				return typeName;
			}
			
			dispName = tcType.getDisplayTypeName();			
		} catch (TCException e) {

		}
		return dispName;
	}
	
	
	/**
	 * プロパティ値を取得する
	 * @brief
	 * @param propName	プロパティ名
	 * @param comp		TCコンポーネント
	 * @return プロパティ値
	 * @throws M9Exception 
	 */
	public static String getProperty(String propName, TCComponent comp) throws M9Exception {
		/**=========処理開始=========*/
		String propValue = "";

		try {
			propValue = comp.getProperty(propName);	
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}		
		return propValue;
	}
	
	/**
	 * オブジェクトに関連付くコンポーネントを全て取得する
	 * @brief
	 * @param primaryComp	プライマリオブジェクト
	 * @return	セカンダリオブジェクト
	 * @throws M9Exception 
	 */
	public static TCComponent[] getSecondaryComponents(TCComponent primaryComp) throws M9Exception {
		/**=========処理開始=========*/
		TCComponent[] secondaryComps = null;
		
		try {
			secondaryComps = primaryComp.getRelatedComponents();
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
		
		return secondaryComps;
	}
	
	/**
	 * 指定したリレーションに関連付くコンポーネントを全て取得する
	 * @brief
	 * @param relName		リレーション
	 * @param primaryComp	プライマリオブジェクト
	 * @return セカンダリオブジェクト
	 * @throws M9Exception 
	 */
	public static TCComponent[] getSecondaryComponents(String relName, TCComponent primaryComp) throws M9Exception {
		/**=========処理開始=========*/
		TCComponent[] secondaryComps = null;
		
		try {
			secondaryComps = primaryComp.getRelatedComponents(relName);
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
		
		return secondaryComps;
	}
	
	/**
	 * 指定リレーションで紐付いているプライマリオブジェクトを全て取得する
	 * @brief
	 * @param secondaryComp		セカンダリオブジェクト
	 * @param primaryType		取得するプライマリオブジェクトのオブジェクトタイプ		
	 * @param relName			リレーション
	 * @return プライマリオブジェクト
	 * @throws M9Exception
	 */
	public static List<TCComponent> getPrimaryComponents(TCComponent secondaryComp, String primaryType, String relName) throws M9Exception {
		Map<String, TCComponent> primaryCompMap = new HashMap<String, TCComponent>();
		
		try {			
			// 参照先を取得
			AIFComponentContext[] whereUsedComps = secondaryComp.whereReferenced();
					
			for (AIFComponentContext whereUsedComp : whereUsedComps) {
								
				// 参照先から指定リレーションで紐付くオブジェクトを取得する
				TCComponent[] relatedComps = ((TCComponent)whereUsedComp.getComponent()).getRelatedComponents(relName);
				
				for (TCComponent relatedComp : relatedComps) {
													
					// UIDが一致すれば参照オブジェクトをプライマリとして取得する
					if (secondaryComp.getUid().equals(relatedComp.getUid())) {
						
						TCComponent primaryComp = (TCComponent)whereUsedComp.getComponent();	// プライマリオブジェクト
						
						// リビジョンでない場合は取得しない
						if (!(primaryComp instanceof TCComponentItemRevision)) {
							continue;
						}
						
						// アクティブなシーケンスではない場合は取得しない
						String activeSeq = M9TCUtils.getProperty(M9Constant.ACTIVE_SEQ, primaryComp);
						if (!activeSeq.equals(M9Constant.LATEST_SEQ)) {
							continue;
						}
																			
						// UIDの重複を取り除く
						String primaryUid = primaryComp.getUid();	// プライマリオブジェクトのUIDを取得
						if (!primaryCompMap.containsKey(primaryUid)) {
							primaryCompMap.put(primaryUid, primaryComp);
						}
						
						break;
					}
				}
			}			
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
	
		return new ArrayList<TCComponent>(primaryCompMap.values());
	}
	
	/**
	 * データセットに関連付くTcFileを取得する
	 * @brief
	 * @param dataset　データセット
	 * @return TcFile
	 * @throws M9Exception 
	 */
	public static List<TCComponentTcFile> getTcFiles(TCComponentDataset dataset) throws M9Exception {
		/**=========処理開始=========*/
		List<TCComponentTcFile> tcFiles = new ArrayList<TCComponentTcFile>();
		
		// データセットに関連付くオブジェクトを取得 
		TCComponent[] secondaryComps = getSecondaryComponents(dataset);
		
		for (TCComponent secondaryComp : secondaryComps) {	
			// TcFileか
			if (secondaryComp instanceof TCComponentTcFile) {
				tcFiles.add((TCComponentTcFile) secondaryComp);
			}
		}
		
		return tcFiles;
	}
	
	/**
	 * TC_ROOTを取得する
	 * @brief
	 * @return TC_ROOT
	 * @throws M9Exception
	 */
	public static String getTcRoot() throws M9Exception {
		AbstractAIFApplication app = AIFDesktop.getActiveDesktop().getCurrentApplication();	
		TCSession session = (TCSession) app.getSession();
		
		String[] configs = null;
		
		try {
			configs = session.getServerConfigInfo();	// serverId, Webkey, TC_DATA, TC_ROOT, TC_DB_CONNECTを取得
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
		
		// 取得したConfig情報のサイズをチェック
		// Configsの要素数が5つ以下の場合、エラーとする
		if (configs == null || configs.length < 5) {	// 取得に失敗 or Cofigsのサイズが不正
			throw new M9Exception("Faild To Get Server Config Info");
		}
		
		// Config情報からTC_ROOTを取得し、返却
		return configs[3];
	}
	
	/**
	 * プロパティ値を更新する
	 * @brief
	 * @param tcComp	TCコンポーネント
	 * @param propName	プロパティ名
	 * @param value		プロパティ値
	 * @throws M9Exception
	 */
	public static void setProperty(TCComponent tcComp, String propName, String value) throws M9Exception {
		try {
			tcComp.setProperty(propName, value);
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}	
	}
	
	/**
	 * プロパティ値を更新する
	 * @brief
	 * @param tcComp	TCコンポーネント
	 * @param propMap	更新プロパティを格納したMap (key:プロパティ名, value:プロパティ値)
	 * @throws M9Exception
	 */
	public static void setProperties(TCComponent tcComp, Map<String, String> propMap) throws M9Exception {
		try {
			tcComp.setProperties(propMap);
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			throw new M9Exception(e);
		}
	}
	
	/**
	 * コンポーネントが指定したタイプか判定する
	 * @brief
	 * @param comp		TCコンポーネント
	 * @param typeNames	オブジェクトタイプ
	 * @return　true 指定タイプと一致
	 * @throws TCException 
	 */
	public static boolean isTypesOf(TCComponent comp, String... typeNames) throws TCException {
		/**=========処理開始=========*/		
		return comp.isTypeOf(typeNames);
	}
	
	/**
	 * コンポーネントが存在するか判定する
	 * @brief
	 * @param comp	TCコンポーネント
	 * @return　		true 存在する
	 */
	public static boolean isExsistComponent(TCComponent comp) {
		// コンポーネントが存在しない場合、refreshをかけると例外が発生する
		// 例外を補足した場合、コンポーネントが存在しないとみなす
		
		if (comp == null) {
			return false;
		}
		
		try {
			comp.refresh();
		} catch (TCException e) {	// refreshに失敗
			M9Logger.error(e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * コンポーネントがロック可能か
	 * @brief
	 * @param comp　TCコンポーネント
	 * @return true ロック可能
	 */
	public static boolean canLockComponent(TCComponent comp) {
		try {
			return comp.okToModify();	// 変更が可能か
		} catch (TCException e) {
			M9Logger.error(e.getMessage());
			return false;
		}
	}
	
	/**
	 * カレントアプリケーションで選択中のコンポーネントを全て取得する
	 * @return	InterfaceAIFComponent[]	選択コンポーネント
	 */
	public static InterfaceAIFComponent[] getTargetComponets() {
		/**=========処理開始=========*/	
		return AIFDesktop.getActiveDesktop().getCurrentApplication().getTargetComponents();
	}
	
	/**
	 * ウィンドウをリフレッシュする
	 * @brief
	 */
	public static void refreshWindow() {
		AIFDesktop.getActiveDesktop().getCurrentApplication().refresh();
	}
	
}
