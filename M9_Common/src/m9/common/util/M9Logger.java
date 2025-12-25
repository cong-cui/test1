/*
 * ファイル名	：	M9Logger.java
 * ファイル説明	：	ログ出力クラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/005/19	T.Nishi		新規作成
 * 02.00.00		2021/08/03	井上 建		処理中で利用されていないインポートを除去
 */
package m9.common.util;


import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.teamcenter.rac.aif.AIFDesktop;
import com.teamcenter.rac.kernel.TCSession;

import m9.common.util.M9Exception;

/**
 * ログ出力クラス
 * @version	01.00.00
 * @date	2017/05/19
 * @author	MCAD T.Nishi
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.01.00		2018/11/16	T.Mochizuki	アドオン実行履歴書込メソッド作成
 */
public class M9Logger{

	/* ログ種別 */
	private static final String INFO = "[INFO] ";
	private static final String ERROR = "[ERROR] ";
	private static final String WARN = "[WARN] ";
	private static final String DEBUG = "[DEBUG] ";
	
	/* メソッド開始・終了 */
	private static final String PREFIX_FUNC_START = "[START] ";
	private static final String PREFIX_FUNC_END = "[END] ";
	
	/**
	 * infoログを出力する
	 * @brief
	 * @param message
	 */
	public static void info(String message){
		outputMessage(message,M9Logger.INFO);
	}
	
	/**
	 * エラーログを出力する
	 * @brief
	 * @param message
	 */
	public static void error(String message){
		outputMessage(message,M9Logger.ERROR);
	}
	
	/**
	 * エラーログを出力する(複数)
	 * @brief
	 * @param objects
	 */
	public static void errors(Object[] objects) {
		for(Object message : objects){
			outputMessage((String) message,M9Logger.ERROR);
		}
	}
	
	/**
	 * WARNログを出力する
	 * @brief
	 * @param message
	 */
	public static void warn(String message){
		outputMessage(message,M9Logger.WARN);
	}
	
	/**
	 * debugログを出力する
	 * @brief
	 * @param message
	 */
	public static void debug(String message){
		String env = System.getenv(M9Constant.M9_DEBUG_MODE);
		
		if (env == null) {	// デバッグモードが定義されていない場合は処理スルー
			return;
		}
		
		if(env.equals(M9Constant.M9_DEBUG_MODE_ON)){
			outputMessage(message,M9Logger.DEBUG);
		}
	}
	
	/**
	 * メソッドの開始ログを出力する(デバッグログ)
	 * @brief
	 */
	public static void funcStart() {
		// 呼び出し元の情報を取得
		String classAndMethod = askCallClassAndMethodName();
		String message = PREFIX_FUNC_START + classAndMethod;
		debug(message);		
	}
	
	/**
	 * メソッドの終了ログを出力する(デバッグログ)
	 * @brief
	 */
	public static void funcEnd() {
		// 呼び出し元の情報を取得
		String classAndMethod = askCallClassAndMethodName();
		String message = PREFIX_FUNC_END + classAndMethod;
		debug(message);		
	}
	
	/**
	 * 呼び出し元のクラス・メソッド名を取得する
	 * @brief
	 * @return　呼び出し元のクラス・メソッド情報
	 */
	private static String askCallClassAndMethodName() {
		String classAndMethodName = "";
		
		// 呼び出し元の情報を取得
		StackTraceElement stackElem = Thread.currentThread().getStackTrace()[3];	// Loggerの呼び出し元の元を取得
		
		if (stackElem != null) {
			classAndMethodName = stackElem.toString();
		}
		
		return classAndMethodName;
	}
	
	/**
	 * 現在時刻を取得する(yyyy/MM/dd HH:mm:ss)
	 * @brief
	 * @return timeStamp
	 */
	private static String getTime(){
		/// 現在時刻
		String timeStamp = null;
		/// [yyyy/MM/dd HH:mm:ss]書式で現在時刻を取得する・
		Date date = new Date();
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
	    timeStamp = sdf1.format(date);
	    
		return timeStamp;
	}
	
	/**
	 * ログ文字列を取得する。
	 * @brief
	 * @param message
	 * @return timeStamp
	 */
	public static String getErrorMessege(String message){
		return getTime() + M9Logger.ERROR + message;
	}
	
	/**
	 * コンソールにログを出力する
	 * @brief
	 * @return timeStamp
	 */
	private static void outputMessage(String message,String type) {
		//TcLogger.disableSystemPrintStreamOverride();
		String out = getTime() + type + message;
		System.out.println(out);
		//TcLogger.initialize();
	}
	
	//ADD_01.01.00_START
	/**
	 * アドオン実行履歴を書き込む
	 * @brief アドオン実行履歴(出力日時,ホスト名,OSユーザー名,TCユーザー名,機能名)を書き込む。
	 * @param	FunctionName	機能名
	 * @throws M9Exception	　　　 処理中にエラーが発生した場合
	 * @return なし
	 * @throws Exception 

	 */
	public static void RecodeExecutionLog(String FunctionName){
		M9Logger.funcStart();
			try{
				/** 事前チェック*/
				if(FunctionName.isEmpty()){
					throw new M9Exception(M9Message.getMessage(M9Message.M9_00_E001));
				}
				/** 実行可能オブジェクトタイププリファレンス定義取得 */
				M9TCPreferenceUtils prefUtils = M9TCPreferenceUtils.getInstance();
				String[] values = prefUtils.getStringValues(M9Constant.M9_EXECUTION_LOG_FOLDER);
				/**  取得結果チェック */
				if (values == null || values.length <= 0 ||values[0].equals("")) {	// 値なしまたは取得に失敗
					throw new M9Exception(M9Message.getMessage(M9Message.M9_00_E001));
					}
				/** フォルダ存在確認 */
				if (M9FileUtils.isExist(values[0])) {
					//存在する場合
				}else{
					//存在しない場合
					throw new M9Exception(M9Message.getMessage(M9Message.M9_00_E001));
				}
				/** ログファイル名*/	
					/** セッションの取得*/	
					TCSession tcSession =(TCSession)AIFDesktop.getActiveDesktop().getCurrentApplication().getSession();
					/** ログファイル名作成*/	
					String OsUserName=System.getProperty("user.name");
					String TcUserName=tcSession.getUserName();
					/** 【OSユーザー名】_【TCユーザー名】_Teamcenter_Addon_Execution_Log.csv */
					String ExecutionLogName =OsUserName+ "_"+TcUserName+"_Teamcenter_Addon_Execution_Log.csv";
				/** 機能実行履歴追記 */
					/** ログファイルフルパス*/
					String fullpath = values[0]+"\\"+ExecutionLogName;
					/** ログ追記文字列作成*/
						/** 出力日時,ホスト名,OSユーザー名,TCユーザー名,機能名 */
						String ExecutionLogString =getTime()+","+java.net.InetAddress.getLocalHost().getHostName() +","+OsUserName+","+TcUserName+","+FunctionName;
						/** ログ追記　ファイルが存在しない場合は新規作成する。*/
						FileWriter fw = new FileWriter(fullpath, true);
						fw.write(ExecutionLogString+"\r\n");
						fw.close();
			}
			catch(Exception e){
				M9Logger.error(e.getMessage());
			}
			M9Logger.funcEnd();
	}
	//ADD_01.01.00_END
}