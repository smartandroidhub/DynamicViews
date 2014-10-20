package com.xiaochaoyang.dynamicviews;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	// Parent view for all rows and the add button.
	private LinearLayout mContainerView;
	// The "Add new" button
	private Button mAddButton;

	// There always should be only one empty row, other empty rows will
	// be removed.
	private View mExclusiveEmptyView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.row_container);

		mContainerView = (LinearLayout) findViewById(R.id.parentView);
		mAddButton = (Button) findViewById(R.id.btnAddNewItem);

		// Add some examples
		inflateEditRow("Xiaochao");
		inflateEditRow("Yang");
	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO: Handle screen rotation:
        // encapsulate information in a parcelable object, and save it
        // into the state bundle.

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // TODO: Handle screen rotation:
        // restore the saved items and inflate each one with inflateEditRow;

    }

    // onClick handler for the "Add new" button;
	public void onAddNewClicked(View v) {
		// Inflate a new row and hide the button self.
		inflateEditRow(null);
		v.setVisibility(View.GONE);
	}

	// onClick handler for the "X" button of each row
	public void onDeleteClicked(View v) {
		// remove the row by calling the getParent on button
		mContainerView.removeView((View) v.getParent());
	}

	// Helper for inflating a row
	private void inflateEditRow(String name) {

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View rowView = inflater.inflate(R.layout.row, null);
		final ImageButton deleteButton = (ImageButton) rowView
				.findViewById(R.id.buttonDelete);
		final EditText editText = (EditText) rowView
				.findViewById(R.id.editText);

		if (name != null && !name.isEmpty()) {
			editText.setText(name);
		} else {
			mExclusiveEmptyView = rowView;
			deleteButton.setVisibility(View.INVISIBLE);
		}

		// A TextWatcher to control the visibility of the "Add new" button and
		// handle the exclusive empty view.
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {

				if (s.toString().isEmpty()) {
					mAddButton.setVisibility(View.GONE);
					deleteButton.setVisibility(View.INVISIBLE);

					if (mExclusiveEmptyView != null
							&& mExclusiveEmptyView != rowView) {
						mContainerView.removeView(mExclusiveEmptyView);
					}
					mExclusiveEmptyView = rowView;
				} else {

					if (mExclusiveEmptyView == rowView) {
						mExclusiveEmptyView = null;
					}

					mAddButton.setVisibility(View.VISIBLE);
					deleteButton.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});

		// Inflate at the end of all rows but before the "Add new" button
		mContainerView.addView(rowView, mContainerView.getChildCount() - 1);
	}
}