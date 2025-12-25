/*
 * ファイル名	：	M9MessageDialog.java
 * ファイル説明	：	共通ダイアログクラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/005/19	T.Nishi		新規作成
 */

package m9.common.ui;

import org.eclipse.swt.widgets.Shell;

import m9.common.util.M9Message;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.Registry;
/**
 *　共通ダイアログクラス
 * @version	01.00.00
 * @date	2017/05/19
 * @author	MCAD T.Nishi
 */
public class M9MessageDialog{
	
	private static Registry registry = Registry.getRegistry("m9.common.ui.messagedialog");
	
	/**
	 * メッセージを指定してダイアログを表示する。
	 * @brief
	 * @param detail
	 * @param dialogType
	 */
	public static void post(String detail, int dialogType){
		String title = getTitle(dialogType);
		String message = getLabel(dialogType);
			
		MessageBox msgBox = new MessageBox(message, detail, title, dialogType);
		msgBox.setModal(true);
		msgBox.setVisible(true);
	}
	
	/**
	 * メッセージIDを指定してダイアログを表示する。
	 * @brief
	 * @param detail
	 * @param dialogType
	 */
	public static void post( String messageId , int dialogType , String... args) {
		String title = getTitle(dialogType);
		String message = getLabel(dialogType);
		String detail = M9Message.getMessage(messageId, args);

		MessageBox msgBox = new MessageBox(message, detail, title, dialogType);
		msgBox.setModal(true);
		msgBox.setVisible(true);
	}
	
	/**
	 * タイトル, メッセージを指定してダイアログを表示する。
	 * @brief
	 * @param title
	 * @param message
	 * @param detail
	 * @param dialogType
	 */
	public static void post(String title, String message, String detail, int dialogType) {
		MessageBox msgBox = null;
		
		// Detailがあれば表示する
		if (detail == null | detail.isEmpty()) {
			msgBox = new MessageBox(message, title, dialogType);
		} else {
			msgBox = new MessageBox(message, detail, title, dialogType);
		}
		
		msgBox.setModal(true);
		msgBox.setVisible(true);
	}
	
	/**
	 * タイトル, メッセージを指定してダイアログを表示する。
	 * @brief
	 * @param title
	 * @param message
	 * @param detail
	 * @param dialogType
	 */
	public static void post(String title, String detail, int dialogType) {
		MessageBox msgBox = null;
		
		String message = getLabel(dialogType);
		// Detailがあれば表示する
		if (detail == null | detail.isEmpty()) {
			msgBox = new MessageBox(message, title, dialogType);
		} else {
			msgBox = new MessageBox(message, detail, title, dialogType);
		}
		
		msgBox.setModal(true);
		msgBox.setVisible(true);
	}
	
	/**
	 * 親画面,タイトル, メッセージを指定してダイアログを表示する。
	 * @brief
	 * 
	 * @param title
	 * @param message
	 * @param detail
	 * @param dialogType
	 */
	public static void post(Shell parent,String title,String message,String detail, int dialogType){
		
		MessageBox msgBox = null;
		// Detailがあれば表示する
		if (detail == null || detail.isEmpty()) {
			msgBox = new MessageBox(parent,message, title, dialogType);
		} else {
			msgBox = new MessageBox(parent,message, detail, title, dialogType);
		}
		
		msgBox.setModal(true);
		msgBox.setVisible(true);
	}
	
	
	/**
	 * タイトルとメッセージIDを指定して完了ダイアログを表示する
	 * @brief
	 * @param title
	 * @param messageId
	 */
	public static void complete(String title,String messageId){
		
		String message = M9Message.getMessage(messageId);
		
		MessageBox msgBox = new MessageBox(message,title,MessageBox.INFORMATION);
		msgBox.setModal(true);
		msgBox.setVisible(true);
	}
	
	
	

	/**
	 * ダイアログのタイトルを取得する
	 * @brief
	 * @param type
	 * @return title
	 */
	private static String getTitle(int type){
		String title = "";
		if (type == MessageBox.INFORMATION) {
			title = registry.getString("INFORMATION.TITLE");
		} else if (type == MessageBox.WARNING) {
			title = registry.getString("WARNING.TITLE");
		} else if (type == MessageBox.ERROR) {
			title = registry.getString("ERROR.TITLE");
		}
		return title;
	}
	
	/**
	 * ダイアログのメッセージラベルを取得する。
	 * @brief
	 * @param type
	 * @return label
	 */
	private static String getLabel(int type){
		String label = "";
		if (type == MessageBox.INFORMATION) {
			label = registry.getString("label.INFORMATION.TITLE");
		} else if (type == MessageBox.WARNING) {
			label = registry.getString("label.WARNING.TITLE");
		} else if (type == MessageBox.ERROR) {
			label = registry.getString("label.ERROR.TEXT");
		}
		return label;
	}
	
	
}
