/*
 * ファイル名	：	M9Message.java
 * ファイル説明	：	メッセージ管理クラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/005/19	T.Nishi		新規作成
 * 02.00.00		2019/04/12	T.Mochizuki	想定外のエラーハンドリング処理等追加
 * 03.00.00		2025/02/12	Cui			CATIAデータ出力メッセージ追加
 */
package m9.common.util;
/**
 * メッセージ管理クラス
 * @version	01.00.00
 * @date	2017/05/19
 * @author	MCAD T.Nishi
 */
import com.teamcenter.rac.util.Registry;

public class M9Message {
	/** パッケージID */
	private static final String PACKAGE_ID = "m9.common.util.message";

	/**
	 * メッセージを取得する
	 * @param id メッセージID
	 * @param args 引数(可変長)
	 * @return メッセージ
	 */
	public static String getMessage(String id, String... args) {
		Registry registry = Registry.getRegistry(PACKAGE_ID);
		return registry.getStringWithSubstitution(id, args);
	}
	
	/*
	 * メッセージIDの定義
	 */

	/* 共通メッセージ */	
	public static final String M9_00_E001 = "M9_00_E001";
	
	/* モデルアイテム作成機能メッセージ */
	public static final String M9_01_I001 = "M9_01_I001";
	public static final String M9_01_W001 = "M9_01_W001";
	
	// 解析アイテム作成機能メッセージ
	public static final String M9_02_I001 = "M9_02_I001";
	public static final String M9_02_E001 = "M9_02_E001";
	public static final String M9_02_E002 = "M9_02_E002";
	public static final String M9_02_E003 = "M9_02_E003";
	public static final String M9_02_E004 = "M9_02_E004";
	public static final String M9_02_E005 = "M9_02_E005";
	public static final String M9_02_E006 = "M9_02_E006";
	public static final String M9_02_E007 = "M9_02_E007";
	public static final String M9_02_E008 = "M9_02_E008";
	public static final String M9_02_E009 = "M9_02_E009";
	public static final String M9_02_E010 = "M9_02_E010";
	public static final String M9_02_E011 = "M9_02_E011";
	//02.00.00_start
	public static final String M9_02_E012 = "M9_02_E012";
	//02.00.00_end
	public static final String M9_02_W001 = "M9_02_W001";
	
	//CAEデータ登録機能メッセージ定義
	public static final String M9_03_I001 = "M9_03_I001";
	public static final String M9_03_E001 = "M9_03_E001";
	public static final String M9_03_E002 = "M9_03_E002";
	public static final String M9_03_E003 = "M9_03_E003";
	public static final String M9_03_E004 = "M9_03_E004";
	public static final String M9_03_E005 = "M9_03_E005";
	public static final String M9_03_E006 = "M9_03_E006";
	public static final String M9_03_E007 = "M9_03_E007";
	public static final String M9_03_E008 = "M9_03_E008";
	public static final String M9_03_E009 = "M9_03_E009";
	public static final String M9_03_E010 = "M9_03_E010";
	public static final String M9_03_E011 = "M9_03_E011";
	public static final String M9_03_E012 = "M9_03_E012";
	public static final String M9_03_E013 = "M9_03_E013";
	public static final String M9_03_E014 = "M9_03_E014";
	public static final String M9_03_E015 = "M9_03_E015";
	public static final String M9_03_E016 = "M9_03_E016";
	
	// CAEデータ取出機能メッセージ定義
	public static final String M9_04_I001 = "M9_04_I001";
	public static final String M9_04_E001 = "M9_04_E001";
	public static final String M9_04_E002 = "M9_04_E002";
	public static final String M9_04_E003 = "M9_04_E003";
	public static final String M9_04_E004 = "M9_04_E004";
	public static final String M9_04_E005 = "M9_04_E005";
	public static final String M9_04_E006 = "M9_04_E006";
	public static final String M9_04_E007 = "M9_04_E007";
	public static final String M9_04_E008 = "M9_04_E008";
	public static final String M9_04_E009 = "M9_04_E009";
	public static final String M9_04_E010 = "M9_04_E010";
	public static final String M9_04_E011 = "M9_04_E011";
	public static final String M9_04_E012 = "M9_04_E012";
	public static final String M9_04_W001 = "M9_04_W001";
	
	/* 指定フォルダ移動機能メッセージ定義 */
	public static final String M9_05_E001 = "M9_05_E001";
	public static final String M9_05_E002 = "M9_05_E002";
	public static final String M9_05_E003 = "M9_05_E003";
	public static final String M9_05_E004 = "M9_05_E004";
	public static final String M9_05_E005 = "M9_05_E005";
	public static final String M9_05_E006 = "M9_05_E006";
	public static final String M9_05_E007 = "M9_05_E007";
	public static final String M9_05_W001 = "M9_05_W001";


	public static final String M9_06_E001 = "M9-06-E001";
	public static final String M9_06_E002 = "M9-06-E002";
	
	/* CATIAデータ出力メッセージ定義 */
	public static final String M9_07_W001 = "M9-07-W001";
	public static final String M9_07_E002 = "M9-07-E002";
	public static final String M9_07_E003 = "M9-07-E003";
	public static final String M9_07_E004 = "M9-07-E004";
	public static final String M9_07_E005 = "M9-07-E005";
	public static final String M9_07_E006 = "M9-07-E006";
	public static final String M9_07_E007 = "M9-07-E007";
	public static final String M9_07_E008 = "M9-07-E008";
	public static final String M9_07_E009 = "M9-07-E009";
	public static final String M9_07_E010 = "M9-07-E010";
	
	
}
