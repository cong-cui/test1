/*
 * ファイル名	：	M9ProgressDialog.java
 * ファイル説明	：	プログレスダイアログクラス
 * 
 * 更新履歴		更新日		担当者		内容
 * 01.00.00		2017/05/23	S.Nomura	新規作成
 */
package m9.common.ui;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * プログレスダイアログクラス
 * @file M9ProgressDialog.java
 * @brief
 * @version 01.00.00
 * @date	2017/05/23
 * @author 	MCAD S.Nomura
 */
public class M9ProgressDialog extends ProgressMonitorDialog {

	// キャンセルボタン有無
	private boolean hasCancelBtn_ = false;
	
	private String dialogTitle_ = "";
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public M9ProgressDialog(Shell parentShell, String dialogTitle, boolean hasCancelBtn) {
		super(parentShell);
		dialogTitle_ = dialogTitle;
		hasCancelBtn_ = hasCancelBtn;
		
		if (hasCancelBtn_) {
			this.setShellStyle(SWT.CLOSE | SWT.PRIMARY_MODAL);
		}
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		this.subTaskLabel.setAlignment(SWT.RIGHT);	//　サブタスクのラベルを右寄せ
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		if (hasCancelBtn_) {
			createCancelButton(parent);
		}
		
		this.getShell().addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(Event event) {
				cancelPressed();			
			}
			
		});
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		if (hasCancelBtn_) {	// キャンセルボタンありのダイアログサイズ
			return new Point(450, 212);
		} else {	// キャンセルボタンなしのダイアログサイズ
			return new Point(450, 160);
		}
	}
	
	@Override
	protected void configureShell(final Shell shell) {
		super.configureShell(shell);
		shell.setImage(SWTResourceManager.getImage(M9ProgressDialog.class, "/icons/defaultapplication_16.png")); // iconにTCのアイコンを設定
		shell.setText(dialogTitle_);
	}
	
    @Override
    protected void cancelPressed() {
       super.cancelPressed();
       

    }
}
