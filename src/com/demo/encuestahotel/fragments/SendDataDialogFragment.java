package com.demo.encuestahotel.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import com.demo.encuestahotel.R;

public class SendDataDialogFragment extends DialogFragment {

	public interface DialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);

		public void onDialogNegativeClick(DialogFragment dialog);
	}

	DialogListener listener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (DialogListener) activity;
		} catch (ClassCastException e) {
			// TODO: handle exception
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Pregunta").setSingleChoiceItems(R.array.dialog_options, -1, null);
		builder.setPositiveButton(R.string.msg_yes,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								listener.onDialogPositiveClick(SendDataDialogFragment.this);

							}
						});
		
		builder.setNegativeButton(R.string.msg_no,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						listener.onDialogNegativeClick(SendDataDialogFragment.this);

					}
				});

		return builder.create();

	}
}
