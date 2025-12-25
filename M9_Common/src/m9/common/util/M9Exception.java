/*
 * ファイル名	：	M9Exception.java
 * ファイル説明	：	カスタム例外クラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/005/19	T.Nishi		新規作成
 */
package m9.common.util;

/**
 * カスタム例外クラス
 * @version	01.00.00
 * @date	2017/05/19
 * @author	MCAD T.Nishi
 */
public class M9Exception extends Exception {

	private static final long serialVersionUID = 1L;
	/** メッセージボディ */
	private static String messageBody_ = "";

	/**
	 * コンストラクタ
	 * @brief メッセージコードからメッセージを取得し、例外オブジェクトをExceptionクラスへスローする
	 * @param code
	 * @param params(可変長)
	 */
	public M9Exception(String code, String...params) {
		super(M9Message.getMessage(code, params));
		messageBody_ = M9Message.getMessage(code, params);
	}

	/**
	 * コンストラクタ
	 * @brief メッセージコードからメッセージを取得し、例外オブジェクトをExceptionクラスへスローする
	 * @param code
	 * @param ex
	 * @param params(可変長)
	 */
	public M9Exception(String code, Throwable ex, String...params) {
		super(M9Message.getMessage(code, params), ex);
		messageBody_ = M9Message.getMessage(code, params);
	}

	/**
	 * コンストラクタ
	 * @brief 例外オブジェクトをExceptionクラスへスローする
	 * @param ex
	 */
	public M9Exception(Throwable ex) {
		super(ex);
	}

	/**
	 * コンストラクタ
	 * @brief メッセージをExceptionクラスへスローする
	 * @param message
	 */
	protected M9Exception(String message) {
		super(message);
		messageBody_ = message;
	}
	
	/**
	 * コンストラクタ
	 * @brief メッセージと例外オブジェクトをExceptionクラスへスローする
	 * @param message
	 * @param throwable
	 */
	protected M9Exception(String message, Throwable ex) {
		super(message, ex);
		messageBody_ = message;
	}
	
	/**
	 * M9Exceptionクラスに設定されたメッセージを取得します
	 * @brief 
	 * @return
	 */
	public String getMessage(){
		return messageBody_;
	}
}
