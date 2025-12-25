/*
 * ファイル名	:	FileUtils.java
 * ファイル説明	:	ファイル処理　ユーティリティクラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/05/17	S.Nomura	新規作成
 */
package m9.common.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ファイル処理 ユーティリティクラス
 * @file FileUtils.java
 * @brief
 * @version
 * @date
 * @author
 */
public class M9FileUtils {

	private static final String EXT_SEPARATOR = ".";
	
	// Windows予約語
	private static final String[] MS_DEVICE_NAMES = {
			"AUX", "COM0", "COM1", "COM2", "COM3", 
			"COM4", "COM5", "COM6", "COM7", "COM8", 
			"COM9", "CON", "LPT0", "LPT1", "LPT2", 
			"LPT3", "LPT4" , "LPT5", "LPT6", "LPT7", 
			"LPT8", "LPT9", "NUL", "PRN"
			}; 
		
	// 禁則文字一致判定文字列
	//  \ / : * ? " < > | 
	private static final String INVALID_CHAR_REGEX = 
			"\\\\" 	 +		// '\' 
			"/" 	 + 		// '/'
			":" 	 +		// ':'
			"\\*" 	 +		// '*'
			"\\?"	 +		// '?'
			"\""	 +		// '"'
			"<"		 +		// '<'
			">"		 +		// '>'
			"\\|";		// '|'
	
	/**
	 * フォルダまたはファイルが存在するか
	 * @brief
	 * @param path
	 * @return
	 */
	public static boolean isExist(String path) {
		File fileOrFolder = new File(path);
		
		if (fileOrFolder.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 書き込み権があるか
	 * @brief
	 * @param folderPath
	 * @return
	 */
	public static boolean canWrite(String path) {	
		Path fileOrFolder = Paths.get(path);
		return	Files.isWritable(fileOrFolder);
	}
	
	/**
	 * フォルダを作成する
	 * @brief
	 * @param path
	 * @throws Exception 
	 */
	public static void createDir(String path) throws Exception {
		File dir = new File(path);
		
		boolean isSuccess = false;
		isSuccess = dir.mkdir();
		if (!isSuccess) {
			throw new Exception("Create Dir Faild");
		}
		
	}
	
	/**
	 * 指定したフォルダ配下のファイル・フォルダ名をすべて取得する
	 * @brief
	 * @param path
	 * @return
	 */
	public static List<String> getChildFileFolderNames(String folderPath) {
		File folder = new File(folderPath);
		List<String> nameList = new ArrayList<String>();
		
		if (!folder.isDirectory()) {	// 指定対象がフォルダでないため終了
			return nameList;
		}
		
		// 配下のファイル・フォルダ名を取得
		String[] names = folder.list();
		
		if (names == null || names.length <= 0) {	// 配下が空
			return nameList;	// 空のリストを返却
		}
		
		nameList = Arrays.asList(names);
		
		return nameList;
	}
		
	/**
	 * ファイル名と拡張子を分割する
	 * @brief
	 * @param src
	 * @return String[] [0] ファイル名, [1] 拡張子名(.を含む) 拡張子がない場合は空白("")
	 */
	public static String[] separateNameAndExt(String src) {
		String name = src;
		String ext = "";
		
		// ファイル名.拡張子の場合分割する
		int dotPoint = src.lastIndexOf(EXT_SEPARATOR);
		
		if (dotPoint > -1) {
			name = src.substring(0, dotPoint);	// ファイル名
			ext = src.substring(dotPoint);	// 拡張子(.含む)
		}
		
		String separated[] = {name, ext};
		return separated;
		
	}
	
	/**
	 *　フォルダ・ファイル名が有効か
	 * @brief Windowsの予約語でない かつ 禁則文字を含まない 
	 * @param name
	 * @return
	 */
	public static boolean isValidName(String name) {
		
		// フォルダ・ファイル名に禁則文字が含まれているか
		if (hasInvalidChar(name)) {	// 禁則文字あり
			return false;
		}
		
		// Windowsの予約語がフォルダ・ファイル名となっているか
		if (isMsDeviceName(name)) {	// 予約語と一致
			return false;
		}
		
		return true;
	}
	
	/**
	 * パスが絶対パスか
	 * @brief 
	 * @param path
	 * @return true 絶対パス(UNC形式の場合もtrue)
	 */
	public static boolean isAbsolutePath(String path) {
		File file = new File(path);
		return file.isAbsolute();
	}
	
	/**
	 * 禁則文字が含まれてるか
	 * @brief
	 * @param name
	 * @return
	 */
	private static boolean hasInvalidChar(String name) {		
		return name.matches(".*[" + INVALID_CHAR_REGEX + "].*");
	}

	/**
	 * フォルダ・ファイル名がWindowsの予約語か
	 * @brief
	 * @param name
	 * @return
	 */
	private static boolean isMsDeviceName(String fileName) {
		boolean isDeviceName = false;
		
		// ファイル名と拡張子を分割
		String[] separated = separateNameAndExt(fileName);
		String name = separated[0];	// ファイル名部分を取得
		name = name.toUpperCase();	// 大文字に揃える
		
		for (String deviceName : MS_DEVICE_NAMES) {
			// 予約語と一致するか
			if (name.equals(deviceName)) {
				// 一致
				isDeviceName = true;
				break;
			}
		}
		
		return isDeviceName;
	}
	
}
