/*
 * ファイル名	：	M9Constant.java
 * ファイル説明	：	定数クラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/05/15	T.Nishi		新規作成
 * 02.00.00		2017/09/21	T.Nishi		定数M9_NAMED_REFERENCE_TYPEを追加
 * 03.00.00     2018/12/19  和泉諒               定数MISC_BINARY、CAE_Dummyを追加
 */
package m9.common.util;


/**
 * 定数クラス
 * @file	M9Constant.java
 * @brief	定数クラス
 * @version	01.00.00
 * @date	2017/05/15
 * @author	MCAD T.nishi
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.01.00		2018/11/16	T.Mochizuki	アドオン実行履歴書込用プリファレンスを追加
 * 02.00.00		2025/02/12	Cui			CATIAデータ出力定義追加
 */

public interface M9Constant {
	
	
	/* preference */
	public static final String DRAG_AND_DROP_DEFAULT_DATASET_TYPE = "DRAG_AND_DROP_default_dataset_type";
	
	public static final String M9_LIMIT_FOLDER_MAX_LEVEL = "M9_LimitFolderMaxLevel";							//フォルダ内最大階層レベル(1以上)
	public static final String M9_LIMIT_FILE_MAX_COUNT = "M9_LimitFileMaxCount";								//フォルダ内登録ファイル最大数
	public static final String M9_LIMIT_MAX_SIZE = "M9_LimitMaxSize";											//フォルダ内登録ファイル最大サイズ数
	public static final String M9_IMP_FOLDER_TRANS_BPS = "M9_ImpFolderTransbps";								//CAEデータ登録機能転送速度(kbps)
	public static final String M9_FOLDER_CREATE_TIME = "M9_FolderCreateTime";									//フォルダアイテム登録時間
	public static final String M9_CREATE_TARGET_TYPE_FOLDER = "M9_CreateTargetTypeFolder";						//TCへ登録するフォルダアイテム定義
	public static final String M9_EXPORT_FOLDER_TRANCE_BPS = "M9_ExpFolderTransbps";							//CAEデータ取り出し機能転送速度
	public static final String M9_FILE_EXPORT_TIME = "M9_FileExportTime";										//ファイルエクスポート時間
	public static final String M9_CAE_EXPORT_TARGET_TYPE = "M9_CAEExportTargetType";							//選択可能タイプ定義
	public static final String M9_CREATE_ANALYSIS_TARGET_TYPE = "M9_CreateAnalysisTargetType";					//解析アイテム作成可能な選択オブジェクトタイプ定義
	public static final String M9_NAMED_REFERENCE_TYPE = "M9_Named_Reference_Type";								//データセット参照タイプ定義プリファレンス
	public static final String M9_EXECUTION_LOG_FOLDER = "M9_ExecutionLogFolder";
	public static final String M9_CAEDATAIMPORT_EXECUTIONLOGFOLDER = "M9_CAEDataImport_ExecutionLogFolder";	//CAEデータ登録ログ出力先フォルダ
	public static final String M9_CATIA_EXPORT_TARGET_TYPE = "M9_CATIAExportTargetType";	//CATIAデータタイプ定義
	
	/* Item type */
	public static final String ITEM_TYPE= "Item";
	public static final String ITEM_REVISION_TYPE= "ItemRevision";
	public static final String MP_FOLDER_TYPE= "M9_Folder";
	public static final String M9_FOLDER_REV_TYPE = "M9_FolderRevision";
	public static final String M9_MODEL_FOLDER_TYPE= "M9_Model_F";
	public static final String M9_MODEL_FOLDER_REV_TYPE = "M9_Model_FRevision";									//モデル用フォルダリビジョン
	public static final String M9_ANALYSIS_FOLDER_TYPE= "M9_Analysis_F";
	public static final String M9_ANALYSIS_FOLDER_REV_TYPE = "M9_Analysis_FRevision";							//解析用フォルダ#2リビジョン
	public static final String M9_PRT_ANALYSIS_FOLDER_TYPE= "M9_Prt_Anlyss_F";
	public static final String M9_PRT_ANALYSIS_FOLDER_REV_TYPE = "M9_Prt_Anlyss_FRevision";						//解析用フォルダ#0リビジョン
	public static final String M9_MODEL_REVISON_TYPE = "M9_ModelRevision";										//モデルリビジョン
	public static final String M9_ANALYSIS_TYPE = "M9_Analysis";												//解析アイテム
	public static final String M9_ANALYSIS_REVISION_TYPE = "M9_AnalysisRevision";								//解析アイテムリビジョン
	public static final String M9_CAD_DATA_REVISION_TYPE = "M9_CADDataRevision";								//CADデータリビジョン
	public static final String CAE_MODEL_REVISION_TYPE = "CAEModelRevision";
	
	/* type */
	public static final String DATASET_TYPE	= "Dataset";
	public static final String FOLDER_TYPE = "Folder";
	public static final String SHARE_FOLDER_TYPE = "Share_Folder";
	public static final String WEB_LINK_TYPE = "Web Link";
	public static final String MISC_TYPE = "MISC";
	public static final String URL_TYPE = "url";
	
	/* folder name */
	public static final String M9_CAE = "CAE";
	public static final String M9_MODEL = "モデル";
	public static final String M9_ANALYSIS = "解析";
	public static final String M9_CAD = "CAD";
	public static final String M9_CLASS = "類別";
	
	/* object attributes */
	public static final String OWNING_USER="owning_user";
	public static final String CONTENTS_REL="contents";
	public static final String ITEM_ID = "item_id" ;
	public static final String ITEM_REVISION_ID = "item_revision_id" ;
	public static final String ITEM_REVISION_LIST = "revision_list";
	public static final String OBJECT_TYPE = "object_type" ;
	public static final String OBJECT_DESC = "object_desc" ;
	public static final String OBJECT_NAME 	= "object_name" ;
	public static final String OBJECT_STRING = "object_string";
	public static final String OWNING_GROUP="owning_group";
	public static final String IMAN_BASED_ON = "IMAN_based_on";
	public static final String WEB_LINK_URL = "url";
	public static final String REL_LIST = "rel_list";
	public static final String ORIGINAL_FILE_NAME = "original_file_name";
	public static final String BYTE_SIZE = "byte_size";
	public static final String ACTIVE_SEQ = "active_seq";	// 最新シーケンス判定フラグ
	
	public static final String M9_SERIES_NAME = "m9_Series_Name";
	public static final String M9_PHASE = "m9_PHASE";
	public static final String M9_ANALYSIS_GROUP = "m9_analysis_group";
	public static final String M9_PROT_NO = "m9_PROT_NO";
	public static final String M9_GLMC = "m9_GLMC";
	public static final String M9_CAE_MEMO = "m9_CAE_MEMO";
	public static final String M9_CAD_ITEM_REV_ID = "m9_cad_item_rev_id";		// CADリビジョンID
	public static final String M9_CAD_ITEM_REV_NAME = "m9_cad_item_rev_name";	// CADリビジョン名
	public static final String M9_MODEL_REV_ID = "m9_model_rev_id";				// モデルリビジョンID
	
	/* relation */
	public static final String IMAN_SPECIFICATION_REL = "IMAN_specification";
	public static final String TC_CAE_DEFINING_REL = "TC_CAE_Defining";
	public static final String TC_CAE_INCLUDE_REL = "TC_CAE_Include";
	public static final String TC_CAE_TARGET_REL = "TC_CAE_Target";
	
	public static final String M9_IMAN_SIMULATION_REL = "M9_IMAN_Simulation";
	
	/* 定数値 */
	public static final String LATEST_SEQ = "1";	// 最新シーケンス判定値(active_seq = 1 で最新)
	
	/* UI */
	// CAE登録・取出ダイアログサイズ
	public static final int M9_IMP_EXP_DIALOG_WIDTH = 660;
	public static final int M9_IMP_EXP_DIALOG_HIGHT = 510;
	// CAE登録・取出テーブルサイズ
	public static final int M9_IMP_EXP_TABLE_WIDTH = 600;
	public static final int M9_IMP_EXP_TABLE_HIGHT = 256;
	// CAE登録・取出テーブル位置
	public static final int M9_IMP_EXP_TABLE_LOCATION_X = 22;
	// CAE登録・取出テーブル幅
	public static final int M9_IMP_EXP_NAME_COL_WIDTH = 417;	// 名前カラム
	public static final int M9_IMP_EXP_SIZE_COL_WIDTH = 163;	// サイズカラム
	
	public static final int LOCATION_X = 200;
	public static final int LOCATION_Y = 200;
	/* 環境変数 */
	public static final String M9_DEBUG_MODE = "M9_DEBUG_MODE";
	public static final String M9_DEBUG_MODE_ON = "ON";
	
	/* CAEデータ登録フォルダアイコン*/
	public static final String M9_F03_FOLDER_IMAGE_PATH = "/com/teamcenter/rac/util/images/directoryopened_16.png";
	public static final String M9_F03_FILE_IMAGE_PATH = "/com/teamcenter/rac/util/images/defaulticon_16.png";

	public static final String MISC_BINARY = "MISC_BINARY";
	public static final String CAE_Dummy = "CAE_Dummy";
	
}
